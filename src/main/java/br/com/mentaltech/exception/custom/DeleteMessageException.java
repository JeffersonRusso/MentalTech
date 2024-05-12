package br.com.mentaltech.exception.custom;

public class DeleteMessageException extends RuntimeException {
    public DeleteMessageException(String message) {
        super(message);
    }
}
