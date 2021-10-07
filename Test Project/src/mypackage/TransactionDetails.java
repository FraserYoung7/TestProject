package mypackage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TransactionDetails {
	
	private ArrayList<Transaction> transactions;
	
	public TransactionDetails() {
		transactions = new ArrayList<>();
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void add(Transaction t) {
		this.transactions.add(t);
	}
	
	/** Return list of transactions for a given category - latest first */
	public ArrayList<Transaction> getTransactionsByCategory(String category) {
		
		//create new ArrayList to hold list of transactions of that category
		ArrayList<Transaction> categoryTransactions = new ArrayList<>();
		
		//loop list to check for transactions of that category
		for(Transaction transaction : transactions) {
			if(transaction.getCategory().equalsIgnoreCase(category)) {
				//add transaction of category to the list
				categoryTransactions.add(transaction);
			}
		}
		
		// sort into date order
		Collections.sort(categoryTransactions, new Comparator<Transaction>() {
			  public int compare(Transaction o1, Transaction o2) {
			      return o2.getTransactionDate().compareTo(o1.getTransactionDate());
			  }
			});
		
		return categoryTransactions;
	}
	
	/** Return list of transactions for a given category - latest first */
	private ArrayList<Transaction> getTransactionsInCategoryByYear(String category, int year) {
		
		//create new ArrayList to hold list of transactions of that category/year
		ArrayList<Transaction> categoryTransactionsInYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		
		//loop list to check for transactions of that category/year
		for(Transaction transaction : transactions) {
			
			// set current transaction year
			calendar.setTime(transaction.getTransactionDate());
			int transactionYear = calendar.get(Calendar.YEAR);
			
			if(transaction.getCategory().equalsIgnoreCase(category) && transactionYear == year) {
				//add transaction of category/year to the list
				categoryTransactionsInYear.add(transaction);
			}
		}
		
		return categoryTransactionsInYear;
	}
	
	private ArrayList<Transaction> getTransactionsInCategoryByMonth(String category, int month) {
		
		//create new ArrayList to hold list of transactions of that category/month
		ArrayList<Transaction> categoryTransactionsInMonth = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		
		//loop list to check for transactions of that category/month
		for(Transaction transaction : transactions) {
			
			// set current transaction month
			calendar.setTime(transaction.getTransactionDate());
			int transactionMonth = calendar.get(Calendar.MONTH);
			
			if(transaction.getCategory().equalsIgnoreCase(category) && transactionMonth == month) {
				//add transaction of category/month to the list
				categoryTransactionsInMonth.add(transaction);
			}
		}
		
		return categoryTransactionsInMonth;
	}
	
	/** Return total outgoing per category */
	public Map<String, Double> getTotalOutgoings(){
	
		Map<String, Double> totalOutgoings = new HashMap<String, Double>();
		
		for(Transaction transaction : transactions) {
			double totalOutgoingByCategory = getTotalOutgoingByCategory(transaction.getCategory());
			if(!(totalOutgoings.containsKey(transaction.getCategory()))) {
				totalOutgoings.put(transaction.getCategory(), totalOutgoingByCategory);
			}
		}
		
		return totalOutgoings;
	}
	
	/** Return total outgoing for given category*/
	private double getTotalOutgoingByCategory(String category) {
		
		double totalOutgoings = 0;
		
		//loop list to check for transactions of that category
		for(Transaction transaction : transactions) {
			if(transaction.getCategory().equalsIgnoreCase(category)) {
				//update totalOutgoings for that category
				totalOutgoings += transaction.getTransactionAmount();
			}
		}
		
		return totalOutgoings;
		
	}
	
	/** Return monthly averages per category */
	public Map<Integer, Double> getMonthlyAveragesByCategory(String category){
	
		Map<Integer, Double> monthlyAverages = new HashMap<Integer, Double>();
		double monthlyAverage = 0;
		
		for(int i = 0; i<12; i++) {
			monthlyAverage = getMonthlyAverageByCategory(category, i);
			monthlyAverages.put(i+1, monthlyAverage);
		}
		
		return monthlyAverages;
	}
	
	
	private double getMonthlyAverageByCategory(String category, int month) {
		
		double monthlyAverage = 0;
		ArrayList<Transaction> categoryTransactionsInMonth = getTransactionsInCategoryByMonth(category, month);

		if(categoryTransactionsInMonth.size() > 0) {
			//loop list to check for transactions of that category
			for(Transaction transaction : categoryTransactionsInMonth) {
				//update totalOutgoings for that category
				monthlyAverage += transaction.getTransactionAmount();
			}

			monthlyAverage /= categoryTransactionsInMonth.size();

			return monthlyAverage;
		}
		else {
			return 0;
		}
	}

	/** Returns highest spend in a given category/year */
	public double getHighestSpendInCategoryByYear(String category, int year) {

		ArrayList<Transaction> categoryTransactionsInYear = getTransactionsInCategoryByYear(category, year);

		//get highest spend transaction
		if(categoryTransactionsInYear.size() > 0) {
			Transaction highestSpend = Collections.max(categoryTransactionsInYear, new Comparator<Transaction>() {
				public int compare(Transaction o1, Transaction o2) {
					return o1.getTransactionAmount() >= o2.getTransactionAmount() ? 1 : -1;
				}
			});

			return highestSpend.getTransactionAmount();
		}
		else {
			return 0;
		}

	}

	/** Returns lowest spend in a given category/year*/
	public double getLowestSpendInCategoryByYear(String category, int year) {

		ArrayList<Transaction> categoryTransactionsInYear = getTransactionsInCategoryByYear(category, year);

		//get lowest spend transaction
		if(categoryTransactionsInYear.size() > 0) {
			Transaction lowestSpend = Collections.min(categoryTransactionsInYear, new Comparator<Transaction>() {
				public int compare(Transaction o1, Transaction o2) {
					return o1.getTransactionAmount() >= o2.getTransactionAmount() ? 1 : -1;
				}
			});

			return lowestSpend.getTransactionAmount();
		}
		else {
			return 0;
		}
	}

}
