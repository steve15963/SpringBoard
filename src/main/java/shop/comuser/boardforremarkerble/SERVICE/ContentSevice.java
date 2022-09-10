package shop.comuser.boardforremarkerble.SERVICE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Content;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.ContentRequest;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Board.NotFoundBoardException;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Board.Value.BoardRequestStatus;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.ContentCreaterIsNotMatched;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.ContentLogoutSessionTryModify;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.ContentNotFoundException;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.ContentRequestNotEqualsException;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.Exception.Content.Value.ContentRequestStatus;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.RESPONSE.ContentResponse;
import shop.comuser.boardforremarkerble.REPOSITORY.BoardRepository;
import shop.comuser.boardforremarkerble.REPOSITORY.ContentRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContentSevice {
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    LoginService loginService;

    public List<Content> recentContent(){
        return contentRepository.findAllByOrderByIdDesc();
    }
    public List<Content> recentBoardCotent(String boardName) {
        Optional<Board> byBoardName = boardRepository.findByBoardName(boardName);
        log.info("find board : {} ", byBoardName.get().getId());
        return byBoardName.map(board ->
                    contentRepository.findAllByBoard(board)
                )
                .orElseGet(() -> new ArrayList<Content>());
    }
    public Content findById(Long id){
        Optional<Content> byId = contentRepository.findById(id);
        return byId
                .map(content -> content)
                .orElseGet(() -> Content.builder().id(-1L).title("").content("").build());
    }

    public ResponseEntity<ContentResponse> create(ContentRequest contentRequest, String boardName,HttpSession session) {
        log.info("finding board : {} ", boardName);
        Optional<Board> byBoardName = boardRepository.findByBoardName(boardName);
        if (byBoardName.isEmpty())
            throw new NotFoundBoardException(BoardRequestStatus.NOT_FOUND_BOARD.getStatus());

        log.info("find Susses boardId : {} ", byBoardName.get().getId());
        User user = (User) session.getAttribute("user");
        Content content = new Content();
        content.set(contentRequest,byBoardName.get(),user);
        content.setUser(user);
        Content save = contentRepository.save(content);

        ContentResponse contentResponse = new ContentResponse();
        contentResponse.set(save);
        log.info("Make Response: {} ", contentResponse);
        return new ResponseEntity<ContentResponse>(contentResponse, HttpStatus.OK);
    }

    public ResponseEntity delete(ContentRequest contentRequest, HttpSession session) {
        Optional<Content> byId = contentRepository.findById(contentRequest.getId());
        User user = (User) session.getAttribute("user");
        return byId.map(content -> {
            if(user.equals(content.getUser())) {
                ContentRequestStatus status = content.equalsRequest(contentRequest);

                switch (status) {
                    case NOT_EQUALS_TITLE:
                    case NOT_FOUND_CONTENT:
                        log.debug("{}", status); //글 삭제 전에 누군가 수정한 경우
                        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
                    //throw new ContentRequestNotEqualsException(status.getStatus());
                }
                contentRepository.delete(content);
                return new ResponseEntity(HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                //throw new ContentCreaterIsNotMatched(ContentRequestStatus.NOT_EQUALS_CREATER.getStatus());
        }).orElseGet(() -> {
            throw new ContentNotFoundException(ContentRequestStatus.NOT_FOUND_CONTENT.getStatus());
        });
    }


    public ResponseEntity modify(ContentRequest contentRequest, String boardName, Long id, HttpSession session) {
        if(loginService.isLogined(session)) {
            User user = (User) session.getAttribute("user");
            Optional<Content> byId = contentRepository.findById(id);
            return byId.map(content -> {
                        if(content.getUser().equals(user)){
                            content.set(contentRequest);
                            contentRepository.save(content);
                            return new ResponseEntity(HttpStatus.OK);
                        }
                        else{
                            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
                            //throw new ContentCreaterIsNotMatched(ContentRequestStatus.NOT_EQUALS_CREATER.getStatus());
                        }
                    })
                    .orElseGet(() -> {
                        log.error("id {}", id);
                        throw new ContentNotFoundException(ContentRequestStatus.NOT_FOUND_CONTENT.getStatus());
                    });
        }
        else{
            throw new ContentLogoutSessionTryModify(ContentRequestStatus.LOGOUT_SESSION_TRY_MODIFY.getStatus());
        }
    }

    public String GetTitleById(Long id){
        Optional<Content> byId = contentRepository.findById(id);
        return byId.map(content -> content.getTitle())
                .orElseGet(() -> "");
    }

    public String GetContentById(Long id){
        Optional<Content> byId = contentRepository.findById(id);
        return byId.map(content -> content.getContent())
                .orElseGet(() -> "");
    }
}
