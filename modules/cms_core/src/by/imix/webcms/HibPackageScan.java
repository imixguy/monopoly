package by.imix.webcms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miha on 24.10.2014.
 */
public class HibPackageScan{
    private List<String> listAll=new ArrayList<String>();

    public HibPackageScan(List<String>... list) {
        for(int i=0;i<list.length;i++){
            listAll.addAll(list[i]);
        }
    }

    public List<String> getListAll() {
        return listAll;
    }

    public void setListAll(List<String>... list) {
        for(int i=0;i<list.length;i++){
            listAll.addAll(list[i]);
        }
    }
}
