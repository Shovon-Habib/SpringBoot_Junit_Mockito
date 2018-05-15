package com.example.test.dbtest;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@TestPropertySource(locations = "classpath:test.properties")
public class DbTester {

    @Autowired
    private UserRepository userRepository;

    @org.junit.Test
    public void testDB() {
        List<User> foundEntity = userRepository.findAll();

        Assertions.assertNotNull(foundEntity);
//        Assertions.assertEquals("asif", userRepository.findAll().stream()
//                .filter(user -> user.getName().equalsIgnoreCase("asif"))
//                .findAny().get().getName());
        Assertions.assertEquals(0, userRepository.findAll().size());
    }
}
