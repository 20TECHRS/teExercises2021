package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    TransferDAO transferDAO;
    public TransferController(TransferDAO transferDAO) {this.transferDAO = transferDAO;}

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(path = "account/{id}/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer, @PathVariable Integer id) {
        LogAPIRequest.logAPICall("POST - account/" + id + "/transfer");
        return transferDAO.createSendTransfer(transfer);
    }

    @RequestMapping(path = "account/{id}/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfersFromAccountId(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - account/" + id + "/transfer");
        return transferDAO.getTransfersFromAccountId(id);
    }

    @RequestMapping(path = "transfer/status/{id}", method = RequestMethod.GET)
    public String getTransferStatusText(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - transfer/status/" + id);
        return transferDAO.getTransferStatusText(id);
    }

    @RequestMapping(path = "transfer/type/{id}", method = RequestMethod.GET)
    public String getTransferTypeText(@PathVariable Integer id) {
        LogAPIRequest.logAPICall("GET - transfer/type/" + id);
        return transferDAO.getTransferTypeText(id);
    }
}
