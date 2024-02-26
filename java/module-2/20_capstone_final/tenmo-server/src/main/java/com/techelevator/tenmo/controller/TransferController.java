package com.techelevator.tenmo.controller;

import java.security.Principal;

import javax.validation.Valid;

import com.techelevator.tenmo.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;

import com.techelevator.tenmo.model.NewTransferDto;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferAuthorization;
import com.techelevator.tenmo.model.TransferStatusUpdateDto;
import com.techelevator.tenmo.model.User;

@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id, Principal principal) {
		Transfer transfer;
		try {
			transfer = transferDao.getTransferById(id);
		} catch (DaoException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		validateAuthorizationToView(principal, transfer);
		return transfer;
    }

	@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody NewTransferDto transferDTO, Principal principal) {
    	Transfer transfer = buildTransferFromTransferDTO(transferDTO);
    	validateAuthorizationToCreate(principal, transfer);
    	if (transfer.isApproved()) {
    		transferBucksBetweenAccounts(transfer);
    	}
		try {
			return transferDao.createTransfer(transfer);
		} catch (DaoException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(path = "/{transferId}", method = RequestMethod.PUT)
	public Transfer updateTransferStatus(@PathVariable int transferId, @Valid @RequestBody TransferStatusUpdateDto dto, Principal principal) {
    	String newStatus = dto.getTransferStatus();

		Transfer transfer;
		try {
			transfer = transferDao.getTransferById(transferId);
		} catch (DaoException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

    	if (transfer != null) {
			validateAuthorizationToUpdateStatus(principal, transfer);
			if (Transfer.TRANSFER_STATUS_APPROVED.equals(newStatus)) {
				transfer.approve();
				transferBucksBetweenAccounts(transfer);
			} else if (Transfer.TRANSFER_STATUS_REJECTED.equals(newStatus)) {
				transfer.reject();
			}

			try {
				return transferDao.updateTransferStatus(transfer);
			} catch (DaoException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
    }

	private Transfer buildTransferFromTransferDTO(NewTransferDto transferDTO) {
		try {
			User userFrom = userDao.getUserById(transferDTO.getUserFrom());
			User userTo = userDao.getUserById(transferDTO.getUserTo());

			return new Transfer(transferDTO.getTransferType(),
					userFrom,
					userTo,
					transferDTO.getAmount());
		} catch (DaoException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void transferBucksBetweenAccounts(Transfer transfer) {
		try {
			Account accountFrom = accountDao.getAccountByUserId(transfer.getUserFrom().getId());
			Account accountTo = accountDao.getAccountByUserId(transfer.getUserTo().getId());
			accountFrom.transfer(accountTo, transfer.getAmount());
			accountDao.updateAccountBalance(accountFrom);
			accountDao.updateAccountBalance(accountTo);
		} catch (DaoException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void validateAuthorizationToView(Principal principal, Transfer transfer) {
		TransferAuthorization auth = new TransferAuthorization(principal, transfer);
        if (!auth.isAllowedToView()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
	}
	
	private void validateAuthorizationToCreate(Principal principal, Transfer transfer) {
		TransferAuthorization auth = new TransferAuthorization(principal, transfer);
        if (!auth.isAllowedToCreate()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
	}
	
	private void validateAuthorizationToUpdateStatus(Principal principal, Transfer transfer) {
		TransferAuthorization auth = new TransferAuthorization(principal, transfer);
        if (!auth.isAllowedToApproveOrReject()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
	}
}
