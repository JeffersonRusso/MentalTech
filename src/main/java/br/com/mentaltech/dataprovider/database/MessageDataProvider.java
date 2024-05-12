package br.com.mentaltech.dataprovider.database;

import br.com.mentaltech.dataprovider.database.entity.MessageEntity;
import br.com.mentaltech.dataprovider.database.mapper.DataProviderMessageMapper;
import br.com.mentaltech.dataprovider.database.mapper.DataProviderUserMapper;
import br.com.mentaltech.dataprovider.database.repository.MessageRepository;
import br.com.mentaltech.dataprovider.database.repository.UserRepository;
import br.com.mentaltech.exception.custom.MessageNotFound;
import br.com.mentaltech.usecase.domain.MessageDomain;
import br.com.mentaltech.utils.PageCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MessageDataProvider {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DataProviderMessageMapper dataProviderUserMapper;

    public List<MessageDomain> getPageableMessages(Integer pageNumber, Integer pageSize) {
        return messageRepository.findAll()
                .stream().map(DataProviderMessageMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<MessageDomain> getPageableMessages(Integer pageNumber, Integer pageSize, Long IdUser) {
        return messageRepository.findByUserId(IdUser)
                .stream().map(DataProviderMessageMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void save(MessageDomain message) {
        MessageEntity entity = DataProviderMessageMapper.toEntity(message);
        messageRepository.save(entity);
    }

    public void put(MessageDomain message) {
        MessageEntity entity = DataProviderMessageMapper.toEntity(message);
        messageRepository.save(entity);
    }

    public void delete(MessageDomain message) {
        messageRepository.findById(message.getId())
                .ifPresentOrElse(messageEntity -> {
                        log.info("MessageEntity: {} | MessageDomain: {}", messageEntity, message);
                        if(messageEntity.getUser().getId().equals(message.getCreatorId())) {
                            MessageEntity entity = DataProviderMessageMapper.toEntity(message);
                            messageRepository.delete(entity);
                            return;
                        }
                        throw new RuntimeException("A mensagem sendo deletada não perence ao usuario");
                        }
                        ,() -> { throw new MessageNotFound("Mensagem com id " + message.getId() + " não encontrada");
                });
    }
}