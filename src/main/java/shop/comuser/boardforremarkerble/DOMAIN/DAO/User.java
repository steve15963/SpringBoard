package shop.comuser.boardforremarkerble.DOMAIN.DAO;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE.UserLevel;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE.UserStatus;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String account;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    @Enumerated(EnumType.ORDINAL)
    private UserLevel level;


}
