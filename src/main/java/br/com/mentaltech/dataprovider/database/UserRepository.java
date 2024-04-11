package br.com.mentaltech.dataprovider.database;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByEmailAndPassword(String loginUserName, String password);
}

//INSERT INTO USER (NAME, PASSWORD) VALUES ("JEFFERSON", "123456789")
//SELECT * FROM USER WHERE NOME = "JEFFERSON" AND PASSOWRD = "123456789";