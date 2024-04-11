package br.com.mentaltech.dataprovider.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class UserEntity implements Serializable {

    /**
     * create table user (age integer not null, birthday timestamp(6), id bigint generated by default as identity, email varchar(255), login_user_name varchar(255), name varchar(255), password varchar(255), phone varchar(255), profession varchar(255), profile varchar(255), profile_descrition varchar(255), surname varchar(255), primary key (id))" via JDBC [Syntax error in SQL statement "create table [*]user (age integer not null, birthday timestamp(6), id bigint generated by default as identity, email varchar(255), login_user_name varchar(255), name varchar(255), password varchar(255), phone varchar(255), profession varchar(255), profile varchar(255), profile_descrition varchar(255), surname varchar(255), primary key (id))"; expected "identifier";]
     * 	at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:94) ~[hibernate-core-6.4.4.Final.jar:6.4.4.Final]
     --INSERT INTO USER_ENTITY (ID, AGE, LOGIN_USER_NAME, PASSWORD, EMAIL) VALUES (1, 18,  'ADAILDO', '1234', 'ADAILDO_ADAILDO@MENTALTECH.COM.BR');
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Date birthday;
    private int age;
    private String phone;
    private String email;
    private String loginUserName;
    private String password;
    private String profile;
    private String profession;
    private String profileDescrition;
    private String symptoms;
}