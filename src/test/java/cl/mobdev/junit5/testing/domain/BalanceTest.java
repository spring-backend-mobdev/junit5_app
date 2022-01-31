package cl.mobdev.junit5.testing.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {

    @Test
    void name_account() {
        Balance balance = new Balance("Emmanuel", new BigDecimal(1999.9));
        //balance.setName("Emmanuel");
        String expected = "Emmanuel";
        String real = balance.getName();

        assertEquals(expected, real);
    }

    @Test
    void balance_account() {
        Balance balance = new Balance("Emmanuel", new BigDecimal(1000.123));
        assertEquals(1000.123, balance.getBalance().doubleValue());
        assertFalse(balance.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(balance.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void getBalance() {
    }

    @Test
    void setBalance() {
    }
}