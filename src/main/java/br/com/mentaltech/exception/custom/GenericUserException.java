package br.com.mentaltech.exception.custom;

public class GenericUserException extends RuntimeException {
    public GenericUserException(String message) {
        super(message);
    }
}