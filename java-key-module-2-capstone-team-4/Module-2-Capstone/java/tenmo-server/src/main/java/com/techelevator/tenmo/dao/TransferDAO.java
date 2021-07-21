package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDAO {
    public Transfer createSendTransfer(Transfer transfer);
    public List<Transfer> getTransfersFromAccountId(Integer accountId);
    public String getTransferTypeText(Integer transferTypeId);
    public String getTransferStatusText(Integer transferStatusId);
}
