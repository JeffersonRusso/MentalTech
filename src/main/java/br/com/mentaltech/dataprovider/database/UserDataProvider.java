package br.com.mentaltech.dataprovider.database;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.dataprovider.database.mapper.DataProviderUserMapper;
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
            throw new RuntimeException("Usuario não existe");
        return dataProviderUserMapper.toMapper(userEntities.get(0));
    }
}