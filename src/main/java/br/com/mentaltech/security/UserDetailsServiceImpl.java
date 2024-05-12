package br.com.mentaltech.security;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.dataprovider.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                log.info("O usuario que está tentando realizar o login é {}", userName);
                List<UserEntity> userEntity = userRepository.findByloginUserName(userName);

                if(Objects.isNull(userEntity) || userEntity.isEmpty())
                    throw new RuntimeException("Usuario Não encontrado");

                return org.springframework.security.core.userdetails.User.builder()
                        .username(userEntity.getFirst().getLoginUserName())
                        .password(userEntity.getFirst().getPassword())
                        .roles("USER")
                        .build();
            }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
