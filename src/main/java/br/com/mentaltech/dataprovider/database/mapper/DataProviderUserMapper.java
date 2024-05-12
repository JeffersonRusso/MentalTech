package br.com.mentaltech.dataprovider.database.mapper;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.usecase.domain.UserDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DataProviderUserMapper {

    public UserDomain toDomain(UserEntity user) {
        int age = (int) ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());

        return UserDomain.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday())
                .age(age)
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

    public UserEntity toEntity(UserDomain user) {
        return UserEntity.builder()
                .loginUserName(user.getLoginUserName())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .profile(user.getProfile())
                .profession(user.getProfession())
                .profileDescrition(user.getProfileDescrition())
                .symptoms(user.getSymptoms())
                .build();
    }
}