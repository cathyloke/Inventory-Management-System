package sd_assign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * SalesReport class extends the Report base class to generate a detailed monthly sales report.
 * This report is based on client sales data, calculating total sales for each month and displaying the results.
 * SalesReport class overrides the generateReport method to implement the specific functionality for
 * generating sales reports.
 */

public class SalesReport extends Report {
	String divider = "----------------------------------------------------------------------------------------------------";
	
	private String monthYear;
	private double totalSales;
	public SalesReport(String monthYear, double totalSales) {
		super();
		this.monthYear = monthYear;
        this.totalSales = totalSales;
	}
	public SalesReport() {}
	
	@Override
	public void generateReport(ArrayList <ClientSales> clientSalesList, ArrayList <Inventory> inventoryList, ArrayList <Product> productList) {
    	// Create a list to store monthly sales data
    	List<SalesReport> monthlySalesList = new ArrayList<>();
        
    	// Iterate through client sales data to calculate total sales per month
        for (ClientSales clientSales : clientSalesList) {
        	try {
        		// Parse sale date and format it to get month and year
	        	String dateString = clientSales.getDate();
	        	SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	        	SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM yyyy");
	        	String monthYear = null;
        	
            	Date saleDate = inputFormat.parse(dateString);
                monthYear = outputFormat.format(saleDate);
                
                // Retrieve product information for the current sale
	        	String selectedProductId = clientSales.getProductID();
	        	Product selectedProduct = null;
	        	
	        	for (Product product : productList) {
	    	        if (product.getId().equals(selectedProductId)) {
	    	        	selectedProduct = product;
	    	        }
	    	    } 
		        
	        	// Calculate sales amount for the current sale
                double sales = clientSales.getQuantity() * selectedProduct.getPrice();
                // Update total sales for the corresponding month
                boolean found = false;
	            for (SalesReport monthlySales : monthlySalesList) {
	                if (monthlySales.monthYear.equals(monthYear)) {
	                    monthlySales.totalSales += sales;
	                    found = true;
	                    break;
	                }
	            }
	            // If no entry exists for the month, add a new entry
	            if (!found) {
	                monthlySalesList.add(new SalesReport(monthYear, sales));
	            }
            } catch (ParseException e) {
                // Handle parsing exception
                System.out.println("Error parsing date: " + e.getMessage());
            }
            
        }

        // Display sales report
        System.out.printf(divider + "\n%56s\n" + divider, "Sales Report");
        System.out.printf("\n | %12s | %20s |", "Month Year", "Total Sales");
        System.out.printf("\n |--------------|----------------------|");
        System.out.println();
        double total = 0;
        for (SalesReport monthlySales : monthlySalesList) {
        	total += monthlySales.totalSales;
            System.out.printf(" | %12s | %20.2f |", monthlySales.monthYear, monthlySales.totalSales);
            System.out.println();
        }
    	
        System.out.printf("-----------------------------------------");
        System.out.printf("\nTotal sales amount : %17.2f\n", total);
	}
}
