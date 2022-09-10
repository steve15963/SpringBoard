package shop.comuser.boardforremarkerble.CONTROLLER.API.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginValue {
    LOGOUT(0,"로그아웃"),
    LOGIN(1,"로그인");
    private int id;
    private String description;
}
