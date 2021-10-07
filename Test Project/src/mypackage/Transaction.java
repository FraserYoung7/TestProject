package mypackage;

import java.util.Date;

public class Transaction {
	
	private Date transactionDate;
	private String vendor;
	private String transactionType;
	private double transactionAmount;
	private String category;
	
	public Transaction(Date transactionDate, String vendor, String transactionType, double transactionAmount, String category) {
		this.transactionDate = transactionDate;
		this.vendor = vendor;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.category = category;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String toString() {
		return "Transaction date: " + this.transactionDate + ", Vendor: " + this.vendor + ", Type: " 
				+ this.transactionType + ", Amount: " + this.transactionAmount + ", Category: " + this.category;
	}
}
