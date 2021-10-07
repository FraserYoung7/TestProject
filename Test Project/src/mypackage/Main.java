package mypackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class Main {
	

	public static void main(String[] args) throws ParseException {
		
		TransactionDetails transactionDetails = new TransactionDetails();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		//create transactions
		Transaction transaction1 = new Transaction(formatter.parse("01-11-2020"), "Morrisons", "card", 10.40, "Groceries");
		Transaction transaction2 = new Transaction(formatter.parse("28-10-2020"), "CYBG", "direct debit", 600, "MyMonthlyDD");
		Transaction transaction3 = new Transaction(formatter.parse("28-10-2020"), "PureGym", "direct debit", 40, "MyMonthlyDD");
		Transaction transaction4 = new Transaction(formatter.parse("01-10-2020"), "M&S", "card", 5.99, "Groceries");
		Transaction transaction5 = new Transaction(formatter.parse("30-09-2020"), "McMillan", "internet", 10, "");
		Transaction transaction6 = new Transaction(formatter.parse("01-01-2019"), "Test", "card", 15.99, "Groceries");
		Transaction transaction7 = new Transaction(formatter.parse("28-11-2020"), "Test2", "internet", 30, "Groceries");
		
		//add transaction data
		transactionDetails.add(transaction1);
		transactionDetails.add(transaction2);
		transactionDetails.add(transaction3);
		transactionDetails.add(transaction4);
		transactionDetails.add(transaction5);
		transactionDetails.add(transaction6);
		transactionDetails.add(transaction7);
		
		ArrayList<Transaction> groceries = transactionDetails.getTransactionsByCategory("Groceries");
		System.out.println("All Groceries transactions:");
		for(Transaction transaction : groceries) {
			System.out.println(transaction.toString());
		}
		
		ArrayList<Transaction> monthlydd = transactionDetails.getTransactionsByCategory("MyMonthlyDD");
		System.out.println("\nAll MyMonthlyDD transactions:");
		for(Transaction transaction : monthlydd) {
			System.out.println(transaction.toString());
		}
		
		Map<String,Double> totalOutgoings = transactionDetails.getTotalOutgoings();
		System.out.println("\nTotal outgoing per category: " + totalOutgoings);
		
		Map<Integer,Double> monthlyAverages = transactionDetails.getMonthlyAveragesByCategory("MyMonthlyDD");
		System.out.println("\nMonthly Averages for MyMonthlyDD category: " + monthlyAverages);
		
		Map<Integer,Double> monthlyAverages2 = transactionDetails.getMonthlyAveragesByCategory("Groceries");
		System.out.println("\nMonthly Averages for Groceries category: " + monthlyAverages2);
		
		double highestSpend2020 = transactionDetails.getHighestSpendInCategoryByYear("Groceries", 2020);
		System.out.println("\nGroceries 2020 Highest Spend: " + highestSpend2020);
		
		double highestSpend2019 = transactionDetails.getHighestSpendInCategoryByYear("Groceries", 2019);
		System.out.println("\nGroceries 2019 Highest Spend: " + highestSpend2019);
		
		double lowestSpend2020 = transactionDetails.getLowestSpendInCategoryByYear("MyMonthlyDD", 2020);
		System.out.println("\nMyMonthlyDD 2020 Lowest Spend: " + lowestSpend2020);
		
		double lowestSpend2019 = transactionDetails.getLowestSpendInCategoryByYear("MyMonthlyDD", 2019);
		System.out.println("\nMyMonthlyDD 2019 Lowest Spend: " + lowestSpend2019);
	}

}
