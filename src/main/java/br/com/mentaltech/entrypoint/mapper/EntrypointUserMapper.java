package br.com.mentaltech.entrypoint.mapper;

import br.com.mentaltech.entrypoint.model.user.UserData;
import br.com.mentaltech.entrypoint.model.user.UserRequest;
import br.com.mentaltech.entrypoint.model.user.UserResponse;
import br.com.mentaltech.usecase.domain.UserDomain;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class EntrypointUserMapper {

    public UserDomain toDomain(UserRequest user) {
        return UserDomain.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .age(LocalDate.now().getYear() - user.getBirthday().getYear())
                .loginUserName(user.getLoginUserName())
                .password(user.getPassword())
                .profile(user.getProfile())
                .profession(user.getProfession())
                .profileDescrition(user.getProfileDescrition())
                .symptoms(symptomsMapper(user.getSymptoms()))
                .build();
    }

    public UserDomain toDomain(String email, String password) {
        return UserDomain.builder()
                .email(email)
                .password(password)
                .build();
    }

    private String symptomsMapper(List<String> symptoms) {
        if (Objects.isNull(symptoms))
            return null;
        StringBuilder symptomsString = new StringBuilder();
        symptoms.forEach(s -> {
            symptomsString.append(s);
            symptomsString.append(", ");
        });
        return symptomsString.toString();
    }

    public UserResponse toResponse(UserDomain user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .phone(user.getPhone())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .loginUserName(user.getLoginUserName())
                .password(user.getPassword())
                .profile(user.getProfile())
                .profession(user.getProfession())
                .profileDescrition(user.getProfileDescrition())
                .symptoms(user.getSymptoms())
                .build();
    }

    public UserData toUserDate(Claims userData) throws JsonProcessingException {
        return new UserData((Integer) userData.get("id"));
    }
}