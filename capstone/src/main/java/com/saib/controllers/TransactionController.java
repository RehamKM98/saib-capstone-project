package com.saib.controllers;

import java.util.List;

import com.saib.models.Account;
import com.saib.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;

import com.saib.services.TransactionService;
import com.saib.util.Results;



@RestController
public class TransactionController {

	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions()
	{
		List<Transaction> list= transactionService.getAllTransaction();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Tranacations Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionID(@PathVariable long transactionID)
	{
		Transaction transaction =transactionService.getTransactionByTransactionID(transactionID);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
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
	
	
	@PutMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction , @PathVariable long transactionID)
	{
		String result=transactionService.updateTransaction(transaction, transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/transactions/{transactionID}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transactionID)
	{
		String result=transactionService.deleteTransaction(transactionID);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
}

