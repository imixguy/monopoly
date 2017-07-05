package by.nulay.changer.data;

import java.util.Date;
import java.util.List;

/**
 * Created by miha on 17.11.2015.
 */
public interface DataSiteService<T extends DataSite,ID extends Number> {

    void addDataSite(T dataSite);

    boolean isAddDataSite(T dataSite);

    T getNextDataSite();

    T getNoWriteDataSite();

    List<T> getNextDataSiteL(Date date);

    boolean saveDataSite(T a);

    T getNextDataSiteOK();

    boolean checkedDataSite(String dataSite);
}
