package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UsersRepository is working when")
class UsersRepositoryCollectionsImplTest {

    private final UsersRepository repository = new UsersRepositoryCollectionsImpl();

    private final User user = User.builder()
            .id(1L)
            .fullName("Maxim")
            .email("maxim@gmail.com")
            .amountOfMoney(new BigDecimal("0"))
            .build();

    @Test
    public void returns_empty_list_when_there_is_no_users() {
        assertThat(repository.findAll().size(), is(equalTo(0)));
    }

    @ParameterizedTest(name = "returns correct list of {0} users where there are {0} users")
    @ValueSource(ints = {1, 3, 5})
    public void returns_list_of_all_users_when_there_are_some_users(int amountOfUsers) {
        var result = new ArrayList<User>();
        for (int i = 0; i < amountOfUsers; i++) {
            repository.save(user);
            user.setId((long) (i + 1));
            result.add(user);
        }
        assertThat(repository.findAll(), is(equalTo(result)));
    }

    @Test
    public void returns_user_entity_on_save() {
        var result = repository.save(user);
        user.setId(1L);
        assertThat(result, is(equalTo(user)));
    }

    @ParameterizedTest(name = "returns correct users list with parameters where limit = {0}, offset = {1}")
    @CsvSource(value = {"1,0,1", "5,0,5", "3,20,23"})
    void returns_the_part_of_users_on_findAll_with_parameters(int limit, int offset, int capacity) {
        for (int i = 0; i < capacity; i++) {
            repository.save(User.builder()
                    .fullName("Maxim")
                    .email("maxim@gmail.com")
                    .amountOfMoney(new BigDecimal("0"))
                    .build());
        }
        var a = new ArrayList<User>();
        for (int i = offset + 1; i <= offset + limit; i++) {
            a.add(User.builder()
                    .id((long) i)
                    .fullName("Maxim")
                    .email("maxim@gmail.com")
                    .amountOfMoney(new BigDecimal("0"))
                    .build());
        }
        System.out.println(repository.findAll(limit, offset));
        assertThat(repository.findAll(limit, offset), is(equalTo(a)));
    }

    @Test
    void findById() {
        repository.save(user);
        user.setId(1L);
        assertThat(repository.findById(1L).get(), is(equalTo(user)));
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}