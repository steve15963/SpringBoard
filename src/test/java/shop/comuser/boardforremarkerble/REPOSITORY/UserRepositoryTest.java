package shop.comuser.boardforremarkerble.REPOSITORY;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.User;
import shop.comuser.boardforremarkerble.DOMAIN.DAO.VALUE.UserStatus;
import shop.comuser.boardforremarkerble.REPOSITORY.CRUD.CRUDtest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class UserRepositoryTest implements CRUDtest {
    UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Test
    public void Create() {
        User user = User
                .builder()
                .password("test01")
                .userName("test01")
                .status(UserStatus.REGISTERED)
                .build();

        User save = userRepository.save(user);
        Assertions.assertNotNull(save);
    }

    @Override
    @Test
    public void Read() {
        User create = User
                .builder()
                .password("test02")
                .userName("test02")
                .status(UserStatus.REGISTERED)
                .build();

        User save = userRepository.save(create);
        System.out.print("save :");
        System.out.println(save);
        Optional<User> byId = userRepository.findById(save.getId());

        assertThat(byId).isNotEqualTo(Optional.empty());

        System.out.print("find :");
        System.out.println(byId.get());

        assertThat(byId.get()).isEqualTo(save);
    }

    @Override
    @Test
    public void Update() {
        User user = User
                .builder()
                .password("test01")
                .userName("test01")
                .status(UserStatus.REGISTERED)
                .build();

        User save = userRepository.save(user);
        save.setUserName("test02");
        User update = userRepository.save(save);

        assertThat(update.getUserName()).isNotEqualTo("test01");

    }

    @Override
    @Test
    public void Delete() {
        User user = User
                .builder()
                .password("test01")
                .userName("test01")
                .status(UserStatus.REGISTERED)
                .build();

        User save = userRepository.save(user);
        Long id = save.getId();
        userRepository.deleteById(id);
        Optional<User> byId = userRepository.findById(id);
        assertThat(byId).isEqualTo(Optional.empty());
    }
}