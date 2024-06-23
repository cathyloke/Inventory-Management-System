package sd_assign;

import java.util.ArrayList;

/*
 * This abstract Report class acts as a base for different report types.
 * It defines a common framework to generate reports, ensuring all derived classes implement
 * the needed report generation functionality. This design promotes reusability and consistency across
 * different report types.
 *
 * Derived classes are expected to implement generateReport method to generate specific reports types
 * based on provided lists of client sales data, inventory, product details.
 */

public abstract class Report {

	// Default constructor
	// Mainly ensures derived classes can invoke super()
	public Report() {
		
	}

	// Abstract method to be implemented by derived classes to generate a report
	public abstract void generateReport(ArrayList<ClientSales> clientSalesList, ArrayList<Inventory> inventoryList, ArrayList<Product> productList);
}
