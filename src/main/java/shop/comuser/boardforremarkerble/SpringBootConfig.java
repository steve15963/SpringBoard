package shop.comuser.boardforremarkerble;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SpringBootConfig {
    public String ServeName = "Study Server";
    public int SessionTimeOut = 10;
}
