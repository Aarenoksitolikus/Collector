package me.akvelon.collector.controllers;

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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

    @Test
    void signUp() {
        when(signUpService.signUp(TEST_USER_FORM)).thenReturn(User.builder()
                .id(id++)
                .fullName(TEST_USER_FORM.getFullName())
                .email(TEST_USER_FORM.getEmail())
                .amountOfMoney(new BigDecimal("0"))
                .build());
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("signUp() is working when")
    class SignUpTest {
        @Test
        public void return_fuck() {
            mockMvc.perform(post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content())
        }
    }
}
