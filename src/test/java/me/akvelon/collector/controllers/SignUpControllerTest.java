package me.akvelon.collector.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;
import me.akvelon.collector.services.interfaces.SignUpService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SignUpController is working when")
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignUpService signUpService;

    private final UserForm TEST_USER_FORM = UserForm.builder()
            .email("test@test.test")
            .fullName("Test User")
            .build();

    private long id = 1L;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    void signUp() {
        when(signUpService.signUp(TEST_USER_FORM)).thenReturn(User.builder()
                .id(id++)
                .fullName(TEST_USER_FORM.getFullName())
                .email(TEST_USER_FORM.getEmail())
                .amountOfMoney(new BigDecimal("0"))
                .build());

        when(signUpService.signUp(null)).thenReturn(null);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("signUp() is working when")
    class SignUpTest {
        @Test
        public void return_user_when_comes_not_null_user() throws Exception {
            mockMvc.perform(post("/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(gson.toJson(TEST_USER_FORM)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.fullName", is("Test User")))
                    .andExpect(jsonPath("$.email", is("test@test.test")))
                    .andExpect(jsonPath("$.amountOfMoney", is(0)));
        }

        @Test
        public void throws_exception_when_comes_null() throws Exception {
            mockMvc.perform(post("/signup")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code", is(400)));
        }
    }
}
