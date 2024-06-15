package com.questifyHub.app.Exceptions;

/**
 * Excessão personalizada para E-mails inválidos
 * 
 */
public class InvalidEmailException extends RuntimeException {

    /**
     * Constructor da excessão
     * 
     * @param message
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}
