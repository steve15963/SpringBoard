package shop.comuser.boardforremarkerble.CONTROLLER.API;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.comuser.boardforremarkerble.CONTROLLER.API.Value.LoginValue;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.UserRequest;
import shop.comuser.boardforremarkerble.SERVICE.LoginService;
import shop.comuser.boardforremarkerble.SERVICE.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserAPI {
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserRequest request, HttpSession session) throws IOException {
        log.info("login request : {}",request);
        if(loginService.isLogined(session)) return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE); //로그인이 된 상태라면.
        // 아이디 비밀번호가 유효한 경우
        if(userService.login_progress(request,session))return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //아이디나 비밀번호가 틀린경우
    }
    @PostMapping("logout")
    public ResponseEntity logout(HttpSession session){
        session.setAttribute("login",LoginValue.LOGOUT);
        session.setAttribute("user",null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
