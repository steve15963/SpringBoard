package shop.comuser.boardforremarkerble.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByAccountAndPassword(String account,String password);
}
