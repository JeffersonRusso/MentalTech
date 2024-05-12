package br.com.mentaltech.entrypoint;

import br.com.mentaltech.entrypoint.mapper.EntrypointMessageMapper;
import br.com.mentaltech.entrypoint.mapper.EntrypointUserMapper;
import br.com.mentaltech.entrypoint.model.message.MessageRequest;
import br.com.mentaltech.entrypoint.model.message.MessageResponse;
import br.com.mentaltech.entrypoint.model.user.UserData;
import br.com.mentaltech.exception.custom.DeleteMessageException;
import br.com.mentaltech.security.JwtUtil;
import br.com.mentaltech.usecase.MessageUseCase;
import br.com.mentaltech.usecase.domain.MessageDomain;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/message")
@RestController
@Controller
@Slf4j
@AllArgsConstructor
public class MessageController {

    @Autowired
    private MessageUseCase messageUseCase;
    @Autowired
    private EntrypointMessageMapper entrypointMessageMapper;
    @Autowired
    private EntrypointUserMapper entrypointUserMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAll")
    public List<MessageResponse> getAllMessages(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        List<MessageDomain> allMessages = messageUseCase.findAllMessages(pageNumber, pageSize);
        return entrypointMessageMapper.toResponse(allMessages);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllByUserId")
    public List<MessageResponse> getAllMessagesByUserId(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                        @RequestHeader("Authorization") String headerValue) throws JsonProcessingException {
        Claims userDataClaims = jwtUtil.getUserDate(headerValue);
        UserData userDate = entrypointUserMapper.toUserDate(userDataClaims);

        List<MessageDomain> allMessages = messageUseCase.findAllMessages(pageNumber, pageSize, (long) userDate.getId());
        log.info("Mensagens retornadas do usuario: {}", allMessages);
        return entrypointMessageMapper.toResponse(allMessages);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/postMessage")
    public ResponseEntity postMessage(@RequestBody MessageRequest message,
                                      @RequestHeader("Authorization") String headerValue) {
        try {
            Claims userDataClaims = jwtUtil.getUserDate(headerValue);
            UserData userDate = entrypointUserMapper.toUserDate(userDataClaims);

            MessageDomain messageDomain = entrypointMessageMapper.toDomain(message, userDate);
            messageUseCase.save(messageDomain);
            return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Não foi possivel salvar a menssagem, motivo: " + ex.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteMessage")
    public void deleteMessage(@RequestParam Long idMessage,
                              @RequestHeader("Authorization") String headerValue) {
        try {
            Claims userDataClaims = jwtUtil.getUserDate(headerValue);
            UserData userDate = entrypointUserMapper.toUserDate(userDataClaims);

            MessageDomain messageDomain = entrypointMessageMapper.toDomain(idMessage, userDate);
            messageUseCase.delete(messageDomain);
        } catch (RuntimeException ex) {
            throw new DeleteMessageException("Não foi possivel deletar a menssagem, motivo: " + ex);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/putMessage")
    public void putMessage(@RequestBody MessageRequest message,
                           @RequestHeader("Authorization") String headerValue) {
        try {
            Claims userDataClaims = jwtUtil.getUserDate(headerValue);
            UserData userDate = entrypointUserMapper.toUserDate(userDataClaims);

            MessageDomain messageDomain = entrypointMessageMapper.toDomain(message, userDate);
            messageUseCase.put(messageDomain);
        } catch (RuntimeException ex) {
            throw new DeleteMessageException("Não foi possivel atualizar a menssagem, motivo: " + ex);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
