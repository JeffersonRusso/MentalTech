     create table message_entity (
        id bigint not null,
        creation_date date,
        text varchar(255),
        title varchar(255),
        user_id bigint,
        primary key (id)
    );

    create table user_entity (
        id bigint not null,
        age integer,
        birthday timestamp(6),
        email varchar(255),
        login_user_name varchar(255),
        name varchar(255),
        password varchar(255),
        phone varchar(255),
        profession varchar(255),
        profile varchar(255),
        profile_descrition varchar(255),
        surname varchar(255),
        symptoms varchar(255),
        primary key (id)
    );

    alter table if exists user_entity
       drop constraint if exists UK_4xad1enskw4j1t2866f7sodrx;

    alter table if exists user_entity
       add constraint UK_4xad1enskw4j1t2866f7sodrx unique (email);

    alter table if exists user_entity
       drop constraint if exists UK_93nbowuw0y3jwrcbw9yvykfa5;

    alter table if exists user_entity
       add constraint UK_93nbowuw0y3jwrcbw9yvykfa5 unique (login_user_name);

    create sequence message_entity_seq start with 1 increment by 50;

    create sequence user_entity_seq start with 1 increment by 50;

    alter table if exists message_entity
       add constraint FKh99srviwhgfebrkxuutrwdtht
       foreign key (user_id)
       references user_entity;


      -- insert into