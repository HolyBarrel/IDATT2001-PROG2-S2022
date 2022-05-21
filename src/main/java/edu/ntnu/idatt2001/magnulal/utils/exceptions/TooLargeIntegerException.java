package edu.ntnu.idatt2001.magnulal.utils.exceptions;

/**
 * Custom subclass of RuntimeException with the name 'TooLargeIntegerException' describing the exceptional event
 * @author magnulal
 * @version 1.0
 * @since 0.3
 */
public class TooLargeIntegerException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param exceptionMessage the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TooLargeIntegerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
