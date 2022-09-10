package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentRequestNotEqualsException extends RuntimeException{
    public ContentRequestNotEqualsException(String message) {
        super(message);
        log.error(message);
    }
}
