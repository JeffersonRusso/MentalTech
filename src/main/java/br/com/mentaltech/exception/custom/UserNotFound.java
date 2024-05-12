package br.com.mentaltech.exception.custom;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String userName) {
        super("Usuario ou senha está incorreto");
    }
}
