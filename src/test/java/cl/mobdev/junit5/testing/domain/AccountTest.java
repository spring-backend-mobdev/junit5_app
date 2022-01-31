package cl.mobdev.junit5.testing.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void name_account() {
        Account account = new Account("Emmanuel", new BigDecimal(1999.9));
        //balance.setName("Emmanuel");
        String expected = "Emmanuel";
        String real = account.getName();

        assertEquals(expected, real);
    }

    @Test
    void balance_account() {
        Account account = new Account("Emmanuel", new BigDecimal(1000.123));
        assertEquals(1000.123, account.getBalance().doubleValue());
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void account_reference() {
        Account account = new Account("Alan Turing", new BigDecimal("9999,99"));
        Account account2 = new Account("Alan Turing", new BigDecimal("9999,99"));

        //assertNotEquals(account, account2);
        assertEquals(account2, account);
    }

//    @Test
//    @Disabled
//    void credit_account() {
//        fail();
//        Account account = new Account("Emmanuel", new BigDecimal("1000.99"));
//        account.credit(new BigDecimal(100));
//    }


}