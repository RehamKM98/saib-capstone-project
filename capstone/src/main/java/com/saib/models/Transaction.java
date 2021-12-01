package com.saib.models;

import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private long transactionID; 
	
	@Column(name="from_account")
	private String fromAccount;
	
	@Column(name="to_account")
	private String toAccount; 
	
	@Column(name="from_account_name")
	private String fromAccountName;
	
	@Column(name="to_account_name")
	private String toAccountName; 
	
	@Column(name="same_bank_transaction")
	private boolean sameBankTransaction;
	
	@Column(name="other_bank")
	private String otherBank; 
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="date")
	private LocalDate date; 
	
	@Column(name="time")
	private LocalDateTime time; 
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="status")
	private String status;
	
	
	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(long transactionID, String fromAccount, String toAccount, String fromAccountName,
			String toAccountName, boolean sameBankTransaction, String otherBank, double amount, LocalDate date,
			LocalDateTime time, String transactionType, String status) {
		super();
		this.transactionID = transactionID;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.fromAccountName = fromAccountName;
		this.toAccountName = toAccountName;
		this.sameBankTransaction = sameBankTransaction;
		this.otherBank = otherBank;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.transactionType = transactionType;
		this.status = status;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getFromAccountName() {
		return fromAccountName;
	}
	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}
	public String getToAccountName() {
		return toAccountName;
	}
	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}
	public boolean isSameBankTransaction() {
		return sameBankTransaction;
	}
	public void setSameBankTransaction(boolean sameBankTransaction) {
		this.sameBankTransaction = sameBankTransaction;
	}
	public String getOtherBank() {
		return otherBank;
	}
	public void setOtherBank(String otherBank) {
		this.otherBank = otherBank;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", fromAccountName=" + fromAccountName + ", toAccountName=" + toAccountName
				+ ", sameBankTransaction=" + sameBankTransaction + ", otherBank=" + otherBank + ", amount=" + amount
				+ ", date=" + date + ", time=" + time + ", transactionType=" + transactionType + ", status=" + status
				+ "]";
	} 
	
	
	
}
