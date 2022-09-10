package shop.comuser.boardforremarkerble.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByBoardNameOrderByIdDesc(String boardName);

    Optional<Board> findByBoardName(String boardName);
}
