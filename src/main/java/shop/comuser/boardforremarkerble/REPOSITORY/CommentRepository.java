package shop.comuser.boardforremarkerble.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Comment;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.Content;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    public void deleteAllByContentId(Long id);
}
