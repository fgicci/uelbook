package edu.uel.uelbook.exception;

public class PersonExistsException extends Exception {

    public PersonExistsException() {
    }

    public PersonExistsException(String message) {
        super(message);
    }

    public PersonExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonExistsException(Throwable cause) {
        super(cause);
    }
}
