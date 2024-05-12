package br.com.mentaltech.usecase.mapper;

import br.com.mentaltech.dataprovider.database.entity.MessageEntity;
import br.com.mentaltech.usecase.domain.MessageDomain;
import org.springframework.stereotype.Component;

@Component
public class UseCaseMessageMapper {

    public MessageDomain toDomain(MessageEntity messsage) {
        return MessageDomain.builder()
                .id(messsage.getId())
                .text(messsage.getText())
                .title(messsage.getTitle())
                .creationDate(messsage.getCreationDate())
                .build();
    }
}
