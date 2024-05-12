package br.com.mentaltech.dataprovider.database.repository;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByEmailAndPassword(String email, String password);
    List<UserEntity> findByloginUserName(String loginUserName);
}