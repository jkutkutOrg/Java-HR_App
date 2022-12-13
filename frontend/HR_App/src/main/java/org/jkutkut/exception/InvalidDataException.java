package org.jkutkut.exception;

import java.io.Serial;

/**
 * Custom class to handle exceptions
 */
public class InvalidDataException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDataException(String s) {
        super(s);
    }

    public InvalidDataException(String s, int min, int max) {
        super(String.format("%s [%d, %d]", s, min, max));
    }

    public InvalidDataException(String s, Object[] arr) {
        super(addArrayToStr(s, arr));
    }
    private static String addArrayToStr(String s, Object[] arr) {
        String arrStr = "{";
        if (arr != null && arr.length > 0) {
            arrStr += " " + arr[0];
            for (int i = 1; i < arr.length; i++) {
                arrStr += ", " + arr[i];
            }
        }
        arrStr += "}";

        return String.format(s, arrStr);
    }
}
