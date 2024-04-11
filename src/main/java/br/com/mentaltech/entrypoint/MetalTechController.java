package br.com.mentaltech.entrypoint;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.entrypoint.model.UserRequest;
import br.com.mentaltech.entrypoint.model.UserResponse;
import br.com.mentaltech.usecase.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Controller
public class MetalTechController {

    @Autowired
    private LoginUseCase loginUseCase;

    @GetMapping("/login")
    public UserResponse login(@RequestBody @Valid UserRequest userRequest) {
        String userName = userRequest.getEmail();
        String password = userRequest.getPassword();

        //SE username é vazio OU password é vazio
        if(userName.isEmpty() || password.isEmpty())
            throw new RuntimeException("O usuario não passou a senha ou o nome de usuario");
        return loginUseCase.login(userRequest);
    }


    @GetMapping("/createUser")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
         return loginUseCase.createUser(userRequest);
    }

    /**
     curl -X GET  "http://localhost:8080/login" \
     -H "Content-Type: application/json" \
     -d '{
     "name" : "",
     "surname" : "ADAILDO",
     "birthday" : "",
     "age" : 0,
     "phone" : "",
     "email" : "ADAILDO_ADAILDO@MENTALTECH.COM.BR",
     "loginUserName" : "",
     "password" : "1234",
     "profile" : "",
     "profession" : "",
     "profileDescrition" : "",
     "symptoms" : [ ]
     }'
     *
     */
}