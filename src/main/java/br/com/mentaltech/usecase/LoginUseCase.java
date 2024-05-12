package br.com.mentaltech.usecase;

import br.com.mentaltech.dataprovider.database.UserDataProvider;
import br.com.mentaltech.usecase.domain.UserDomain;
import br.com.mentaltech.usecase.mapper.UseCaseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    @Autowired
    private UserDataProvider userDataProvider;

    public UserDomain login(UserDomain userRequest) {
        return userDataProvider.login(
                userRequest.getEmail(),
                userRequest.getPassword()
        );
    }

    public UserDomain signUp(UserDomain user) {
        return userDataProvider.signUp(user);
    }
}