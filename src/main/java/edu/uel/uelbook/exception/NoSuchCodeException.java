package edu.uel.uelbook.exception;

public class NoSuchCodeException extends Exception {

    public NoSuchCodeException() {
    }

    public NoSuchCodeException(String message) {
        super(message);
    }

    public NoSuchCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCodeException(Throwable cause) {
        super(cause);
    }
}
