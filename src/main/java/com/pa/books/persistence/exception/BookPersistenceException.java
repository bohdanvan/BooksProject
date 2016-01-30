package com.pa.books.persistence.exception;

/**
 * @author apo
 */
public class BookPersistenceException extends PersistenceException {

    public BookPersistenceException() {}

    public BookPersistenceException(String message) {
        super(message);
    }

    public BookPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookPersistenceException(Throwable cause) {
        super(cause);
    }


}
