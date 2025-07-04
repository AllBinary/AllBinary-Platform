package java.util;

//J2SE to J2ME compatibility
public final class Objects {

    public static <T> T requireNonNull(final T value) {
        if (value == null) {
            throw new NullPointerException();
        } else {
            return value;
        }
    }

    public static <T> T requireNonNull(final T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }

}
