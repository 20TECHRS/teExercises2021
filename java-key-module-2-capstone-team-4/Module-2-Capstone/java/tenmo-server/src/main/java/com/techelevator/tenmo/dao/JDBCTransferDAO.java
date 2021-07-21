package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTransferDAO implements TransferDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCTransferDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Transfer createSendTransfer(Transfer transfer) {
        String sqlCreateTransfer = "INSERT INTO transfers(transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES(?, ?, ?, ?, ?, ?);";

        transfer.setTransferId(getNextTransferId());
        transfer.setTransferTypeId(getTransferTypeID());
        transfer.setTransferStatusId(getTransferStatusID());

        jdbcTemplate.update(sqlCreateTransfer, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

        return transfer;
    }

    @Override
    public List<Transfer> getTransfersFromAccountId(Integer accountId) {
        List<Transfer> transfers = new ArrayList();
        String sqlSelectTransfers = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectTransfers, accountId, accountId);

        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }

        return transfers;
    }

    @Override
    public String getTransferTypeText(Integer transferTypeId) {
        String sqlTransferTypeText = "SELECT transfer_type_desc FROM transfer_types WHERE transfer_type_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTransferTypeText, transferTypeId);

        if(results.next()) {
            return results.getString(1);
        }
        return null;
    }

    @Override
    public String getTransferStatusText(Integer transferStatusId) {
        String sqlTransferStatusText = "SELECT transfer_status_desc FROM transfer_statuses WHERE transfer_status_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTransferStatusText, transferStatusId);

        if(results.next()) {
            return results.getString(1);
        }
        return null;
    }

    //HELPER METHODS

    private Integer getNextTransferId() {
        String selectNextTransferId = "SELECT nextval('seq_transfer_id')";
        SqlRowSet nextTransferId = jdbcTemplate.queryForRowSet(selectNextTransferId);

        if (nextTransferId.next()) {
            return nextTransferId.getInt(1);
        }
        else {
            throw new RuntimeException("There was an error getting the next Transfer Id");
        }
    }

    private Integer getTransferTypeID() {
        String sqlGetTransferTypeID = "SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = ?";
        SqlRowSet transferTypeID = jdbcTemplate.queryForRowSet(sqlGetTransferTypeID, "Send");

        if (transferTypeID.next()) {
            return transferTypeID.getInt(1);
        } else {
            throw new RuntimeException("There was an error getting the next Transfer Type Id");
        }
    }

    private Integer getTransferStatusID() {
        String sqlGetTransferStatusID = "SELECT transfer_status_id FROM transfer_statuses WHERE transfer_status_desc = ?";
        SqlRowSet transferStatusID = jdbcTemplate.queryForRowSet(sqlGetTransferStatusID, "Approved");

        if (transferStatusID.next()) {
            return transferStatusID.getInt(1);
        } else {
            throw new RuntimeException("There was an error getting the next Transfer Status Id");
        }
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setAmount(results.getDouble("amount"));
        return transfer;
    }

}
