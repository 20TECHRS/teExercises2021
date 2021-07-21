package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*******************************************************************************************************
 * This is where you code any API controllers you may create
 *
 * Feel free to create additional controller classs if you would
 * like to have separate controller classses base on functionality or use
********************************************************************************************************/
@RestController
public class AccountController {

    AccountDAO accountDAO;
    public AccountController(AccountDAO accountDAO) {this.accountDAO = accountDAO;}

@RequestMapping(path = "/account/username/{username}", method = RequestMethod.GET)
        public Account getAccount(@PathVariable String username) {
            LogAPIRequest.logAPICall("GET - /account/username/" + username);
            return accountDAO.getAccount(username);
    }

@RequestMapping(path = "/account/{id}", method = RequestMethod.GET)
        public Account getAccountByUserID(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - /account/" + id);
        Account account = accountDAO.getAccountByUserID(id);
        return account;
}

@RequestMapping(path = "/account/{id}/transfer", method = RequestMethod.PUT)
        public void updateAccountBalance(@PathVariable Integer id, @RequestBody Account account) {
        LogAPIRequest.logAPICall("PUT - /account/" + id + "/transfer");
        accountDAO.updateAccountBalance(account);
}

@RequestMapping(path = "/account/{id}/userId", method = RequestMethod.GET)
    public Integer getUserIdFromAccountId(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - /account/" + id + "/userId");
        return accountDAO.getUserIdFromAccountId(id);
}




} // End of Class
