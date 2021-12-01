package com.saib.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.saib.models.Account;
import com.saib.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;

import com.saib.services.TransactionService;
import com.saib.util.Results;



@RestController
public class TransactionController {

	
	@Autowired
	TransactionService transactionService;
	
	
	//Displaying all transactions
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions()
	{
		List<Transaction> list= transactionService.getAllTransaction();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Tranacations Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
	}
	
	//display a transaction with specific ID
	@GetMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionID(@PathVariable long transactionID)
	{
		Transaction transaction =transactionService.getTransactionByTransactionID(transactionID);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	//Adding transaction with the same fields in the database 
	//the entary of the date should be in the format of:"yyyy-MM-dd HH:mm:ss" in order for the filter by date works
	@PostMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> addTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(transaction);
		String result=transactionService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Transaction eas created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		return response;
	}
	
	
	//update transaction details by the transaction ID
	@PutMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction , @PathVariable long transactionID)
	{
		String result=transactionService.updateTransaction(transaction, transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	//delete a transaction by providing the ID
	@DeleteMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transactionID)
	{
		String result=transactionService.deleteTransaction(transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	//filter the transaction by the transaction type
	@GetMapping("/transactions/transactionType/{transactionType}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionType(@PathVariable String transactionType)
	{
		List<Transaction> list=transactionService.getTransactionByTransactionType(transactionType);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
	}
	
	
	//filter transactions by transaction ID
	@GetMapping("/transactions/date/{date}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByDate(@RequestParam String date)
	{
		List<Transaction> list=transactionService.getTransactionByDate(date);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
	}
	
	//filter transactions by transaction date and type
	@GetMapping("/transactions/date&transactionType/{date&transactionType}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByDateAndTransactionType(@RequestParam String date, String transactionType)
	{
		List<Transaction> list=transactionService.getTransactionByDateAndTransactionType(date,transactionType);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
	}
	
	
	//pagination without sorting
	@GetMapping("/transactions/all")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		List<Transaction> list=transactionService.getAllTransactions(pageNumber, pageSize );
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction  Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
	}
	
	
	//pagination with sorting 
	@GetMapping("/transactions/all/sorted")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(@RequestParam int pageNumber,@RequestParam int pageSize, @RequestParam String sortBy)
	{
		List<Transaction> list=transactionService.getAllTransactions(pageNumber, pageSize , sortBy);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction  Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
	}

}

