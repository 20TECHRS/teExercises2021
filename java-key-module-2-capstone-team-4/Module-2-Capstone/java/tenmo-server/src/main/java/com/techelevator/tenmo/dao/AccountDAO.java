package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {

    Account getAccount(String userName);

    void updateAccountBalance(Account account);

    Account getAccountByUserID(Integer userId);

    Integer getUserIdFromAccountId(Integer accountId);

}
