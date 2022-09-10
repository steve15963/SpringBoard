package shop.comuser.boardforremarkerble.SERVICE;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shop.comuser.boardforremarkerble.CONTROLLER.API.Value.LoginValue;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.UserRequest;

import javax.servlet.http.HttpSession;

import static shop.comuser.boardforremarkerble.CONTROLLER.API.Value.LoginValue.*;

@Service
@Slf4j
public class LoginService {
    public boolean isLogined(HttpSession session){
        LoginValue login = (LoginValue) session.getAttribute("login");
        User user = (User) session.getAttribute("user");
        log.info("IsLogined : {} / {}", user, login);
        switch (login == null ? LOGOUT : login){ //null값은 session값이 없으므로 로그아웃 상태임
            case LOGOUT: return false;
            case LOGIN: return true;
        }
        return false;
    }
}
