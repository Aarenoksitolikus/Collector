package me.akvelon.collector;

import me.akvelon.collector.controllers.SignUpController;
import me.akvelon.collector.controllers.TransactionsController;
import me.akvelon.collector.controllers.UsersController;
import me.akvelon.collector.repositories.intefraces.TransactionsRepository;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import me.akvelon.collector.services.interfaces.SignUpService;
import me.akvelon.collector.services.interfaces.TransactionsService;
import me.akvelon.collector.services.interfaces.UsersService;
import me.akvelon.collector.utils.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private SignUpController signUpController;

    @Autowired
    private TransactionsController transactionsController;

    @Autowired
    private UsersController usersController;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper mapper;

    @Test
    void contextLoads() {
        assertThat(signUpController, is(notNullValue()));
        assertThat(transactionsController, is(notNullValue()));
        assertThat(usersController, is(notNullValue()));
        assertThat(signUpService, is(notNullValue()));
        assertThat(transactionsService, is(notNullValue()));
        assertThat(usersService, is(notNullValue()));
        assertThat(transactionsRepository, is(notNullValue()));
        assertThat(usersRepository, is(notNullValue()));
        assertThat(mapper, is(notNullValue()));
    }
}
