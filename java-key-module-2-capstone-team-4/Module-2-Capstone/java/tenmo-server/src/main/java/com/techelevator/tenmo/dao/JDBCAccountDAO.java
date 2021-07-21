package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCAccountDAO implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCAccountDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Account getAccount(String userName) {
        String sqlGetAccount = "SELECT * FROM Accounts WHERE user_id = (SELECT user_id FROM users where username ilike ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAccount, userName);
        if (results.next()) {
            return mapRowToAccount(results);
        }
        return null;
    }

    @Override
    public void updateAccountBalance(Account account) {
        String sqlWithdrawal = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sqlWithdrawal, account.getBalance(), account.getAccountId());
    }

    @Override
    public Account getAccountByUserID(Integer userId) {
        String sqlGetAccountByUserID = "SELECT * FROM accounts WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAccountByUserID, userId);
        if(results.next()) {
            return mapRowToAccount(results);
        }
        return null;
    }

    @Override
    public Integer getUserIdFromAccountId(Integer accountId) {
        String sqlGetUserIdFromAccountId = "SELECT user_id FROM accounts WHERE account_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserIdFromAccountId, accountId);

        if (results.next()) {
            return results.getInt(1);
        }
        return null;
    }

    // HELPER METHOD
    private Account mapRowToAccount(SqlRowSet results) {
        Account anAccount = new Account();
        anAccount.setUserId(results.getInt("user_id"));
        anAccount.setAccountId(results.getInt("account_id"));
        anAccount.setBalance(results.getDouble("balance"));
        return anAccount;
    }



}
