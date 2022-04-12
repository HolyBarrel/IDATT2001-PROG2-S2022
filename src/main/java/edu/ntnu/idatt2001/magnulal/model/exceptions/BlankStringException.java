package edu.ntnu.idatt2001.magnulal.model.exceptions;
//TODO: comment
public class BlankStringException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param exceptionMessage the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BlankStringException(String exceptionMessage) {
        super(exceptionMessage);
    }
}