package br.com.exemplo.testai.config.exceptions;

public class CustomHasDependentRegistersException extends RuntimeException {
    public CustomHasDependentRegistersException(String message) {
        super(message);
    }
}