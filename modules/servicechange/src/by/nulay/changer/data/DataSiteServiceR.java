package by.nulay.changer.data;

import org.springframework.stereotype.Service;

/**
 * Created by miha on 17.11.2015.
 */
@Service("DataSiteService")
public class DataSiteServiceR extends DataSiteServiceHib<DataSite,Long> implements DataSiteService<DataSite,Long> {
}
