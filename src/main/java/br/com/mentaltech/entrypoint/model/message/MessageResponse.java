package br.com.mentaltech.entrypoint.model.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class MessageResponse implements Serializable {

    private Long id;
    private Long creatorId;
    private String title;
    private String text;
    private String creatorName;
    private LocalDate creationDate;
}
