package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Board.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardRequestStatus {
    OK(0,"정상"),

    NOT_FOUND_BOARD(1,"보드를 찾지 못했습니다.");
    long id;
    String status;
}
