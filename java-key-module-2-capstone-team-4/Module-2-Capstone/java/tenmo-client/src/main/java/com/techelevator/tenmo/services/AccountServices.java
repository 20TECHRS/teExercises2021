package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;



public class AccountServices {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL;

    public AccountServices(String url) { BASE_URL = url;}

    public Double getBalance(String userName) {
        Account anAccount = restTemplate.getForObject(BASE_URL + "/account/username/" + userName, Account.class);
        return anAccount.getBalance();
    }

    public boolean withdrawal(AuthenticatedUser user, Double amount) {
        Account account = getAccountByUserID(user.getUser().getId());

        if (account.getBalance() < amount) {
            return false;
        }
        account.setBalance(account.getBalance()-amount);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(account, httpHeaders);

        restTemplate.put(BASE_URL + "/account/" + account.getAccountId() + "/transfer", entity);

        return true;
    }

    public void deposit(Integer userId, Double amount) {
        Account account = getAccountByUserID(userId);
        account.setBalance(account.getBalance()+amount);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(account, httpHeaders);

        restTemplate.put(BASE_URL + "/account/" + account.getAccountId() + "/transfer", entity);
    }

    public Account getAccountByUserID(Integer userId) {
        Account account = restTemplate.getForObject(BASE_URL + "/account/" + userId, Account.class);
        return account;
    }

    public Integer getUserIdFromAccountId(Integer accountId) {
        return restTemplate.getForObject(BASE_URL + "/account/" + accountId + "/userId", Integer.class);
    }








} // End of Class
