package br.com.mentaltech.exception.custom;

public class SaveMessageException extends RuntimeException {
    public SaveMessageException(String message) {
        super(message);
    }
}
