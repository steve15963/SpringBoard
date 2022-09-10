package shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE.UserLevel;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE.UserStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String id;
    private String account;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;
    @Enumerated(EnumType.ORDINAL)
    private UserLevel level;

}
