package me.akvelon.collector.repositories.collections;

import me.akvelon.collector.exceptions.TransactionException;
import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.Transaction;
import me.akvelon.collector.models.User;
import me.akvelon.collector.repositories.intefraces.TransactionsRepository;
import me.akvelon.collector.repositories.intefraces.UsersRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("TransactionsRepository is working when")
class TransactionsRepositoryCollectionsImplTest {

    private final UsersRepository usersRepository = new UsersRepositoryCollectionsImpl();
    private final TransactionsRepository transactionsRepository = new TransactionsRepositoryCollectionsImpl(usersRepository);

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final User firstUser = User.builder()
            .id(1L)
            .fullName("Maxim")
            .email("maxim@gmail.com")
            .amountOfMoney(new BigDecimal("5"))
            .build();

    private final User secondUser = User.builder()
            .id(2L)
            .fullName("Sergey")
            .email("sergey@gmail.com")
            .amountOfMoney(new BigDecimal("0"))
            .build();

    private final Transaction transaction = Transaction.builder()
            .from(1L)
            .to(2L)
            .amount(new BigDecimal("1"))
            .timestamp(timestamp)
            .build();

    @BeforeEach
    public void setUp() {
        usersRepository.save(firstUser);
        usersRepository.save(secondUser);
    }

    @Nested
    @DisplayName("findAll() is working")
    class FindAllTest {
        @Nested
        @DisplayName("findAll() without parameters is working")
        class FindAllWithoutParametersTest {

            @Test
            public void returns_empty_list_when_there_is_no_transactions() {
                assertThat(transactionsRepository.findAll().size(), is(equalTo(0)));
            }

            @ParameterizedTest(name = "returns correct list of {0} transactions where there are {0} transactions")
            @ValueSource(ints = {1, 3, 5})
            public void returns_list_of_all_transactions_when_there_are_some_transactions(int amountOfTransactions) {
                var result = new ArrayList<Transaction>();
                for (int i = 0; i < amountOfTransactions; i++) {
                    transactionsRepository.save(transaction);
                    transaction.setId((long) (i + 1));
                    result.add(transaction);
                }
                System.out.println(transactionsRepository.findAll());
                assertThat(transactionsRepository.findAll(), is(equalTo(result)));
            }
        }

        @Nested
        @DisplayName("findAll() with parameters is working")
        class FindAllWithParametersTest {
            @ParameterizedTest(name = "returns correct transactions list with parameters where limit = {0}, offset = {1}")
            @CsvSource(value = {"1,0,1", "5,0,5", "3,20,23"})
            public void returns_the_part_of_users_on_findAll_with_parameters(int limit, int offset, int capacity) {
                if (capacity > 5) {
                    usersRepository.changeAmountOfMoney(1L, new BigDecimal("200"));
                }
                for (int i = 0; i < capacity; i++) {
                    transactionsRepository.save(Transaction.builder()
                            .from(1L)
                            .to(2L)
                            .amount(new BigDecimal("1"))
                            .timestamp(timestamp)
                            .build());
                }
                var expectedTransactions = new ArrayList<Transaction>();
                for (int i = offset + 1; i <= offset + limit; i++) {
                    expectedTransactions.add(Transaction.builder()
                            .id((long) i)
                            .from(1L)
                            .to(2L)
                            .amount(new BigDecimal("1"))
                            .timestamp(timestamp)
                            .build());
                }
                assertThat(transactionsRepository.findAll(limit, offset), is(equalTo(expectedTransactions)));
            }
        }
    }

    @Nested
    @DisplayName("save() is working")
    class SaveTest {
        @Test
        public void returns_transaction_entity_on_save() {
            var result = transactionsRepository.save(transaction);
            transaction.setId(1L);
            assertThat(result, is(equalTo(transaction)));
        }

        @Test
        public void throws_exception_if_transaction_is_null() {
            assertThrows(NullPointerException.class, () -> transactionsRepository.save(null));
        }

        @Test
        public void throws_exception_if_sender_is_null() {
            assertThrows(UserNotFoundException.class, () -> transactionsRepository.save(Transaction.builder()
                    .to(2L)
                    .amount(new BigDecimal("1"))
                    .timestamp(timestamp)
                    .build()));
        }

        @Test
        public void throws_exception_if_recipient_is_null() {
            assertThrows(TransactionException.class, () -> transactionsRepository.save(Transaction.builder()
                    .from(1L)
                    .amount(new BigDecimal("1"))
                    .timestamp(timestamp)
                    .build()));
        }

        @Test
        public void throws_exception_if_amount_is_null() {
            assertThrows(NullPointerException.class, () -> transactionsRepository.save(Transaction.builder()
                    .from(1L)
                    .to(2L)
                    .timestamp(timestamp)
                    .build()));
        }

//            @Test
//            public void throws_exception_if_users_were_deleted_during_operation() {
//                transactionsRepository.save(transaction);
//            }
    }

    @Nested
    @DisplayName("findById() is working")
    class FindByIdTest {
        @Test
        public void returns_null_if_user_does_not_exist() {
            assertThat(transactionsRepository.findById(1L), is(equalTo(Optional.empty())));
        }

        @Test
        public void returns_correct_user_if_it_exists() {
            transactionsRepository.save(transaction);
            transaction.setId(1L);
            assertThat(transactionsRepository.findById(1L).get(), is(equalTo(transaction)));
        }
    }

    @Nested
    @DisplayName("findAllLatest() is working")
    class FindAllLatestTest {
        @ParameterizedTest(name = "returns all latest transactions from given time")
        @CsvSource(value = {"1,0"})
        public void returns_fuck(int a, int b) {

        }
    }
}