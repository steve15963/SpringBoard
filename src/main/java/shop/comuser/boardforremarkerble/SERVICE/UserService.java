package shop.comuser.boardforremarkerble.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.comuser.boardforremarkerble.CONTROLLER.API.Value.LoginValue;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.UserRequest;
import shop.comuser.boardforremarkerble.REPOSITORY.UserRepository;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public HttpRequest getUser(Long id){

        return null;
    }

    public HttpRequest findUserByUserName(Long id){

        return null;
    }

    public int Login(Long id){

        return 0;
    }

    public boolean login_progress(UserRequest request, HttpSession session) {
        Optional<User> loginUser = userRepository.findByAccountAndPassword(
                request.getAccount(), request.getPassword()
        );
        return loginUser.map(user -> {
                    session.setAttribute("login", LoginValue.LOGIN);
                    session.setAttribute("user", user);
                    return true;
                })
                .orElseGet(() -> false);
    }
}
