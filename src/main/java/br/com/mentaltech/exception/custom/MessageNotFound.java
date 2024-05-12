package br.com.mentaltech.exception.custom;

public class MessageNotFound extends RuntimeException {
    public MessageNotFound(String message) {
        super(message);
    }
}