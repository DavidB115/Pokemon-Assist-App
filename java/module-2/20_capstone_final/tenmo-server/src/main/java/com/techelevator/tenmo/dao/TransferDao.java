package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    Transfer getTransferById(int transferId);

    Transfer createTransfer(Transfer newTransfer);

    List<Transfer> getTransfersByUserId(int userId);

    List<Transfer> getTransfers();

    List<Transfer> getPendingTransfersByUserId(int currentUserId);

	Transfer updateTransferStatus(Transfer transfer);
}
