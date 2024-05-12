package br.com.mentaltech.entrypoint.model.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserRequest {
    private Long id;
    private String name;
    private String surname;

    @Past
    private LocalDate birthday;
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