package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import main.Transaction;
import main.TransactionDetails;

public class TransactionsTest extends TestCase{
	
	private TransactionDetails td;
	private SimpleDateFormat formatter;
	
    @Before
    public void setUp() throws ParseException {
		td = new TransactionDetails();
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		//create transactions
		Transaction transaction1 = new Transaction(formatter.parse("01-11-2020"), "Morrisons", "card", 10.40, "Groceries");
		Transaction transaction2 = new Transaction(formatter.parse("28-10-2020"), "CYBG", "direct debit", 600, "MyMonthlyDD");
		Transaction transaction3 = new Transaction(formatter.parse("28-10-2020"), "PureGym", "direct debit", 40, "MyMonthlyDD");
		Transaction transaction4 = new Transaction(formatter.parse("01-10-2020"), "M&S", "card", 5.99, "Groceries");
		Transaction transaction5 = new Transaction(formatter.parse("30-09-2020"), "McMillan", "internet", 10, "");
		
		//add transaction data
		td.add(transaction1);
		td.add(transaction2);
		td.add(transaction3);
		td.add(transaction4);
		td.add(transaction5);
    }

    @After
    public void tearDown() {
    	td = null;
    }
	
    @Test
    public void testGetAllTransactionsForGroceries() throws ParseException {
		ArrayList<Transaction> groceries = td.getTransactionsByCategory("Groceries");
		
		assertEquals(groceries.size(), 2);
		assertEquals(groceries.get(0).getTransactionDate(), formatter.parse("01-11-2020"));
		
		Transaction newTrans = new Transaction();
		newTrans.setCategory("Groceries");
		newTrans.setTransactionAmount(100.00);
		newTrans.setTransactionType("internet");
		newTrans.setVendor("Test Place");
		newTrans.setTransactionDate(formatter.parse("01-01-2021"));
		td.add(newTrans);
		
		groceries = td.getTransactionsByCategory("Groceries");
		
		assertEquals(groceries.size(), 3);
		assertEquals(groceries.get(0).toString(), "Transaction date: Fri Jan 01 00:00:00 GMT 2021, Vendor: Test Place, Type: internet, Amount: 100.0, Category: Groceries");
    }
    
    @Test
    public void testGetAllTransactionsForMyMonthlyDD() {
		ArrayList<Transaction> groceries = td.getTransactionsByCategory("MyMonthlyDD");
		
		assertEquals(groceries.size(), 2);
    }
    
    @Test
    public void testGetAllTransactionsForEmptyCategory() {
		ArrayList<Transaction> groceries = td.getTransactionsByCategory("");
		
		assertEquals(groceries.size(), 1);
		assertEquals(groceries.get(0).getVendor(), "McMillan");
    }
    
    @Test
    public void testGetTotalOutgoingByCategory() {
        Map<String,Double> totalOutgoings = td.getTotalOutgoings();
        
		assertEquals(totalOutgoings.get("Groceries"), 16.39);
		assertEquals(totalOutgoings.get("MyMonthlyDD"), 640.00);
    }
    
    @Test
    public void testGetMonthlyAverageSpendByCategory() {
        Map<Integer,Double> groceriesAverage = td.getMonthlyAveragesByCategory("Groceries");
        
		assertEquals(groceriesAverage.get(10), 5.99);
		assertEquals(groceriesAverage.get(1), 0.0);
    }
    
    @Test
    public void testGetHighestSpendInCategoryInYear() {
        double groceriesHighest2020 = td.getHighestSpendInCategoryByYear("Groceries", 2020);
        
		assertEquals(groceriesHighest2020, 10.40);
		
		ArrayList<Transaction> transactions = td.getTransactionsByCategory("Groceries");
		transactions.get(0).setTransactionAmount(500.00);
		
		groceriesHighest2020 = td.getHighestSpendInCategoryByYear("Groceries", 2020);
		assertEquals(groceriesHighest2020, 500.00);
		
		transactions.get(0).setCategory("Shopping");
		transactions.get(1).setCategory("Shopping");
		
		groceriesHighest2020 = td.getHighestSpendInCategoryByYear("Groceries", 2020);
		assertEquals(groceriesHighest2020, 0.00);
    }
    
    @Test
    public void testGetLowestSpendInCategoryInYear() {
        double groceriesLowest2020 = td.getLowestSpendInCategoryByYear("Groceries", 2020);
        
		assertEquals(groceriesLowest2020, 5.99);
		
		ArrayList<Transaction> transactions = td.getTransactionsByCategory("Groceries");
		transactions.get(0).setTransactionAmount(2.00);
		
		groceriesLowest2020 = td.getLowestSpendInCategoryByYear("Groceries", 2020);
		assertEquals(groceriesLowest2020, 2.00);
		
		transactions.get(0).setCategory("Shopping");
		transactions.get(1).setCategory("Shopping");
		
		groceriesLowest2020 = td.getLowestSpendInCategoryByYear("Groceries", 2020);
		assertEquals(groceriesLowest2020, 0.00);
    }
}
