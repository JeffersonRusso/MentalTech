package br.com.mentaltech.dataprovider.database;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.dataprovider.database.mapper.DataProviderUserMapper;
import br.com.mentaltech.dataprovider.database.repository.UserRepository;
import br.com.mentaltech.exception.custom.UserNotFound;
import br.com.mentaltech.usecase.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataProviderUserMapper dataProviderUserMapper;

    public UserDomain login(final String email, final String password ) {
        List<UserEntity> userEntities = userRepository.findByEmailAndPassword(email, password);
        if(userEntities.isEmpty())
            throw new UserNotFound(email);
        return dataProviderUserMapper.toDomain(userEntities.getFirst());
    }

    public UserDomain signUp(final UserDomain userDomain) {
        UserEntity entity = dataProviderUserMapper.toEntity(userDomain);
        UserEntity save = userRepository.save(entity);
        return dataProviderUserMapper.toDomain(save);
    }
}