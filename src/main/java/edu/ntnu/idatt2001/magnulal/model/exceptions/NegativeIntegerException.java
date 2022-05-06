package edu.ntnu.idatt2001.magnulal.model.exceptions;

/**
 * Custom subclass of RuntimeException with the name 'NegativeIntegerException' describing the exceptional event
 * @author magnulal
 * @version 0.3
 * @since 0.3
 */
public class NegativeIntegerException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param exceptionMessage the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NegativeIntegerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
