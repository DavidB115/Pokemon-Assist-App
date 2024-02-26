package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private Logger log = LoggerFactory.getLogger(getClass());
    private JdbcTemplate jdbcTemplate;
    private AccountDao accountDao;
    private UserDao userDao;

    private static final String SQL_SELECT_TRANSFER = "SELECT t.transfer_id, tt.transfer_type_desc, ts.transfer_status_desc, t.amount, " +
            "aFrom.account_id as fromAcct, aFrom.user_id as fromUser, aFrom.balance as fromBal, " +
            "aTo.account_id as toAcct, aTo.user_id as toUser, aTo.balance as toBal " +
            "FROM transfer t " +
            "INNER JOIN transfer_type tt ON t.transfer_type_id = tt.transfer_type_id " +
            "INNER JOIN transfer_status ts ON t.transfer_status_id = ts.transfer_status_id " +
            "INNER JOIN account aFrom on account_from = aFrom.account_id " +
            "INNER JOIN account aTo on account_to = aTo.account_id ";

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(SQL_SELECT_TRANSFER);
        try {
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        Transfer transfer = null;
        String sql = SQL_SELECT_TRANSFER + "WHERE transfer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfer;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";
        int transferTypeId = getTransferTypeIdByTypeDesc(transfer.getTransferType());
        int transferStatusId = getTransferStatusIdByStatusDesc(transfer.getTransferStatus());
        Account fromAccount = accountDao.getAccountByUserId(transfer.getUserFrom().getId());
        Account toAccount = accountDao.getAccountByUserId(transfer.getUserTo().getId());
        try {
            int newTransferId = jdbcTemplate.queryForObject(sql, int.class, transferTypeId, transferStatusId, fromAccount.getAccountId(), toAccount.getAccountId(), transfer.getAmount());
            log.debug("created new Transfer with ID: " + newTransferId);
            newTransfer = getTransferById(newTransferId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newTransfer;
    }

    @Override
    public List<Transfer> getTransfersByUserId(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = SQL_SELECT_TRANSFER +
                "WHERE (account_from IN (SELECT account_id FROM account WHERE user_id = ?) " +
                "OR account_to IN (SELECT account_id FROM account WHERE user_id = ?))";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public List<Transfer> getPendingTransfersByUserId(int userId) {
        String sql = SQL_SELECT_TRANSFER + "WHERE transfer_status_id = 1 AND account_from IN (SELECT account_id FROM account WHERE user_id = ?)";
        List<Transfer> transfers = new ArrayList<>();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public Transfer updateTransferStatus(Transfer transfer) {
        Transfer updatedTransfer = null;
        String sql = "UPDATE transfer SET transfer_status_id = ? WHERE transfer_id = ?";
        int transferStatusId = getTransferStatusIdByStatusDesc(transfer.getTransferStatus());
        try {
            int rowsAffected = jdbcTemplate.update(sql, transferStatusId, transfer.getTransferId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedTransfer = getTransferById(transfer.getTransferId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedTransfer;
    }

    private int getTransferTypeIdByTypeDesc(String transferType) {
        String sql = "SELECT transfer_type_id FROM transfer_type WHERE transfer_type_desc = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferType);
            if (results.next()) {
                return results.getInt("transfer_type_id");
            } else {
                throw new DaoException("Unable to lookup transferType " + transferType);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private int getTransferStatusIdByStatusDesc(String transferStatus) {
        String sql = "SELECT transfer_status_id FROM transfer_status WHERE transfer_status_desc = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferStatus);
            if (results.next()) {
                return results.getInt("transfer_status_id");
            } else {
                throw new DaoException("Unable to lookup transferStatus " + transferStatus);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        return new Transfer(rs.getInt("transfer_id"),
                rs.getString("transfer_type_desc"),
                rs.getString("transfer_status_desc"),
                userDao.getUserById(rs.getInt("fromUser")),
                userDao.getUserById(rs.getInt("toUser")),
                rs.getBigDecimal("amount"));
    }
}
