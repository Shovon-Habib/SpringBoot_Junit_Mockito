package com.example.test;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=test")
@Configuration
@EnableTransactionManagement
public class TestApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        System.out.println("Hello World!");
    }

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        List<User> foundEntity = userRepository.findAll();

        Assertions.assertNotNull(foundEntity);
        Assertions.assertEquals("asif", userRepository.findAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase("asif"))
                .findAny().get().getName());
    }
}
