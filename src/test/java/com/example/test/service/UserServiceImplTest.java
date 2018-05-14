package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeAll
    public void create() {
        userServiceImpl = new UserServiceImpl(userRepository);
        initMocks(this);
    }

    @Test
    public void testMockCreation() {
        //Assert.assertNotNull() methods checks that the object is null or not. If it is null then it throws an AssertionError.
        Assertions.assertNotNull(userRepository);
        Assertions.assertNotNull(user);
    }

    @Test
    void createUser() {
        user.setId((long) 1);
        user.setName("Asif");
        user.setEmail("a@a.a");
        user.setPassword("213234");
        when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user, userRepository.save(user));
    }

    @Test
    void getUsers() {
        when(userRepository.findAll()).thenReturn(getMockUsers());
        assertEquals("Asif", userRepository.findAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase("asif"))
                .findAny().get().getName());
    }

    private List<User> getMockUsers() {
        return new ArrayList<User>() {{
            add(new User("Asif", "a@a.a", "123213"));
            add(new User("Asif1", "a1@a.a", "123213"));
            add(new User("Asif2", "a2@a.a", "123213"));
        }};
    }

}