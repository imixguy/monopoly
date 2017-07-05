package rw.gcktc.cms.material.parsetonode;


import com.fasterxml.jackson.annotation.*;
import org.apache.log4j.Logger;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.NodeProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by miha on 09.09.2014.
 * Класс для преобразования свойств объекта в поля key и value класса NodeProperty
 */
public abstract class NodeExt implements INodeExt {
    private static Logger log =  Logger.getLogger(NodeExt.class);
    @JsonIgnore
    protected Node node;

    public NodeExt() {
        node=new Node();
        pastAllData();
    }

    public NodeExt(Node node) {
        this.node = node;
        pastAllData();
    }

    public Long getId() {
        return node.getId();
    }

    public void setId(Long id) {
        this.node.setId(id);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setCopyAllData(Object objNew,Object objCopy){
        for(Field field:objNew.getClass().getFields()){
            try {
                Field fCopy=objCopy.getClass().getField(field.getName());
                field.set(objNew,fCopy.get(objCopy));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    //Метод преднозначен для работы с анотированными классами и позволяет вставить все данные в класс из объекта Node
    protected void pastAllData(){
        if(node.getId()==null) return;
        Object bean=this;
        Class klass = bean.getClass();
        boolean includeSuperClass=klass.isAnnotationPresent(IncludeSuperclass.class);
        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
        if (klass.getClassLoader() == null) {
            includeSuperClass = false;
        }

        List<Method> methods = getMethods(klass);
        List<Method> excludeMethod=new ArrayList<Method>();
        for ( Method method:methods) {
            if(method.isAnnotationPresent(Transient.class)){
                excludeMethod.add(method);
            }
        }

        List<Field> fields = getFields(klass);
        List<Field> excludeField=new ArrayList<Field>();
        for (Field field:fields) {
            if(field.isAnnotationPresent(Transient.class)){
                excludeField.add(field);
            }
        }
        methods.removeAll(excludeMethod);
        fields.removeAll(excludeField);
        pastAllData(methods,fields);
    }

    //Метод преднозначен для работы с неанотированными классами, для прямого выова
    //и позволяет вставить все данные в класс из объекта Node
    public void pastAllData(boolean includeSuperClass){
        pastAllData(includeSuperClass,null,null);
    }

    //Метод преднозначен для работы с неанотированными классами, для прямого выова
    //и позволяет вставить все данные в класс из объекта Node
    public void pastAllData(boolean includeSuperClass, List<String> methodsExclude, List<String> fieldsExclude){
        if(node.getId()==null) return;
        Object bean=this;
        Class klass = bean.getClass();
        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
        if (klass.getClassLoader() == null) {
            includeSuperClass = false;
        }

        List<Method> methods = getMethods(klass);
        List<Method> excludeMethod=new ArrayList<Method>();
        for (Method method:methods) {
            if(isExcludeMethod(method.getName(),methodsExclude)){
                excludeMethod.add(method);
            }
        }

        List<Field> fields = getFields(klass);
        List<Field> excludeField=new ArrayList<Field>();
        for (Field field:fields) {
            if(isExcludeField(field.getName(),fieldsExclude)){
                excludeField.add(field);
            }
        }
        methods.removeAll(excludeMethod);
        fields.removeAll(excludeField);
        pastAllData(methods,fields);
    }

    //Метод позволяет вставить все данные в перечисленные методы из объекта Node
    protected void pastAllData(List<Method> methods, List<Field> fields){
        Object bean=this;
        Class klass = bean.getClass();
        for (Method method :methods) {
            try {
//                if(excludeMethod==null | (excludeMethod!=null && !isExcludeMethod(method.getName(),excludeMethod))){
                if (Modifier.isPublic(method.getModifiers())) {
                    Type returnType = method.getGenericReturnType();
                    if(returnType instanceof Collection | method.getReturnType().isArray()){
                        //делаем разбор объектов массива
                        //key  в nodeProperty должен начинаться с название текущего свойства знак массива []
                        //точка и проперти объекта массива (person[4].name)
                    }else {
                        if(!method.getReturnType().getClass().getName().startsWith("java.lang")){
                            //делаем разбор объекта с его полями
                            //key  в nodeProperty должен начинаться с название текущего свойства
                            //точка и проперти вложенного объекта (person.name)
                        }else {
                            String name = method.getName();
                            String key = "";
                            if (name.startsWith("get")) {
                                key = name.substring(3);
                            } else if (name.startsWith("is")) {
                                key = name.substring(2);
                            }
                            String keyU = key;
                            if (key.length() > 0 &&
                                    Character.isUpperCase(key.charAt(0)) &&
                                    method.getParameterTypes().length == 0) {
                                if (key.length() == 1) {
                                    key = key.toLowerCase();
                                } else if (!Character.isUpperCase(key.charAt(1))) {
                                    key = key.substring(0, 1).toLowerCase() +
                                            key.substring(1);
                                }
                                if (!isExcludedField(key, fields)) {
                                    try {
                                        Method mset = bean.getClass().getMethod("set" + keyU, method.getReturnType());
                                        List<NodeProperty> lnp = node.getPropertysValue(node, key);
                                        if (lnp != null && lnp.size() > 0) {
                                            String value = lnp.get(0).getValue();
                                            if (method.getReturnType().equals(Boolean.class)) {
                                                if (value != null) {
                                                    mset.invoke(bean, value.equals("true"));
                                                }
                                            } else {
                                                mset.invoke(bean, value);
                                            }
                                        }
                                    } catch (NoSuchMethodException e) {
                                        //такого сеттера не существует
                                    } catch (SecurityException e) {
                                        //метод закрыт и мы его инжектим
                                    } catch (IllegalArgumentException e) {
                                        //неопределенная инжекция
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void fillObject(boolean includeSuperClass) {
        fillObject(includeSuperClass,null,null);
    }

    public void fillObject(boolean includeSuperClass, List<String> methodsExclude, List<String> fieldsExclude) {
        Object bean=this;
        Class klass = bean.getClass();
        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
        if (klass.getClassLoader() == null) {
            includeSuperClass = false;
        }

        List<Method> methods = getMethods(klass);
        List<Method> excludeMethod=new ArrayList<Method>();
        for (Method method:methods) {
            if(isExcludeMethod(method.getName(),methodsExclude)){
                excludeMethod.add(method);
            }
        }

        List<Field> fields = getFields(klass);
        List<Field> excludeField=new ArrayList<Field>();
        for (Field field:fields) {
            if(isExcludeField(field.getName(),fieldsExclude)){
                excludeField.add(field);
            }
        }
        methods.removeAll(excludeMethod);
        fields.removeAll(excludeField);
        fillObject(methods, fields);
    }

    public void fillObject() {
        Object bean=this;
        Class klass = bean.getClass();
        boolean includeSuperClass=klass.isAnnotationPresent(IncludeSuperclass.class);
        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
        if (klass.getClassLoader() == null) {
            includeSuperClass = false;
        }

        List<Method> methods = getMethods(klass);
        List<Method> excludeMethod=new ArrayList<Method>();
        for ( Method method:methods) {
            if(method.isAnnotationPresent(Transient.class)){
                excludeMethod.add(method);
            }
        }

        List<Field> fields = getFields(klass);
        List<Field> excludeField=new ArrayList<Field>();
        for (Field field:fields) {
            if(field.isAnnotationPresent(Transient.class)){
                excludeField.add(field);
            }
        }
        methods.removeAll(excludeMethod);
        fields.removeAll(excludeField);

        fillObject(methods,fields);
    }

    private void fillObject(List<Method> methods, List<Field> fields) {
        Object bean=this;
        for ( Method method:methods) {
            try {
                Type returnType = method.getGenericReturnType();
                if(returnType instanceof Collection | method.getReturnType().isArray()){
                    //делаем разбор объектов массива
                    //key  в nodeProperty должен начинаться с название текущего свойства знак массива []
                    //точка и проперти объекта массива (person[4].name)
                }else {
                    if(!method.getReturnType().getClass().getName().startsWith("java.lang")){
                        //делаем разбор объекта с его полями
                        //key  в nodeProperty должен начинаться с название текущего свойства
                        //точка и проперти вложенного объекта (person.name)
                    }else {

//                if(excludeMethod==null | (excludeMethod!=null && !isExcludeMethod(method.getName(),excludeMethod))){
                        if (Modifier.isPublic(method.getModifiers())) {
                            String name = method.getName();
                            String key = "";
                            if (name.startsWith("get")) {
                                key = name.substring(3);
                            } else if (name.startsWith("is")) {
                                key = name.substring(2);
                            }
                            String keyU = key;
                            if (key.length() > 0 &&
                                    Character.isUpperCase(key.charAt(0)) &&
                                    method.getParameterTypes().length == 0) {
                                if (key.length() == 1) {
                                    key = key.toLowerCase();
                                } else if (!Character.isUpperCase(key.charAt(1))) {
                                    key = key.substring(0, 1).toLowerCase() +
                                            key.substring(1);
                                }
//                            Field f=getField(fields,key);
                                if (!isExcludedField(key, fields)) {
                                    //method.invoke(bean);
                                    try {
                                        Object ob = method.invoke(bean);
                                        if (ob != null) {
                                            node.addOnlyOneProperty(key, method.invoke(bean).toString());
                                        } else {
                                            node.addOnlyOneProperty(key, null);
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Не удалось заполнить поле " + key);
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isExcludeMethod(String nameMethod,List<String> excludeMethod){
        for(String exM:excludeMethod){
            if(exM.equals(nameMethod)){
                return true;
            }
        }
        return false;
    }

    private boolean isExcludedField(String nameField, List<Field> allField){
        for(Field field:allField){
            if(field.getName().equals(nameField)){
                return false;
            }
        }
        return true;
    }

    private boolean isExcludeField(String nameField,List<String> excludeField){
        for(String exF:excludeField){
            if(exF.equals(nameField)){
                return true;
            }
        }
        return false;
    }

    private Field getField(List<Field> fields, String fieldN)  {
        for(Field field:fields){
            if(field.getName().equals(fieldN)){
                return field;
            }
        }

        return null;
    }


    private List<Field> getFields(Class clazz)  {
        List<Field> fields = new ArrayList<Field>();

        if(clazz.isAnnotationPresent(IncludeSuperclass.class)){
            Class superClass = clazz.getSuperclass();
            if (superClass!=null) {
                fields.addAll(getFields(superClass));
            }
        }
        for(Field f:clazz.getDeclaredFields()) {
            fields.add(f);
        }

        return fields;
    }

    private List<Method> getMethods(Class clazz){
        List<Method> methods = new ArrayList<Method>();

        if(clazz.isAnnotationPresent(IncludeSuperclass.class)){
            Class superClass = clazz.getSuperclass();
            if (superClass!=null) {
                methods.addAll(getMethods(superClass));
            }
        }
        for(Method m:clazz.getDeclaredMethods()) {
            methods.add(m);
        }

        return methods;
    }



//    protected void callAllGet(boolean includeSuperClass, List<String> excludeMethod, List<String> excludeField){
//        if(node.getId()==null) return;
//        Object bean=this;
//        Class klass = bean.getClass();
//
//        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
//
//        if (klass.getClassLoader() == null) {
//            includeSuperClass = false;
//        }
//
//        Method[] methods = (includeSuperClass) ?
//                klass.getMethods() : klass.getDeclaredMethods();
//        for (int i = 0; i < methods.length; i += 1) {
//            try {
//                Method method = methods[i];
//                if(excludeMethod==null | (excludeMethod!=null && !isExcludeMethod(method.getName(),excludeMethod))){
//                    if (Modifier.isPublic(method.getModifiers())) {
//                        String name = method.getName();
//                        String key = "";
//                        if (name.startsWith("get")) {
//                            key = name.substring(3);
//                        } else if (name.startsWith("is")) {
//                            key = name.substring(2);
//                        }
//                        String keyU = key;
//                        if (key.length() > 0 &&
//                                Character.isUpperCase(key.charAt(0)) &&
//                                method.getParameterTypes().length == 0) {
//                            if (key.length() == 1) {
//                                key = key.toLowerCase();
//                            } else if (!Character.isUpperCase(key.charAt(1))) {
//                                key = key.substring(0, 1).toLowerCase() +
//                                        key.substring(1);
//                            }
//                            Field f = klass.getDeclaredField(key);
//                            if(excludeField==null | (excludeField!=null && !isExcludeField(f.getName(),excludeField))) {
//                                try {
//                                    Method mset = bean.getClass().getMethod("set" + keyU, method.getReturnType());
//                                    List<NodeProperty> lnp = node.getPropertysValue(node, key);
//                                    if (lnp != null && lnp.size() > 0) {
//                                        String value = lnp.get(0).getValue();
//                                        if (method.getReturnType().equals(Boolean.class)) {
//                                            if (value != null) {
//                                                mset.invoke(bean, value.equals("true"));
//                                            }
//                                        } else {
//                                            mset.invoke(bean, value);
//                                        }
//                                    }
//
//                                } catch (NoSuchMethodException e) {
//                                    //такого сеттера не существует
//                                } catch (SecurityException e) {
//                                    //метод закрыт и мы его инжектим
//                                } catch (IllegalArgumentException e) {
//                                    //неопределенная инжекция
//                                }
//                            }
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }


//    public void fillObject(boolean includeSuperClass, List<String> excludeMethod, List<String> excludeField) {
//        Object bean=this;
//        Class klass = bean.getClass();
//
//        /* If klass.getSuperClass is System class then force includeSuperClass to false. */
//
//        if (klass.getClassLoader() == null) {
//            includeSuperClass = false;
//        }
//
//        Method[] methods = (includeSuperClass) ?
//                klass.getMethods() : klass.getDeclaredMethods();
//        for (int i = 0; i < methods.length; i += 1) {
//            try {
//                Method method = methods[i];
//                if(excludeMethod==null | (excludeMethod!=null && !isExcludeMethod(method.getName(),excludeMethod))){
//                    if (Modifier.isPublic(method.getModifiers())) {
//                        String name = method.getName();
//                        String key = "";
//                        if (name.startsWith("get")) {
//                            key = name.substring(3);
//                        } else if (name.startsWith("is")) {
//                            key = name.substring(2);
//                        }
//                        String keyU= key;
//                        if (key.length() > 0 &&
//                                Character.isUpperCase(key.charAt(0)) &&
//                                method.getParameterTypes().length == 0) {
//                            if (key.length() == 1) {
//                                key = key.toLowerCase();
//                            } else if (!Character.isUpperCase(key.charAt(1))) {
//                                key = key.substring(0, 1).toLowerCase() +
//                                        key.substring(1);
//                            }
//                            Field f=klass.getDeclaredField(key);
//                            if(excludeField==null | (excludeField!=null && !isExcludeField(f.getName(),excludeField))) {
//                                //method.invoke(bean);
//                                try {
//                                    Object ob = method.invoke(bean);
//                                    if (ob != null) {
//                                        node.addOnlyOneProperty(key, method.invoke(bean).toString());
//                                    } else {
//                                        node.addOnlyOneProperty(key, null);
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Не удалось заполнить поле " + key);
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}



