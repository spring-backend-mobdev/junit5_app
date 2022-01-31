package cl.mobdev.junit5.testing.domain;

import cl.mobdev.junit5.testing.exception.InsufficientMoneyException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        this.account = new Account("Alan Turing", new BigDecimal("1000.99"));
        System.out.println("Starting testing the method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Ending the test method");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting testing class AccountTest");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Ending the testing class AccountTest");
    }

    @Test
    //@Disabled
    @DisplayName("Testing the account name")
    @EnabledOnJre(JRE.JAVA_17)
    @EnabledOnOs(OS.MAC)
    void name_account() {
        //fail(); // falla el método
        // Account account = new Account("Alan Turing", new BigDecimal(1000.99));
        //balance.setName("Alan Turing");
        String expected = "Alan Turing";
        String real = account.getName();
        assertNotNull(real, () -> "Account cannot be null !!");
        assertEquals(expected, real, () -> "The name of the account is not what was expected !!");
        assertTrue(real.equals("Alan Turing"), () -> "The name of the expected account must be the same as the real one !!");
        assertEquals(expected, real);
    }

    @Test
    void balance_account() {
        //Account account = new Account("Alan Turing", new BigDecimal(1000.99));
        assertNotNull(account.getBalance());
        assertEquals(1000.99, account.getBalance().doubleValue());
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void account_reference() {
        //Account account = new Account("Alan Turing", new BigDecimal("1000.99"));
        Account account2 = new Account("Alan Turing", new BigDecimal("1000.99"));
        //assertNotEquals(account, account2);
        assertEquals(account2, account);
    }

    @Test
    void account_debit() {
        //Account account = new Account("Alan Turing", new BigDecimal("1000.99"));
        account.debit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(900, account.getBalance().intValue());
        assertEquals("900.99", account.getBalance().toPlainString());
    }

    @Test
    void account_credit() {
        //Account account = new Account("Alan Turing", new BigDecimal("1000.99"));
        account.credit(new BigDecimal(100));
        assertNotNull(account.getBalance());
        assertEquals(1100, account.getBalance().intValue());
        assertEquals("1100.99", account.getBalance().toPlainString());
    }

    @Test
    void insufficient_money_exception_account() {
        Account account = new Account("Alan Turing", new BigDecimal("1000.999"));
        Exception exception = assertThrows(InsufficientMoneyException.class, () -> {
           account.debit(new BigDecimal(1500));
        });

        String actual = exception.getMessage();
        String expected = "Insufficient Money";
        assertEquals(expected, actual);
    }

    @Test
    void transfer_money_account() {
        Account account1 = new Account("Alan Turing", new BigDecimal("2500"));
        Account account2 = new Account("Linus Torvals", new BigDecimal("1500"));

        Bank bank = new Bank();
        bank.setName("Banco Scotiabank");
        bank.transfer(account2,account1, new BigDecimal(500));
        assertEquals("1000", account2.getBalance().toPlainString());
        assertEquals("3000", account1.getBalance().toPlainString());
    }

    @Test
    void relationship_bank_account_test_father() {
        Account account1 = new Account("Alan Turing", new BigDecimal("2500"));
        Account account2 = new Account("Linus Torvals", new BigDecimal("1500"));
        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.setName("Bank Scotiabank");
        bank.transfer(account2,account1, new BigDecimal(500));
        assertEquals("1000", account2.getBalance().toPlainString());
        assertEquals("3000", account1.getBalance().toPlainString());

        assertEquals(2, bank.getAccounts().size());
        assertEquals("Bank Scotiabank", account1.getBank().getName());

        /** OPCIÓN 1: la más complejaLa manera más simple de buscar la existencia de una persona en el banco*/
        assertEquals("Alan Turing", bank.getAccounts().stream().filter(c -> c.getName().equals("Alan Turing"))
                .findFirst().get().getName());

        /** OPCIÓN 2:  Un poco más simpleLa manera más simple de buscar la existencia de una persona en el banco*/
        assertTrue(bank.getAccounts().stream()
                .filter(c -> c.getName().equals("Alan Turing"))
                .findFirst().isPresent());

        /** OPCIÓN 3: La manera más simple de buscar la existencia de una persona en el banco*/
        assertTrue(bank.getAccounts().stream()
                .anyMatch(c -> c.getName().equals("Alan Turing")));
    }

    @Test
    void relationship_bank_account_test_assertAll_lambdas() {
        Account account1 = new Account("Alan Turing", new BigDecimal("2500"));
        Account account2 = new Account("Linus Torvals", new BigDecimal("1500"));

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        bank.setName("Bank Scotiabank");
        bank.transfer(account2,account1, new BigDecimal(500));

        assertAll(
                () -> assertEquals("1000", account2.getBalance().toPlainString()),
                () -> assertEquals("3000", account1.getBalance().toPlainString()),
                () -> assertEquals(2, bank.getAccounts().size()),
                () -> assertEquals("Bank Scotiabank", account1.getBank().getName()),
                () -> assertEquals("Alan Turing", bank.getAccounts().stream().filter(c -> c.getName().equals("Alan Turing"))
                        .findFirst().get().getName()),
                () -> assertTrue(bank.getAccounts().stream()
                        .filter(c -> c.getName().equals("Alan Turing"))
                        .findFirst().isPresent()),
                () -> assertTrue(bank.getAccounts().stream()
                        .anyMatch(c -> c.getName().equals("Linus Torvals"))));
    }
}