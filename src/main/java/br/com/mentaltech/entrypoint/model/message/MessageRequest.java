package br.com.mentaltech.entrypoint.model.message;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class MessageRequest {

    private Long id;
    private String text;
    private String title;
    private String creatorName;
    private LocalDate creationDate;

}
