package br.com.mentaltech.usecase.mapper;

import br.com.mentaltech.entrypoint.model.user.UserResponse;
import br.com.mentaltech.usecase.domain.UserDomain;
import org.springframework.stereotype.Component;

@Component
public class UseCaseUserMapper {

    public UserResponse toResponse(UserDomain user) {
        return UserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .phone(user.getPhone())
                .email(user.getEmail())
                .loginUserName(user.getLoginUserName())
                .password(user.getPassword())
                .profile(user.getProfile())
                .profession(user.getProfession())
                .profileDescrition(user.getProfileDescrition())
                .symptoms(user.getSymptoms())
                .build();
    }
}