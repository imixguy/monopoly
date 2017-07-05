package rw.gcktc.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.12.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public interface FileUploaderIface {

    //получаем массив форматов допустимых файлов
    String[] getFormat();

    void setFormat(String[] format);

    //получаем максимальный размер файла
    Integer getMaxSize();

    void setMaxSize(Integer maxSize);

    //проверяем существует ли такой файл, если да то добавляем к файлу номер
    String isFileIssue(String folder, String filename);

    //проверяем существует ли такой файл, если да то добавляем к файлу номер
    String isFileIssue(String folder, String filename, Integer num);

    //проверяем размер и тип файла
    void validateImage(MultipartFile image);

    //Сохраняем аватарку пользователя
    void saveFile(String folder, String filename, MultipartFile image) throws RuntimeException;

    //получаем файл по имени без учета типа файла
    File[] getListFileByName(String folder, final String userName);
}
