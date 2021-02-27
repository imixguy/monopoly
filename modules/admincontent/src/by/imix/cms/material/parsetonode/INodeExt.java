package by.imix.cms.material.parsetonode;

import java.util.List;

/**
 * Created by miha on 10.09.2014.
 */
public interface INodeExt {
    String getType();

    void pastAllData(boolean includeSuperClass);
    void pastAllData(boolean includeSuperClass, List<String> methodsExclude, List<String> fieldsExclude);

    void fillObject();
    void fillObject(boolean includeSuperClass);
    void fillObject(boolean includeSuperClass, List<String> methodsExclude, List<String> fieldsExclude);

}
