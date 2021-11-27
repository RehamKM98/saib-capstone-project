package com.saib.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.saib.models.Transaction;
import com.saib.repository.TransactionRepository;
import com.saib.util.Results;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	
	public List<Transaction> getAllTransaction()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	}
	
	public Transaction getTransactionByTransactionID(long transactionID)
	{
		Optional<Transaction> optional=transactionRepository.findById(transactionID);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction ID :"+transactionID+"doesn't exist");
		}
	}
	
	public String addTransaction(Transaction transaction)
	{
		String result="";
		Transaction storedTransaction= transactionRepository.save(transaction);
		if(storedTransaction!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction was not created");
		}
		
		return result;
	}
	
	public String updateTransaction(Transaction transaction, long transactionID)
	{
		String result="";
		
		transaction.setTransactionID(transactionID);
		Transaction updatedTransaction=transactionRepository.save(transaction);
		
		if(updatedTransaction!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction was not updated");
		}
		return result;	
	}
	
	
	public String deleteTransaction(long transactionID)
	{
		String result="";
		try {
			transactionRepository.deleteById(transactionID);
		
		
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}	
	}
	
}
