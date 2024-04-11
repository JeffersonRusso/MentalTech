package br.com.mentaltech.dataprovider.database.mapper;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.usecase.domain.UserDomain;
import org.springframework.stereotype.Component;

@Component
public class DataProviderUserMapper {

    public UserDomain toMapper(UserEntity user) {
        return UserDomain.builder()
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
