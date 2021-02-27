package by.imix.image;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 03.12.13
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public class ImageException extends RuntimeException{
    public ImageException() {
    }

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageException(Throwable cause) {
        super(cause);
    }
}
