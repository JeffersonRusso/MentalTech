package br.com.mentaltech.dataprovider.database.mapper;

import br.com.mentaltech.dataprovider.database.entity.MessageEntity;
import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import br.com.mentaltech.usecase.domain.MessageDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataProviderMessageMapper {

    public static MessageDomain toDomain(MessageEntity messsage) {
        return MessageDomain.builder()
                .id(messsage.getId())
                .text(messsage.getText())
                .title(messsage.getTitle())
                .creatorId(messsage.getUser().getId())
                .creatorName(messsage.getUser().getName())
                .creationDate(messsage.getCreationDate())
                .build();
    }

    public static MessageEntity toEntity(MessageDomain message) {

        UserEntity userEntity = UserEntity
                .builder()
                .id(message.getCreatorId())
                .build();

        return MessageEntity.builder()
                .id(message.getId())
                .text(message.getText())
                .title(message.getTitle())
                .user(userEntity)
                .creationDate(LocalDate.now())
                .build();
    }

}
