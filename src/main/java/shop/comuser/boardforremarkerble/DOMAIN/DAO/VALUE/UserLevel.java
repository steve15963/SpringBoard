package shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserLevel {
    GUEST(0,"유저"),
    USER(1,"매니저"),
    MANAGER(2,"매니저"),
    MASTER(3,"서버관리자"),
    SUPERMASTER(4,"서버제작자");
    private int id;
    private String description;
}
