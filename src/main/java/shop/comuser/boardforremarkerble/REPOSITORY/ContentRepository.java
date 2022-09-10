package shop.comuser.boardforremarkerble.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Board;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Content;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content,Long> {
    public List<Content> findAllByOrderByIdDesc();
    public List<Content> findAllByBoard(Board board);
}
