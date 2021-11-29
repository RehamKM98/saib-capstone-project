package com.saib.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	
	//defined filteration methods
	List<Transaction> getTransactionByTransactionType(String transactionType);
	List<Transaction> getTransactionByDate(LocalDateTime date);
	List<Transaction> getTransactionByDateAndTransactionType(LocalDateTime date, String transactionType );

}
