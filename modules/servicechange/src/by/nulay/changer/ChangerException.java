package by.nulay.changer;

import java.io.IOException;

/**
 * Created by miha on 23.01.2016.
 */
public class ChangerException extends RuntimeException{

    public ChangerException() {
    }

    public ChangerException(String s) {
        super(s);
    }

    public ChangerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ChangerException(Throwable throwable) {
        super(throwable);
    }

    public ChangerException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
