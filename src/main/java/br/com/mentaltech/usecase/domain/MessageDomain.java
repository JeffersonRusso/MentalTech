package br.com.mentaltech.usecase.domain;

import br.com.mentaltech.dataprovider.database.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MessageDomain {

    private Long id;
    private Long creatorId;
    private String title;
    private String text;
    private String creatorName;
    private LocalDate creationDate;
}
