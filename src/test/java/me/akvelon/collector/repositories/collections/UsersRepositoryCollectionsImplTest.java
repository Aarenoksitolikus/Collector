package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.exceptions.BalanceCheckException;
import me.akvelon.collector.exceptions.TransactionException;
import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Nested
    @DisplayName("findAll() is working")
    class FindAllTest {
        @Nested
        @DisplayName("findAll() without parameters is working")
        class FindAllWithoutParametersTest {

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
        }

        @Nested
        @DisplayName("findAll() with parameters is working")
        class FindAllWithParametersTest {
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
                assertThat(repository.findAll(limit, offset), is(equalTo(a)));
            }
        }
    }

    @Nested
    @DisplayName("save() is working")
    class SaveTest {
        @Test
        public void returns_user_entity_on_save() {
            var result = repository.save(user);
            user.setId(1L);
            assertThat(result, is(equalTo(user)));
        }
    }

    @Nested
    @DisplayName("findById() is working")
    class FindByIdTest {
        @Test
        void returns_null_if_user_does_not_exist
                () {
            assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
        }

        @Test
        void returns_correct_user_if_it_exists() {
            repository.save(user);
            user.setId(1L);
            assertThat(repository.findById(1L).get(), is(equalTo(user)));
        }
    }

    @Nested
    @DisplayName("update() is working")
    class UpdateTest {
        @Test
        void returns_null_if_user_does_not_exist
                () {
            repository.update(user);
            assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
        }

        @Test
        void updates_all_fields_if_user_exists() {
            repository.save(user);
            user.setEmail("new-email@gmail.com");
            user.setFullName("New User");
            user.setAmountOfMoney(new BigDecimal("1"));
            repository.update(user);
            assertThat(repository.findById(1L).get(), is(equalTo(user)));
        }
    }

    @Nested
    @DisplayName("deletion is working")
    class DeletionTest {

        @Nested
        @DisplayName("delete() is working")
        class DeleteTest {
            @Test
            void returns_empty_optional_if_user_was_deleted() {
                repository.save(user);
                user.setId(1L);
                assertThat(repository.findById(1L), is(equalTo(Optional.of(user))));
                repository.delete(user);
                assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
            }

            @Test
            void returns_empty_optional_if_user_was_not_exist() {
                repository.delete(user);
                assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
            }
        }

        @Nested
        @DisplayName("deleteById() is working")
        class DeleteByIdTest {
            @Test
            void returns_empty_optional_if_user_exists() {
                repository.save(user);
                user.setId(1L);
                assertThat(repository.findById(1L), is(equalTo(Optional.of(user))));
                repository.deleteById(1L);
                assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
            }

            @Test
            void returns_empty_optional_if_user_was_not_exist() {
                repository.deleteById(1L);
                assertThat(repository.findById(1L), is(equalTo(Optional.empty())));
            }
        }

    }

    @Nested
    @DisplayName("changeAmountOfMoney() is working")
    class ChangeAmountOfMoneyTest {
        @Test
        public void throws_exception_if_id_is_null() {
            assertThrows(UserNotFoundException.class, () -> repository.changeAmountOfMoney(null, null));
        }

        @Test
        public void throws_exception_if_user_does_not_exist() {
            assertThrows(UserNotFoundException.class, () -> repository.changeAmountOfMoney(1L, null));
        }

        @Test
        public void throws_exception_if_amount_of_money_is_null() {
            repository.save(user);
            assertThrows(TransactionException.class, () -> repository.changeAmountOfMoney(1L, null));
        }

        @Test
        public void throws_exception_if_user_has_not_enough_money() {
            repository.save(user);
            assertThrows(BalanceCheckException.class, () -> repository.changeAmountOfMoney(1L, new BigDecimal("-1")));
        }

        @Test
        public void adds_amount_of_money_if_it_is_positive() {
            repository.save(user);
            repository.changeAmountOfMoney(1L, new BigDecimal("1"));
            assertThat(repository.findById(1L).get().getAmountOfMoney(), is(equalTo(new BigDecimal("1"))));
        }
    }
}