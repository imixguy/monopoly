package rw.ktc.gktc.book;

import java.util.List;

/**
 * Created by miha on 04.12.2014.
 */
public interface RecrdsService{
    //1 запись напоминания
    Record saveRecord(Record record);
    //2 чтение напонинаний за * срок
    List<Record> getAllRecord();
    //    List<Record> getAllRecord(Record record,Book book);
    //3 изменения напоминания получение записи
    Record getRecordById(Long id);
    //4 удалить напоминаня
    boolean removeRecordById(Long id);
    boolean removeRecord(Record record);
}
