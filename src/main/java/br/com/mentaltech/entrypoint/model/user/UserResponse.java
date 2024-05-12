package br.com.mentaltech.entrypoint.model.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private int age;
    private String phone;
    private String email;
    private String loginUserName;
    private String password;
    private String profile;
    private String profession;
    private String profileDescrition;
    private String symptoms;
    private String token;
}