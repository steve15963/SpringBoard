package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Slf4j
public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
