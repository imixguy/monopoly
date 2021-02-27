package by.imix.cms.material;

import by.imix.cms.material.parsetonode.INodeExt;

/**
 * Created by miha on 28.08.2014.
 * Интерфейс для обязательного Содержимого страницы
 */
interface Content extends INodeExt {

    //имя страницы
    String getName();
    void setName(String name);

    //содержимое страницы
    String getContent();
    void setContent(String content);

//    String getContent();
//    void setContent(String content);

    boolean isPastRoot();
    void setPastRoot(boolean pastRoot);
}
