package com.saib.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saib.models.Account;
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
	
	public List<Transaction> getTransactionByTransactionType(String TransactionType){
		
		List<Transaction> transactions=transactionRepository.getTransactionByTransactionType(TransactionType);
		return transactions;
	}
	public List<Transaction> getTransactionByDate(LocalDateTime date){
		
		List<Transaction> transactions=transactionRepository.getTransactionByDate(date);
		return transactions;
	}
	

	public List<Transaction> getTransactionByDateAndTransactionType(LocalDateTime date, String transactionType){

		List<Transaction> transactions=transactionRepository.getTransactionByDateAndTransactionType(date , transactionType);
		return transactions;
	}
	
	public List<Transaction> getAllTransactions ( Integer pageNumber, Integer pageSize){
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		Page <Transaction>PageRequest = transactionRepository.findAll(paging);
		int totalElements = PageRequest.getNumberOfElements();
		int total = PageRequest.getTotalPages();
		System.out.println("Total Numner of Pages Are: "+ total+ "  Total Elements: "+ totalElements);

		if (PageRequest.hasContent()) {
			return PageRequest.getContent();
		}
		else {
			return new ArrayList<Transaction>();
		}
	}
	
	public List<Transaction> getAllTransactions ( Integer pageNumber, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page <Transaction>PageRequest = transactionRepository.findAll(paging);
		int totalElements = PageRequest.getNumberOfElements();
		int total = PageRequest.getTotalPages();
		System.out.println("Total Numner of Pages Are: "+ total+ "  Total Elements: "+ totalElements);

		if (PageRequest.hasContent()) {
			return PageRequest.getContent();
		}
		else {
			return new ArrayList<Transaction>();
		}
	}
	
}
