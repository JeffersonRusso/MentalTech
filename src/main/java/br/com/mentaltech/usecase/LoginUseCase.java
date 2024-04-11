package br.com.mentaltech.usecase;

import br.com.mentaltech.dataprovider.database.UserDataProvider;
import br.com.mentaltech.entrypoint.model.UserRequest;
import br.com.mentaltech.entrypoint.model.UserResponse;
import br.com.mentaltech.usecase.domain.UserDomain;
import br.com.mentaltech.usecase.mapper.UseCaseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginUseCase {

    @Autowired
    private UserDataProvider dataProvider;

    @Autowired
    private UseCaseUserMapper useCaseUserMapper;

    public UserResponse login(UserRequest userRequest) {

        //peguei o loginUserName do User (User Controller)
        String email = userRequest.getEmail();
        //peguei o password do User (User Controller)
        String password = userRequest.getPassword();
        UserDomain userDomain = dataProvider.login(email, password);
        return useCaseUserMapper.toMapper(userDomain);
    }

    public UserResponse createUser(UserRequest userRequest) {
        return null;
    }
}