package cl.mobdev.junit5.testing.domain;

import cl.mobdev.junit5.testing.exception.InsufficientMoneyException;

import java.math.BigDecimal;

public class Account {
    private String name;
    private BigDecimal balance;
    private Bank bank;

    public Account() {
    }

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void debit(BigDecimal amount) {
        BigDecimal newBalance = this.balance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientMoneyException("Insufficient Money");
        }
        this.balance = newBalance;
    }


    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account c = (Account) obj;
        if (this.name == null || this.balance == null) {
            return false;
        }
        return this.name.equals(c.getName()) && this.balance.equals(c.getBalance());
    }


}






