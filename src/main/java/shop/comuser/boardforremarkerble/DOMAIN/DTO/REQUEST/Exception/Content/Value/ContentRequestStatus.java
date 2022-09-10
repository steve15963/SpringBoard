package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentRequestStatus {
    OK(0,"정상"),
    NOT_EQUALS_TITLE(1,"타이틀이 같지 않습니다."),
    NOT_EQUALS_CONTENT(2,"컨텐트 내용이 같지 않습니다."),
    NOT_EQUALS_CREATER(3,"글 작성자가 다릅니다."),


    NOT_FOUND_CONTENT(101,"컨텐트를 찾지 못했습니다."),


    LOGOUT_SESSION_TRY_MODIFY(201,"로그인 하지 않은 사용자가 수정을 시도합니다.");
    long id;
    String status;
}
