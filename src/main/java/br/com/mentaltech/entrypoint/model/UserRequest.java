package br.com.mentaltech.entrypoint.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
public class UserRequest {
    private String name;
    private String surname;

    @Past
    private Date birthday;
    private int age;
    private String phone;

    @Email
    private String email;
    private String loginUserName;
    private String password;
    private String profile;
    private String profession;
    private String profileDescrition;
    private List<String> symptoms;
}