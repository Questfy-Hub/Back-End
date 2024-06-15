package com.questifyHub.app.Exceptions;

/**
 * Excessão personalizada para
 * 
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructo vazio
     * 
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Constructor da excessão
     * 
     * @param message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor da excessão
     * 
     * @param message
     * @param cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor da excessão
     * 
     * @param cause
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
