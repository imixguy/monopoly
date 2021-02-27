package by.imix.cms.material;

/**
 * Created by miha on 04.09.2014.
 * Класс содержащий в себе информацию на основе проперти для дополнительного контента. Например блоков.
 */
public interface AdditContent extends Content{
    String getListURL();
    void setListURL(String listURL);
}
