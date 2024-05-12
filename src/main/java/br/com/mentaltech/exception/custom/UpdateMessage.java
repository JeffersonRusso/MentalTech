package br.com.mentaltech.exception.custom;

public class UpdateMessage  extends RuntimeException {
    public UpdateMessage(String message) {
        super(message);
    }
}
