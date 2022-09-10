package shop.comuser.boardforremarkerble.REPOSITORY;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;
import shop.comuser.boardforremarkerble.REPOSITORY.CRUD.CRUDtest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class BoardRepositoryTest implements CRUDtest {

    @Autowired
    BoardRepository boardRepository;

    Board board;

    @BeforeEach
    public void BeforeEach(){
        board = boardRepository.save(
                Board
                        .builder()
                        .boardName("test01")
                        .build()
        );
    }

    @Override
    @Test
    public void Create() {
        assertThat(board.getBoardName()).isEqualTo("test01");
    }

    @Override
    @Test
    public void Read() {
        Optional<Board> byId = boardRepository.findById(board.getId());
        assertThat(byId).isNotEqualTo(Optional.empty());
        assertThat(byId.get()).isEqualTo(board);
    }

    @Override
    @Test
    public void Update() {
        board.setBoardName("test02");
        Board update = boardRepository.save(board);
        assertThat(update.getBoardName()).isEqualTo("test02");
    }

    @Override
    @Test
    public void Delete() {
        Long id = board.getId();
        boardRepository.deleteById(id);
        Optional<Board> byId = boardRepository.findById(id);
        assertThat(byId).isEqualTo(Optional.empty());
    }
}