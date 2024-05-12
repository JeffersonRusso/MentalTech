package br.com.mentaltech.usecase.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserDomain {
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
}