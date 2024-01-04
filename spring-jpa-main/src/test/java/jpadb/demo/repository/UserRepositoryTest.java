package jpadb.demo.repository;

import jpadb.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EntityScan(basePackages = "jpadb.demo.model")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest() {
        // given
        User user = new User();
        user.setPassword("987456");
        user.setName("John Doe");
        user.setAge(30);

        // when
        User savedUser = userRepository.save(user);

        // then
        assertNotNull(savedUser.getId());
        assertEquals("987456", savedUser.getPassword());
        assertEquals("John Doe", savedUser.getName());
        assertEquals(30, savedUser.getAge());

        // You can add more assertions based on your requirements
    }

    @Test
    void deleteUserTest() {
        // given
        User user = new User();
        user.setPassword("123456");
        user.setName("Jane Doe");
        user.setAge(25);

        // save the user
        User savedUser = userRepository.save(user);

        // when
        userRepository.delete(savedUser);

        // then
        assertNull(userRepository.findById(savedUser.getId()).orElse(null));
    }
}