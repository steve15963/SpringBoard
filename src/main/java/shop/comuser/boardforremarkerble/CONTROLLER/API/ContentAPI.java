package shop.comuser.boardforremarkerble.CONTROLLER.API;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.REQUEST.ContentRequest;
import shop.comuser.boardforremarkerble.DOMAIN.DTO.RESPONSE.ContentResponse;
import shop.comuser.boardforremarkerble.SERVICE.ContentSevice;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/content")
@Slf4j
public class ContentAPI {

    @Autowired
    ContentSevice contentSevice;
    @PostMapping("{boardName}")
    public ResponseEntity<ContentResponse> create(@RequestBody ContentRequest contentRequest,@PathVariable String boardName,HttpSession session){
        return contentSevice.create(contentRequest,boardName,session);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody ContentRequest contentRequest, HttpSession session){
        return contentSevice.delete(contentRequest,session);
    }

    @PatchMapping("{boardName}/{id}")
    public ResponseEntity modify(@RequestBody ContentRequest contentRequest,@PathVariable String boardName,@PathVariable long id,HttpSession session){
        return contentSevice.modify(contentRequest,boardName,id,session);
    }
}
