package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class TransferServices {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL;

    public TransferServices(String url) {
        BASE_URL = url;
    }

    public Transfer createSendTransfer(Integer fromUserId, Integer toUserId, Double amount) {
        Transfer transfer = new Transfer(fromUserId, toUserId, amount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);

        transfer = restTemplate.postForObject(BASE_URL + "account/" + toUserId + "/transfer", entity, Transfer.class);

        return transfer;
    }

    public Transfer[] getTransfersFromAccount(Integer accountId) {
        Transfer[] transfers = restTemplate.getForObject(BASE_URL + "account/" + accountId + "/transfer", Transfer[].class);
        return transfers;
    }

    public String getTransferTypeText(Integer transferTypeId) {
        String transferTypeText = restTemplate.getForObject(BASE_URL + "transfer/type/" + transferTypeId, String.class);
        return transferTypeText;
    }

    public String getTransferStatusText(Integer transferStatusId) {
        String transferStatusText = restTemplate.getForObject(BASE_URL + "transfer/status/" + transferStatusId, String.class);
        return transferStatusText;
    }

    // HELPER METHOD


} //End of Class

