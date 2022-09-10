package shop.comuser.boardforremarkerble.REPOSITORY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Comment;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Content;
import shop.comuser.boardforremarkerble.REPOSITORY.CRUD.CRUDtest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ContentRepositoryTest implements CRUDtest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    BoardRepository boardRepository;

    Board board;
    Comment comment;
    Content content;

    @BeforeEach
    public void BeforeEach(){
        board = boardRepository.save(
                Board
                        .builder()
                        .boardName("test01")
                        .build()
        );
        content = contentRepository.save(
                Content
                        .builder()
                        .content("test01")
                        .title("testTitle01")
                        .views(0)
                        .thumbsUp(0)
                        .thumbsDown(0)
                        .board(board)
                        .build()
        );
        comment = commentRepository.save(
                Comment
                        .builder()
                        .text("test01")
                        .content(content)
                        .build()
        );
    }

    @Override
    @Test
    public void Create() {
        assertThat(content).isNotNull();
        assertThat(content.getContent()).isEqualTo("test01");
    }

    @Override
    @Test
    public void Read() {
        Optional<Content> byId = contentRepository.findById(content.getId());
        assertThat(byId).isNotEqualTo(Optional.empty());
        assertThat(byId.get().getContent()).isEqualTo("test01");
    }

    @Override
    @Test
    public void Update() {
        content.setContent("test02");
        content.setUpdateAt(LocalDateTime.now());
        Content update = contentRepository.save(content);
        assertThat(update.getContent()).isEqualTo("test02");
    }

    @Override
    @Test
    public void Delete() {
        Long id = content.getId();
        commentRepository.deleteAllByContentId(id);
        contentRepository.deleteById(id);
        Optional<Content> byId = contentRepository.findById(id);
        assertThat(byId).isEqualTo(Optional.empty());

    }
}