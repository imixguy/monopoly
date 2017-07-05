package by.nulay.changer.task;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by miha on 22.11.2015.
 */
@Service("taskSaverOnTheFile")
public class TaskSaverOnTheFile implements TaskSaveAction {
    @Override
    public void taskSaveAction(String obj) {
        //C:\Users\miha\AppData\Roaming\Mozilla\Firefox\Profiles\hmji9ni2.autowork2\gm_scripts\facebookData
        File file = new File("C:"+File.separator+"Users"+File.separator+"miha"+File.separator+"AppData"+File.separator+"Roaming"+File.separator+"Mozilla"+File.separator+"Firefox"+File.separator+"Profiles"+File.separator+"hmji9ni2.autowork2"+File.separator+"gm_scripts"+File.separator+"facebookData"+File.separator+"facebookData.user.js");
        try {
            FileUtils.writeStringToFile(file,obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void taskSaveAction(String obj, String folderEx) {
        File file = new File(folderEx);
        try {
            FileUtils.writeStringToFile(file,obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
