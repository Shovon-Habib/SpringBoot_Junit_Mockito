package com.example.test.web;

import com.example.test.model.User;
import com.example.test.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DefaultRestController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private DefaultRestController defaultRestController;

    @BeforeAll
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(defaultRestController)
                .build();
    }

    @Test
    public void testPostRestController() throws Exception {
        mockMvc.perform(post("/rest/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.message", is("Successful!!")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetRestController() throws Exception {
        Mockito.when(userService.getUsers()).thenReturn(mockUsers());
        mockMvc.perform(get("/rest/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Asif")))
                .andExpect(jsonPath("$[0].email", is("a@a.a")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Asif1")))
                .andExpect(jsonPath("$[1].email", is("a1@a.a")))

                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Asif2")))
                .andExpect(jsonPath("$[2].email", is("a2@a.a")))
        ;
        Mockito.verify(userService, Mockito.times(1)).getUsers();
        Mockito.verifyNoMoreInteractions(userService);
    }

    private List<User> mockUsers() {
        return new ArrayList<User>() {{
            add(new User((long) 1, "Asif", "a@a.a", "123213"));
            add(new User((long) 2, "Asif1", "a1@a.a", "123213"));
            add(new User((long) 3, "Asif2", "a2@a.a", "123213"));
        }};
    }
}