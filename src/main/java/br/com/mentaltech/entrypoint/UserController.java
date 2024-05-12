package br.com.mentaltech.entrypoint;

import br.com.mentaltech.entrypoint.mapper.EntrypointUserMapper;
import br.com.mentaltech.entrypoint.model.user.UserRequest;
import br.com.mentaltech.entrypoint.model.user.UserResponse;
import br.com.mentaltech.exception.custom.GenericUserException;
import br.com.mentaltech.exception.custom.UserNotFound;
import br.com.mentaltech.security.JwtUtil;
import br.com.mentaltech.usecase.LoginUseCase;
import br.com.mentaltech.usecase.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/user")
@RestController
@Controller
@Slf4j
@AllArgsConstructor
public class UserController {

    @Autowired
    private LoginUseCase loginUseCase;
    @Autowired
    private EntrypointUserMapper entrypointUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestParam("email") String email,
                                              @RequestParam String password) {
        try {
            if (email.isEmpty() || password.isEmpty())
                throw new RuntimeException("O usuario não passou a senha ou o nome de usuario");

            UserDomain userDomain = entrypointUserMapper.toDomain(email, password);
            UserDomain userLogin = loginUseCase.login(userDomain);
            UserResponse userResponse = entrypointUserMapper.toResponse(userLogin);

            String token = jwtUtil.createToken(userResponse);
            userResponse.setToken(token);

            return ResponseEntity.ok(userResponse);
        } catch (UserNotFound ex) {
            throw ex;
        } catch (Exception ex) {
            throw new GenericUserException("Não foi possivel realizar o login. Motivo: "
                    + ex);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/signUp")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        try {
            UserDomain userDomainRequest = entrypointUserMapper.toDomain(userRequest);
            UserDomain userDomainResponse = loginUseCase.signUp(userDomainRequest);
            log.info("Usuario encontrado. Email: {}", userDomainResponse.getEmail());
            return entrypointUserMapper.toResponse(userDomainResponse);
        } catch (Exception ex) {
            throw new GenericUserException("Não foi possivel processar o cadastramento. Motivo: "
                    + ex);
        }
    }
}