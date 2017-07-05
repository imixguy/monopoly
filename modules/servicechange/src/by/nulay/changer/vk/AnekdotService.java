package by.nulay.changer.vk;

import java.util.Date;
import java.util.List;

/**
 * Created by miha on 24.07.2014.
 */
public interface AnekdotService<T extends Anekdot,ID extends Number> {

    void addAnekdot(T anekdot);

    boolean isAddAnekdot(T anekdot);

    T getNextAnekdot();

    T getNoWriteAnekdot();

    List<T> getNextAnekdotL(Date date);

    boolean saveAnekdot(T a);

    T getNextAnekdotOK();

    boolean checkedAnek(String anekdot);
}
