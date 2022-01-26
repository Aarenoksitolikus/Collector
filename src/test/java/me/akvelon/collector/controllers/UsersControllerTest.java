package me.akvelon.collector.controllers;

import me.akvelon.collector.models.Page;
import me.akvelon.collector.models.User;
import me.akvelon.collector.services.interfaces.UsersService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UsersController is working when")
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

//    @BeforeEach
//    void signUp() {
//        when(signUpService.signUp(TEST_USER_FORM)).thenReturn(User.builder()
//                .id(id++)
//                .fullName(TEST_USER_FORM.getFullName())
//                .email(TEST_USER_FORM.getEmail())
//                .amountOfMoney(new BigDecimal("0"))
//                .build());
//
//        when(signUpService.signUp(null)).thenReturn(null);
//    }

    private final User TEST_USER = User.builder()
            .id(1L)
            .email("test@test.test")
            .fullName("Test User")
            .amountOfMoney(new BigDecimal("0"))
            .build();

    @BeforeEach
    public void setUp() {
        var list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            list.add(TEST_USER);
        }
        User[] array = (User[]) list.stream().skip(2).limit(3).toArray();
        var i = 1L;
        for (User current : list) {
            current.setId(i++);
        }
        when(usersService.getAll()).thenReturn(list);
        when(usersService.getPage(2, 3)).thenReturn(new Page<>(array));
        when(usersService.getById(1L)).thenReturn(Optional.of(TEST_USER));
//        doNothing().when(usersService.changeBalance(1L, "1"));
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getAll() is working when")
    class GetAllTest {
        @Test
        public void returns_correct_list_of_users() throws Exception {
            mockMvc.perform(get("users/all"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$"));
        }
    }
}