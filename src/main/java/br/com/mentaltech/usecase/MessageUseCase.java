package br.com.mentaltech.usecase;

import br.com.mentaltech.dataprovider.database.MessageDataProvider;
import br.com.mentaltech.usecase.domain.MessageDomain;
import br.com.mentaltech.usecase.mapper.UseCaseMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageUseCase {

    @Autowired
    private MessageDataProvider messageDataProvider;

    @Autowired
    private UseCaseMessageMapper useCaseMessageMapper;

    public List<MessageDomain> findAllMessages(Integer pageNumber, Integer pageSize) {
        return messageDataProvider.getPageableMessages(pageNumber, pageSize);
    }

    public List<MessageDomain> findAllMessages(Integer pageNumber, Integer pageSize, Long idUser) {
        return messageDataProvider.getPageableMessages(pageNumber, pageSize, idUser);
    }

    public void save(MessageDomain message) {
        messageDataProvider.save(message);
    }

    public void delete(MessageDomain message) {
        messageDataProvider.delete(message);
    }

    public void put(MessageDomain message) {
        messageDataProvider.put(message);
    }

}
