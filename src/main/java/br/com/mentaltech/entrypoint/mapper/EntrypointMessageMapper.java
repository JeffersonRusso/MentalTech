package br.com.mentaltech.entrypoint.mapper;

import br.com.mentaltech.entrypoint.model.message.MessageRequest;
import br.com.mentaltech.entrypoint.model.message.MessageResponse;
import br.com.mentaltech.entrypoint.model.user.UserData;
import br.com.mentaltech.usecase.domain.MessageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntrypointMessageMapper {

    public Page<MessageResponse> toResponse(Page<MessageDomain> pageMessages) {
        List<MessageResponse> messageResponseList = new ArrayList<>();
        pageMessages.stream().forEach(messagePage ->
                messageResponseList.add(
                        MessageResponse
                                .builder()
                                .id(messagePage.getId())
                                .creatorId(messagePage.getCreatorId())
                                .title(messagePage.getTitle())
                                .text(messagePage.getText())
                                .creatorName(messagePage.getCreatorName())
                                .creationDate(messagePage.getCreationDate())
                                .build()
        ));
        return new PageImpl<>(messageResponseList, pageMessages.getPageable(), pageMessages.getTotalElements());
    }


    public List<MessageResponse> toResponse(List<MessageDomain> pageMessages) {
        List<MessageResponse> messageResponseList = new ArrayList<>();
        pageMessages.forEach(messagePage ->
                messageResponseList.add(
                        MessageResponse
                                .builder()
                                .id(messagePage.getId())
                                .creatorId(messagePage.getCreatorId())
                                .title(messagePage.getTitle())
                                .text(messagePage.getText())
                                .creatorName(messagePage.getCreatorName())
                                .creationDate(messagePage.getCreationDate())
                                .build()
                ));
        return messageResponseList;
    }


    public MessageDomain toDomain(MessageRequest message, UserData userDate) {

        return MessageDomain
                .builder()
                .id(message.getId())
                .creatorId((long) userDate.getId())
                .title(message.getTitle())
                .text(message.getText())
                .creationDate(LocalDate.now())
                .build();
    }

    public MessageDomain toDomain(Long idMessage, UserData userDate) {

        return MessageDomain
                .builder()
                .id(idMessage)
                .creatorId((long) userDate.getId())
                .build();
    }
}