package sd_assign;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/*
 * InventoryReport class extends the Report base class to generate a detailed inventory report.
 * This report includes a list of inventory items sorted by their expiry dates, displaying each item's
 * product ID, product name, quantity, and expiry date. The class uses sorting to organise data.
 */

public class InventoryReport extends Report {
	static String divider = "----------------------------------------------------------------------------------------------------";
	
	// Constructor for InventoryReport that initialises the report with default settings
	public InventoryReport() {
		super();	
	}
	
	// Generates an inventory report that lists products sorted by their expiry dates
	@Override
	public void generateReport(ArrayList <ClientSales> clientSalesList, ArrayList <Inventory> inventoryList, ArrayList <Product> productList) {
		// Sorts inventory list by expiry dates in ascending order
        Collections.sort(inventoryList, new Comparator<Inventory>() {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            @Override
            public int compare(Inventory item1, Inventory item2) {
                try {
                    Date expDate1 = dateFormat.parse(item1.getExpDate());
                    Date expDate2 = dateFormat.parse(item2.getExpDate());
                    return expDate1.compareTo(expDate2);
                } catch (ParseException e) {
                	// Logs error if expiry date can't be parsed
                	e.printStackTrace();
                    return 0;
                }
            }
        });
        
        // Printing header for inventory report
        System.out.printf(divider + "\n%58s\n" + divider, "Inventory Report");
        System.out.println();
        System.out.printf("%68s\n%70s\n", "Inventory Sorted by Expiry Date", "-----------------------------------");
        System.out.printf(" | %-10s | %-68s | %10s | %15s |", "Product ID", "Product Name", "Quantity", "Expiration Date");
        System.out.printf("\n |------------|----------------------------------------------------------------------|------------|-----------------|");
        // Iterate through each item in the sorted inventory list and print their details
        for (Inventory item : inventoryList) {
        	String selectedProductId = item.getProductId();
        	Product selectedProduct = null;
        	// Match each inventory item with its corresponding product details
        	for (Product product : productList) {
    	        if (product.getId().equals(selectedProductId)) {
    	        	selectedProduct = product;
    	        }
    	    } 
        	// Print the inventory item details including the matched product name
        	System.out.printf("\n | %-10s | %-68s | %10s | %15s |", item.getProductId(), selectedProduct.getName(), item.getQuantity(), item.getExpDate()); 
        }
        System.out.println();
	}
}
