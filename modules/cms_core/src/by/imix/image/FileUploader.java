package by.imix.image;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.12.13
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class FileUploader implements FileUploaderIface {

    private String[] format={"image/jpeg","image/png","image/gif"};//Формат документов
    private Integer maxSize=500000;//размер в байтах

    @Override
    public String[] getFormat() {
        return format;
    }

    @Override
    public void setFormat(String[] format) {
        this.format = format;
    }

    @Override
    public Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    //проверяем существует ли такой файл, если да то добавляем к файлу номер
    @Override
    public String isFileIssue(String folder, String filename) {
        return isFileIssue(folder,filename,0);
    }
    //проверяем существует ли такой файл, если да то добавляем к файлу номер
    @Override
    public String isFileIssue(String folder, String filename, Integer num) {
        String newFN=filename;
        if(!num.equals(0)){
            newFN=filename.substring(0,filename.indexOf("."))+"_"+num+filename.substring(filename.indexOf("."), filename.length());
        }
        File file = new File(folder+File.separator + newFN);
        if(file.isFile()){
            return isFileIssue(folder,filename,num+1);
        }
        return newFN;
    }

    //Проверяем является ли файл картинкой, ворд или pdf документом

    @Override
    public void validateImage(MultipartFile image) {
        if(image.isEmpty()){
            throw new ImageException("File is empty");
        }
        if(image.getSize()>getMaxSize()){
            throw new ImageException("File size larger than "+maxSize+" are not allowed");
        }
        boolean key=true;
        for(String frm:format){
            if(image.getContentType().equals(frm)){
                key=false;
                break;
            }
        }
        if(key){
            throw new ImageException("File format not accepted");
        }
    }

    //Сохраняем аватарку пользователя
    @Override
    public void saveFile(String folder, String filename, MultipartFile image) throws RuntimeException {
        try {
            File file = new File(folder+File.separator + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new ImageException("Unable to save image", e);
        }
    }

    //получаем файл по имени без учета формата
    public File[] getListFileByName(String folder, final String userName){
        File[] fileL = new File(folder).listFiles(new FilenameFilter(){
            public boolean accept(File f, String name) {
                File file = new File(name);
                return file.getName().contains(userName+".");
            }
        });
        return fileL;
    }
}
