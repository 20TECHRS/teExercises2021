package com.techelevator.tenmo.models;

import java.util.Objects;

public class Account {

    private Integer userId;
    private Integer accountId;
    private Double balance;

    public Account () { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(userId, account.userId)) return false;
        if (!Objects.equals(accountId, account.accountId)) return false;
        return Objects.equals(balance, account.balance);
    }

    public Account (Integer userId, Integer accountId, Double balance) {
        this.userId    = userId;
        this.accountId = accountId;
        this.balance   = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // Helper Methods

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }

}
