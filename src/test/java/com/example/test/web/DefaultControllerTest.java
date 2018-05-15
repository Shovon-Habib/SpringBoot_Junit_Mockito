package com.example.test.web;

import com.example.test.model.User;
import com.example.test.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DefaultController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    DefaultController defaultController;

    @BeforeAll
    public void initialize() {
        initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(defaultController)
                .build();
    }

    @Test
    public void testMockCreation() {
        //Assert.assertNotNull() methods checks that the object is null or not. If it is null then it throws an AssertionError.
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(mvc);
    }

    @Test
    public void testExample() throws Exception {
        given(this.userService.getUsers()).willReturn(mockUsers());
        Object object = this.mvc.perform(get("/")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(model().attribute("title", "Home"))
                .andExpect(model().attribute("users", userService.getUsers()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    private List<User> mockUsers() {
        return new ArrayList<User>() {{
            add(new User("Asif", "a@a.a", "123213"));
            add(new User("Asif1", "a1@a.a", "123213"));
            add(new User("Asif2", "a2@a.a", "123213"));
        }};
    }
}