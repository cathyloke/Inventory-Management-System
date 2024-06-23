package sd_assign;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

/*
 * The Admin class manages product and inventory operations, sales retrieval,
 * and report generation within the skincare and health system. It provides methods
 * for retrieving, updating products and inventory items.
 * Additionally, it facilitates the retrieval of client sales data and the generation
 * of sales and inventory reports. The class incorporates user input handling and
 * uses ArrayLists to manage product, inventory, and sales information.
 */

public class Admin extends User {
	// Constructor
	public Admin(String id, String name, String username, String password, String email, String phone, String address) {
		super(id, name, username, password, email, phone, address);
	}
	
	// Method to manage product operations
	public static void manageProductMenu(ArrayList<Product> productList) {
		Scanner sc = new Scanner(System.in);
		String divider = "----------------------------------------------------------------------------------------------------";
		boolean loop = true;
		String category;
		int option;
			
		while (loop) {
			// Display product menu
			System.out.printf("\n" + divider + "\n%62s\n" + divider, "Manage Product List Page");
			System.out.println();
			// Display list of products
			System.out.printf("%60s\n","List of Products");
			System.out.printf("%60s\n","----------------");
			// Display table header
			System.out.printf(" %-5s  %-60s  %10s  %8s\n", "ID", "NAME", "CATEGORY", "PRICE");
			System.out.printf("%-5s  %-60s  %12s  %8s\n", "-----", "------------------------------------------------------------", "----------", "--------");
			
			// Display products
			for (Product product : productList) {
				System.out.printf(" %-5s  %-60s  %10s  %8.2f\n", product.getId(), product.getName(), product.getCategory(), product.getPrice());            
			}
			
			// Display menu options
			System.out.print(divider + "\n1. Create product\n2. Retrieve product\n3. Update product\n4. Delete product\n5. Exit\nSelect your option (1/2/3/4/5): ");
			option = sc.nextInt();
			
			// Switch case based on user's option
			switch (option) {
				// Prompt user to enter category and create product accordingly and handle invalid category input
				case 1: {
					do {
						System.out.printf("\n" + divider + "\n%56s\n" + divider, "Add Product");
						System.out.println();
						System.out.print("Enter category (skincare/health) : ");
						category = sc.next();
						if (category.equals("skincare")) {
							SkinCareProduct product = new SkinCareProduct();
							product.addProduct();
						} else if (category.equals("health")) {
							HealthProduct product = new HealthProduct();
							product.addProduct();
						} else {
							System.out.print("Invalid category. Please try again. ");	
						}
					} while (!category.equals("skincare") && !category.equals("health"));
					break;
				}
				
				// Prompt user to enter category and retrieve product accordingly and handle invalid category input
				case 2: {	
					do {
						System.out.printf("\n" + divider + "\n%58s\n" + divider, "Retrieve Product");
						System.out.println();
						System.out.print("Enter category (skincare/health) : ");
						category = sc.next();
						if (category.equals("skincare")) {
							SkinCareProduct product = new SkinCareProduct();		
							product.retrieveProduct();
						} else if (category.equals("health")) {
							HealthProduct product = new HealthProduct();
							product.retrieveProduct();
						} else {
							System.out.print("Invalid category. Please try again. ");	
						}
					} while (!category.equals("skincare") && !category.equals("health"));
					break;
				}
				
				// Prompt user to enter category and update product accordingly and handle invalid category input
				case 3: {
					do {
						System.out.printf("\n" + divider + "\n%50s\n" + divider, "Update Product");
						System.out.println();
						System.out.print("Enter category (skincare/health) : ");
						category = sc.next();
						if (category.equals("skincare")) {
							SkinCareProduct product = new SkinCareProduct();
							product.updateProduct();
						} else if (category.equals("health")) {
							HealthProduct product = new HealthProduct();
							product.updateProduct();
						} else {
							System.out.print("Invalid category. Please try again. ");	
						}
					} while (!category.equals("skincare") && !category.equals("health"));
					break;
				}
				
				// Prompt user to enter category and delete product accordingly and handle invalid category input
				case 4: {
					do {
						System.out.printf("\n" + divider + "\n%50s\n" + divider, "Delete Product");
						System.out.println();
						
						System.out.print("Enter category (skincare/health) : ");
						category = sc.next();
						if (category.equals("skincare")) {
							SkinCareProduct product = new SkinCareProduct();
							product.deleteProduct();
						} else if (category.equals("health")) {
							HealthProduct product = new HealthProduct();
							product.deleteProduct();
						} else {
							System.out.print("Invalid category. Please try again.");	
						}
					} while (!category.equals("skincare") && !category.equals("health"));
					break;
				}
				
				case 5:
					System.out.println("Exiting...");
					loop = false;
					break;
				default:
					System.out.println("\nInvalid option. Please try again.");
			}
		}
	}
		
	// Method to manage inventory operations
	public static void manageInventoryMenu(ArrayList<Inventory> inventoryList) {
		Scanner sc = new Scanner(System.in);
		String divider = "----------------------------------------------------------------------------------------------------";	//100-
		boolean loop = true;
		int option;
		
		while (loop) {
			
			System.out.printf("\n" + divider + "\n%60s\n" + divider, "Manage Inventory Menu");
	        System.out.println();
	        System.out.printf("%60s\n","List of Inventory");
			System.out.printf("%60s\n","-----------------");
			
			System.out.printf(" %-5s  %-5s  %10s\n", "ID", "QUANTITY", "EXPIRY DATE");
			System.out.printf("%-5s   %-5s   %10s\n", "-----", "-------", "-----------");
			
			for (Inventory inventory : inventoryList) {
				System.out.printf(" %-5s   %5s     %10s\n", 
						inventory.getProductId(), 
						inventory.getQuantity(), 
						inventory.getExpDate());            
			}
			
			
	        System.out.print("1. Add inventory item\n2. Retrieve inventory item\n3. Update inventory item\n4. Delete inventory item\n5. Exit\nSelect your option (1/2/3/4/5): ");
	        option = sc.nextInt();
	        
	        switch (option) {
	        case 1:
	        	// Add inventory item
	        	System.out.printf("\n" + divider + "\n%56s\n" + divider, "Add Inventory Item");
	        	System.out.println();
	        	System.out.print("Enter product ID: ");
	        	String productId = sc.next();
	        	System.out.print("Enter expiry date (DD-MM-YYYY): ");
	        	String expDate = sc.next();
	        	System.out.print("Enter quantity: ");
	        	int quantity = sc.nextInt();
	        	Inventory newItem = new Inventory(productId, quantity, expDate);
	        	inventoryList.add(newItem);

	        	// Write to file
	        	Inventory.writeInventoryFile(inventoryList);
	        	
	        	// Print the newly created inventory item
	        	System.out.println("Newly added inventory:");
	        	System.out.println("Product ID : " + newItem.getProductId());
	            System.out.println("Expiry date: " + newItem.getExpDate());
	            System.out.println("Quantity   : " + newItem.getQuantity());
	        	
	        	System.out.println("Inventory item added successfully.");
	        	break;
	        	
	        case 2:
	        	// Retrieve inventory item
	        	System.out.printf("\n" + divider + "\n%56s\n" + divider, "Retrieve Inventory Item");
	        	System.out.println();
	        	System.out.print("Enter product ID: ");
	        	productId = sc.next();
	        	ArrayList<Inventory> matchingItems = Inventory.retrieveInventoryItem(productId);
	        	if (matchingItems.isEmpty()) {
	        		  System.out.println("Inventory item not found.");
        		} else {
        		  System.out.println("Inventory items found for product ID " + productId);
        		  System.out.println("----------------------------------------------------------");
        		  System.out.println("Product ID\tQuantity\tExpiry Date");
        		  System.out.println("----------------------------------------------------------");
        		  
        		  // Iterate through matchingItems and display details of each item
        		  for (Inventory item : matchingItems) {
        		      System.out.println(item.getProductId() + "\t\t" + item.getQuantity() + "\t\t" + item.getExpDate());
        		  }
        		  
        		  System.out.println("----------------------------------------------------------");     
        		}
	        	break;
	        	
	        case 3:
	        	// Update inventory item
	        	System.out.printf("\n" + divider + "\n%56s\n" + divider, "Update Inventory Item");
	        	System.out.println();
	        	System.out.print("Enter product ID: ");
	        	productId = sc.next();
	        	
	        	if (Inventory.retrieveInventoryItem(productId) == null) {
	        		System.out.println("Inventory item not found.");
	        		break;
	        	} else {
	        		for (Inventory updateItem : inventoryList) {
		        		if (updateItem.getProductId().equals(productId)) {
		        			System.out.print("Enter new product ID: ");
		        			productId = sc.next();
		        			System.out.print("Enter new expiry date (DD-MM-YYYY): ");
		        			expDate = sc.next();
		        			System.out.print("Enter new quantity: ");
		    	        	quantity = sc.nextInt();
		    	        	
		    	        	updateItem.setProductId(productId);
		    	        	updateItem.setExpDate(expDate);
		    	        	updateItem.setQuantity(quantity);
		    	        	
		    	        	System.out.println("Inventory item updated successfully.");
		    	        	Inventory.updateInventoryItem(productId, expDate, quantity);
		    	        	
		    	        	System.out.println("Updated inventory:");
		    	        	System.out.println("Product ID : " + updateItem.getProductId());
		    	        	System.out.println("Expiry date: " + updateItem.getExpDate());
		    	        	System.out.println("Quantity   : " + updateItem.getQuantity());
		        		}
		        	}
	        	}
	        	break;
	        	
	        case 4:
	            // Delete inventory item
	            System.out.printf("\n" + divider + "\n%56s\n" + divider, "Delete Inventory Item");
	            System.out.println();
	            System.out.print("Enter product ID: ");
	            productId = sc.next();
	            
	            // Retrieve inventory items with the specified product ID
	            ArrayList<Inventory> itemsToDelete = Inventory.retrieveInventoryItem(productId);
	            if (itemsToDelete.isEmpty()) {
	                System.out.println("Inventory item not found.");
	            } else {
	                System.out.println("\nInventory items found for product ID " + productId);
	                System.out.println("----------------------------------------------------------");
	                System.out.println("No\tProduct ID\tQuantity\tExpiry Date");
	                System.out.println("----------------------------------------------------------");
	                
	                // Display details of the found inventory items along with numbers for selection
	                int num = 0;
	                for (Inventory item : itemsToDelete) {
	                    System.out.printf("| %2s | %-15s | %-13s | %-15s |\n",
	                        ++num,
	                        item.getProductId(),
	                        item.getQuantity(),
	                        item.getExpDate());
	                }
	                
	                System.out.println("----------------------------------------------------------");
	                
	                System.out.print("Enter Product No to delete	: ");
	                int noInput = sc.nextInt();
	                
	                while (noInput > itemsToDelete.size() || noInput <= 0) {
	                	System.out.print("\nInvalid Product No. Please try again.\nProduct No	: ");
	                	noInput = sc.nextInt();
	                }
	                
	                System.out.println();
	                
	                // Confirmation prompt
	        		System.out.print("Delete this item (y/n)? ");
	        		String confirmation = sc.next();
	        		  
	        		if (confirmation.equalsIgnoreCase("y")) {
	        			inventoryList.remove(itemsToDelete.get(noInput - 1));
	        			Inventory.writeInventoryFile(inventoryList);
	        			System.out.println("Inventory item deleted successfully.");
	        		} else {
	        			System.out.println("Deletion cancelled.");
	        		}
	            }
	            break;

	        case 5:
	        	loop = false;
	        	System.out.println("Exiting...");
	        	break;
	        	
	        default:
	        	System.out.println("Invalid option. Please try again.");
	        }
		}
	}
	
	// Method to retrieve client sales data
	public static void retrieveClientSales(ArrayList <ClientSales> clientSalesList) {
		// Create a temporary ArrayList to store the ClientSales objects
	    ArrayList<ClientSales> tempSalesList = new ArrayList<>();
	    tempSalesList.clear();
	    
	    // Prompt the user to enter the client ID
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter client ID: ");
	    String clientId = scanner.next();
	    
	    // Iterate through the clientSalesList to find sales with the given client ID
	    for (ClientSales clientSales : clientSalesList) {
	        if (clientSales.getClientID().equals(clientId)) {
	            // If the client ID matches, add the ClientSales object to the temporary list
	            tempSalesList.add(clientSales);
	        }
	    }
	    
	    // Check if any sales were found for the given client ID
	    if (tempSalesList.isEmpty()) {
	        System.out.println("No sales found for the given client ID.");
	    } else {
	        // Print the sales data for the given client ID
	        System.out.println("\nSales for Client ID: " + clientId);
	        System.out.println("----------------------------------------------------------");
	        System.out.println("SalesID\tClientID\tDate\tProductID\tQuantity");
	        System.out.println("----------------------------------------------------------");
	        for (ClientSales sales : tempSalesList) {
	            System.out.println(sales.getSalesID() + "\t" + sales.getClientID() + "\t" + 
	                               sales.getDate() + "\t" + sales.getProductID() + "\t\t" + sales.getQuantity());
	        }
	        System.out.println("----------------------------------------------------------");     
	    }	
	}
	
	// Method to generate sales and inventory reports
	public static void generateReport(ArrayList <ClientSales> clientSalesList, ArrayList <Inventory> inventoryList, ArrayList <Product> productList) {
		Scanner sc = new Scanner(System.in);
		String divider = "----------------------------------------------------------------------------------------------------";
		int option;
		boolean loop = true;
		// Loop until the user chooses to exit the report generation menu
		while (loop) {
			// Display report generation menu
			System.out.printf("\n" + divider + "\n%60s\n" + divider, "Generate Report Menu");
		    System.out.println();
		    
		    System.out.print("1. Sales report\n2. Inventory report\n3. Exit\nSelect your option : ");
		    option = sc.nextInt();
		    System.out.println();
		    
		    switch(option) {
		    case 1: { 
		    	// Generate sales report
		    	SalesReport salesReport = new SalesReport();
		    	salesReport.generateReport(clientSalesList, inventoryList, productList);
		        break;
		    }
		    
		    case 2: { 	
		    	// Generate inventory report
		    	InventoryReport inventoryReport = new InventoryReport();
		    	inventoryReport.generateReport(clientSalesList, inventoryList, productList);
		        break;
		    }
		    case 3:
		    	loop = false;
		    	break;
		    default:
		    	System.out.println("\nInvalid option. Please try again.");
		    }
		}
	}
}
