package com.pa.books.persistence.exception;

/**
 * @author apo
 */
public class BookExistsPersistenceException extends BookPersistenceException {

    public BookExistsPersistenceException() {
    }

    public BookExistsPersistenceException(String message) {
        super(message);
    }

    public BookExistsPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookExistsPersistenceException(Throwable cause) {
        super(cause);
    }
}
