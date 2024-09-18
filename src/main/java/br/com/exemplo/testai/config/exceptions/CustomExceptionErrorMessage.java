package br.com.exemplo.testai.config.exceptions;

public class CustomExceptionErrorMessage extends RuntimeException {
    public CustomExceptionErrorMessage(String message) {
        super(message);
    }
}