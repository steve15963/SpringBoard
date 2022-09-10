package shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0,"사용상태","사용자 등록 상태"),
    BLOCK(1,"정지상태","관리자에 의하여 정지된 상태");
    private int id;
    private String title;
    private String description;
}
