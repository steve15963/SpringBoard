package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundBoardException extends RuntimeException{
    public NotFoundBoardException(String message) {
        super(message);
        log.error(message);
    }
}
