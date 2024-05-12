package br.com.mentaltech.exception;

import br.com.mentaltech.exception.custom.DeleteMessageException;
import br.com.mentaltech.exception.custom.GenericUserException;
import br.com.mentaltech.exception.custom.UpdateMessage;
import br.com.mentaltech.exception.custom.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Autowired
    private MappingJackson2HttpMessageConverter mapping;

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class, Exception.class, RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex,
            WebRequest request) throws Exception {
        return errorToJson(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = { UserNotFound.class })
    protected ResponseEntity<Object> userNotFound(
            RuntimeException ex,
            WebRequest request) {
        return errorToJson(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = { GenericUserException.class })
    protected ResponseEntity<Object> genericUserException(
            RuntimeException ex,
            WebRequest request) {
        return errorToJson(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = { DeleteMessageException.class })
    protected ResponseEntity<Object> deleteMessageException(
            RuntimeException ex,
            WebRequest request) {
        return errorToJson(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = { UpdateMessage.class })
    protected ResponseEntity<Object> updateMessage(
            RuntimeException ex,
            WebRequest request) {
        return errorToJson(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> errorToJson(Exception ex, HttpStatus httpStatus) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        errorMap.put("time", LocalDateTime.now().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Object responseBody = mapping.getObjectMapper().convertValue(errorMap, Object.class);

        return new ResponseEntity<>(responseBody, headers, httpStatus);
    }
}