package shop.comuser.boardforremarkerble.CONTROLLER.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;
import shop.comuser.boardforremarkerble.SERVICE.BoardService;
import shop.comuser.boardforremarkerble.SERVICE.ContentSevice;
import shop.comuser.boardforremarkerble.SERVICE.LoginService;
import shop.comuser.boardforremarkerble.SpringBootConfig;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/web")
public class StaticBoundCONTROLLER {

    @Autowired
    SpringBootConfig springBootConfig;
    @Autowired
    BoardService boardService;

    @Autowired
    ContentSevice contentSevice;

    @Autowired
    LoginService loginService;
    /*<--------------------------- 비 로그인 지역 ---------------------->*/
    @GetMapping("")
    public ModelAndView WellComePage(HttpSession session){
        log.info("session : {}",session.getAttribute("login"));
        log.info("Access Index Page");
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView;
        if(loginService.isLogined(session)) modelAndView = new ModelAndView("/view/logined/index").addObject("user",user);
        else                                modelAndView = new ModelAndView("/view/logouted/index");
        return modelAndView
                .addObject("serverName", springBootConfig.getServeName())
                .addObject("boardList", boardService.getAllBoard());
    }


    @GetMapping("/login")
    public ModelAndView LoginPage(HttpSession session,HttpServletResponse httpServletResponse) throws IOException {
        log.info("session : {}",session.getAttribute("login"));
        log.info("Access login Page");
        if(loginService.isLogined(session)) {
            httpServletResponse.sendRedirect("/web");
            return null;
        }
        return new ModelAndView("/view/logouted/login")
                .addObject("serverName", springBootConfig.getServeName())
                .addObject("boardList", boardService.getAllBoard());
    }

    @GetMapping("view/{boardName}")
    public ModelAndView BoardSeleted(@PathVariable String boardName,HttpSession session){
        log.info("session : {}",session.getAttribute("login"));
        log.info("Access View ContentList Page : {}",boardName);

        User user = (User) session.getAttribute("user");

        ModelAndView modelAndView;
        if(loginService.isLogined(session)) modelAndView = new ModelAndView("/view/logined/board").addObject("user",user);
        else                                modelAndView = new ModelAndView("/view/logouted/board");

        return modelAndView
                .addObject("serverName", springBootConfig.getServeName())
                .addObject("boardList", boardService.getAllBoard())
                .addObject("boardName", boardName)
                .addObject("contentList", contentSevice.recentBoardCotent(boardName));
    }
    /*<--------------------------- 로그인 지역 ---------------------->*/
    @GetMapping("view/{boardName}/{id}")
    public ModelAndView ContentSeleted(@PathVariable String boardName,@PathVariable Long id,HttpSession session,HttpServletResponse httpServletResponse) throws IOException {
        log.info("session : {}",session.getAttribute("login"));
        log.info("Access view Content Page : {}",boardName);
        if(loginService.isLogined(session)) {
            User user = (User) session.getAttribute("user");
            return new ModelAndView("/view/logined/content")
                    .addObject("user",user)
                    .addObject("serverName", springBootConfig.getServeName())
                    .addObject("boardList", boardService.getAllBoard())
                    .addObject("content", contentSevice.findById(id))
                    .addObject("boardName", boardName)
                    .addObject("id", id)
                    .addObject("contentList", contentSevice.recentBoardCotent(boardName));
        }
        httpServletResponse.sendRedirect("/web/login");
        return null;
    }

    @GetMapping("write/{boardName}")
    public ModelAndView ContentWirte(@PathVariable String boardName,HttpSession session,HttpServletResponse httpServletResponse) throws IOException {
        log.info("session : {}",session);
        log.info("Access write Content Page : {}",boardName);
        if(loginService.isLogined(session)) {
            User user = (User) session.getAttribute("user");
            return new ModelAndView("/view/logined/write")
                    .addObject("user", user)
                    .addObject("serverName", springBootConfig.getServeName())
                    .addObject("boardList", boardService.getAllBoard())
                    .addObject("boardName", boardName);
        }
        httpServletResponse.sendRedirect("/web");
        return null;
    }

    @GetMapping("modify/{boardName}/{id}")
    public ModelAndView ContentModify(@PathVariable String boardName,@PathVariable Long id,HttpSession session,HttpServletResponse httpServletResponse) throws IOException {
        log.info("session : {}",session);
        log.info("Access modify Content Page : {}",boardName);
        if(loginService.isLogined(session)) {
            User user = (User) session.getAttribute("user");
            return new ModelAndView("/view/logined/modify")
                    .addObject("user",user)
                    .addObject("serverName", springBootConfig.getServeName())
                    .addObject("boardList", boardService.getAllBoard())
                    .addObject("boardName", boardName)
                    .addObject("title", contentSevice.GetTitleById(id))
                    .addObject("content",contentSevice.GetContentById(id));
        }
        httpServletResponse.sendRedirect("/web");
        return null;
    }



}
