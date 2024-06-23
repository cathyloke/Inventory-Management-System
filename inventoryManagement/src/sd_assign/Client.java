package sd_assign;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * The Client class represents a user of the system who can purchase products
 * and view past purchases.
 */

public class Client extends User{
	// Constructor
	public Client(String id, String name, String username, String password, String email, String phone, String address) {
		super(id, name, username, password, email, phone, address);
	}
	
	// Represents a shopping cart item with inventory and quantity.
	class Cart {
		Inventory inventory;
		int count;
		
		public Inventory getInventory() {
			return inventory;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public Cart(Inventory inventory, int count) {
			this.inventory = inventory;
			this.count = count;
		}
	}
	
	/*
	 * Allows the client to purchase products by adding them to the shopping cart,
	 * proceeding to payment, and updating inventory and sales records accordingly.
	 */
	public void purchaseProducts(ArrayList <Inventory> inventoryList, ArrayList <Product> productList, ArrayList <ClientSales> clientSalesList) {
		String divider = "----------------------------------------------------------------------------------------------------";
		Scanner sc = new Scanner(System.in);
		ArrayList <Cart> cartList = new ArrayList<>(); 
		
		boolean loop = true;
        
		while (loop) {
			// Display purchase product menu
			System.out.println();	
			System.out.printf("\n" + divider + "\n%58s\n" + divider, "Purchase Product");
			System.out.println();
			
			// List products available for purchase with their details
			System.out.println("\n| No  | Product Name                                                        | Expiry Date | Inventory Count | Price ($) |");
	        System.out.println("|-----|---------------------------------------------------------------------|-------------|-----------------|-----------|");
	
	        for (int i = 0; i < inventoryList.size(); i++) {
	        
	        	Inventory inventory = inventoryList.get(i);
	        	String productId = inventory.getProductId();
	        	Product product = null;
	        	
	    	    for (Product products : productList) {
	    	        if (products.getId().equals(productId)) {
	    	        	product = products;
	    	        }
	    	    } 
	        	System.out.printf("| %3s | %-68s| %11s | %15d | %9.2f |%n", i + 1, product.getName(), inventory.getExpDate(), inventory.getQuantity(), product.getPrice());
	        }
	        
	        // Display options to manage the cart and proceed to payment
	        System.out.println(divider + "\nOptions : ");
	        System.out.println(divider + "\nOptions : ");  
			System.out.println("1. Manage cart");
	        System.out.println("2. Proceed to payment (Exit if no items are added to cart)");
	        System.out.println("3. Exit");
	        System.out.print("Enter your choice: ");
	        int choice = sc.nextInt();
			String op; 
			System.out.println();
				
			switch (choice) {
	        case 1:
	        	// Option to manage cart contents
	        	manageCart(cartList, inventoryList, productList);
	        	break;
	        case 2: 
	        	// Option to make payment contents
	        	makePayment(cartList, inventoryList, productList, clientSalesList);
	            break;
	        case 3:
	        	System.out.println("Exiting the purchase product page will erase your cart data. Exit page?");
	        	System.out.print("Yes - Y (Or type any other key to exit) : ");
	        	sc.nextLine();
	        	op = sc.nextLine();
	        	
	        	// Clear cart and exit purchase product page when user input Y
	        	if (op.equals("Y")) {
	        		cartList.clear();
	        		loop = false;
	        	}

	        	break;
	        default:
	        	System.out.println("Invalid choice. Please enter 1, 2 or 3.");
	            break;
			}
		}	
	}
	
	public void manageCart(ArrayList <Cart> cartList, ArrayList <Inventory> inventoryList, ArrayList <Product> productList) {
		String divider = "----------------------------------------------------------------------------------------------------";	//100-
		Scanner sc = new Scanner(System.in);
		int noInput, quantityInput, num;
		double total;

		// Display menu options for cart management
		System.out.println(divider + "\nOptions : ");  
		System.out.println("1. Add cart\n2. Retrieve cart\n3. Update cart\n4. Delete cart");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
		System.out.println();
			
		switch (choice) {
        case 1:
        	// Handle adding a new product to the cart
        	System.out.println("------------------------\n\tAdd Cart\n------------------------");
            System.out.print("Product No	: ");
            noInput = sc.nextInt();
            
            // Validate product number input
            while(noInput > inventoryList.size()) {
            	System.out.print("\nInvalid Product No. Please try again.\nProduct No	: ");
	            noInput = sc.nextInt();
            }
            
            System.out.println();
            
            Inventory addCartInventory = inventoryList.get(noInput - 1);
            String addCartProductId = addCartInventory.getProductId();
            Product addCartProduct = null;
            
            // Match product ID from productList to add to cart
            for (Product products : productList) {
    	        if (products.getId().equals(addCartProductId)) {
    	        	addCartProduct = products;
    	        }
    	    } 
            
            // Display details of selected product
            System.out.println("Product number " + noInput + " Details");
    		System.out.println("Product name		: " + addCartProduct.getName());
    		System.out.println("Available quantity	: " + addCartInventory.getQuantity());
    		System.out.println("Price per item		: " + addCartProduct.getPrice());
    		
    		System.out.print("\nEnter the quantity to purchase: ");
            quantityInput = sc.nextInt();
            
            // Add selected product to cart
            cartList.add(new Cart(addCartInventory, quantityInput));
            addCartInventory.setQuantity(addCartInventory.getQuantity() - quantityInput);
            System.out.println("Item added to cart.");
            
            break;
        
        case 2: 
        	// Display all items currently in the cart
        	System.out.printf(divider + "\n%58s\n" + divider, "Retrieve Cart List");
        	System.out.println("\n| No  | Product ID | Product Name                                                        | Quantity | Price ($) | Subtotal |");
	        System.out.println("|-----|------------|---------------------------------------------------------------------|----------|-----------|----------|");
	
	        total = 0;  
	        num = 0;

	        for (Cart cart : cartList) {
	        	Inventory selectedInventory;
	        	selectedInventory = cart.getInventory();
	        	String selectedProductId = selectedInventory.getProductId();
	        	Product selectedProduct = null;
	        	for (Product products : productList) {
	    	        if (products.getId().equals(selectedProductId)) {
	    	        	selectedProduct = products;
	    	        }
	    	    } 
	        			
	        	System.out.printf("| %3s | %-10s | %-67s | %8s | %9s | %8s |\n", 
	        			num = num + 1,
	        			selectedInventory.getProductId(),
	        			selectedProduct.getName(),
	        			cart.getCount(),
	        			selectedProduct.getPrice(),
	    				(selectedInventory.getQuantity() * selectedProduct.getPrice()));
	        	total += (selectedInventory.getQuantity() * selectedProduct.getPrice());
	        }
        	
        	break;
        case 3: 
        	// Handle updating the quantity of a product in the cart
        	// Display cart details
            System.out.printf(divider + "\n%58s\n" + divider, "Update Cart List");
            System.out.println("\n| No  | Product ID | Product Name                                                        | Quantity | Price ($) | Subtotal |");
	        System.out.println("|-----|------------|---------------------------------------------------------------------|----------|-----------|----------|");
	
	        
	        num = 0;
	        // Display cart
	        for (Cart cart : cartList) {
	        	Inventory selectedInventory;
	        	selectedInventory = cart.getInventory();
	        	String selectedProductId = selectedInventory.getProductId();
	        	Product selectedProduct = null;
	        	for (Product products : productList) {
	    	        if (products.getId().equals(selectedProductId)) {
	    	        	selectedProduct = products;
	    	        }
	    	    } 
	        			
	        	System.out.printf("| %3s | %-10s | %-67s | %8s | %9s | %8s |\n", 
	        			num = num + 1,
	        			selectedInventory.getProductId(),
	        			selectedProduct.getName(),
	        			cart.getCount(),
	        			selectedProduct.getPrice(),
	    				(selectedInventory.getQuantity() * selectedProduct.getPrice()));
	        }
	        
	        System.out.print("\nEnter Product No to be updated	: ");
            noInput = sc.nextInt();
            
            // Validate product number input
            while(noInput > cartList.size() || noInput <= 0) {
            	System.out.print("\nInvalid Product No. Please try again.\nProduct No	: ");
	            noInput = sc.nextInt();
            }
            
            Inventory updateCartInventory = cartList.get(noInput - 1).getInventory();
            
    		System.out.print("\nRe-enter the quantity to purchase: ");
            quantityInput = sc.nextInt();

            // Update inventory and cart with new quantity
            for (Inventory inventory: inventoryList) {
            	if (inventory == updateCartInventory) {            		
            		inventory.setQuantity(inventory.getQuantity() - (quantityInput - cartList.get(noInput - 1).getCount()));
            	}
            }
             
            // Update cart list
            cartList.get(noInput - 1).setCount(quantityInput);
            
            System.out.println("Item updated to cart.");
              	
        	break;	
        case 4:
        	// Handle removal of a product from the cart        
        	// Display cart details
            System.out.printf(divider + "\n%58s\n" + divider, "Delete Cart List");
            System.out.println("\n| No  | Product ID | Product Name                                                        | Quantity | Price ($) | Subtotal |");
	        System.out.println("|-----|------------|---------------------------------------------------------------------|----------|-----------|----------|");
	
	        num = 0;
	        // Display cart
	        for (Cart cart : cartList) {
	        	Inventory selectedInventory;
	        	selectedInventory = cart.getInventory();
	        	String selectedProductId = selectedInventory.getProductId();
	        	Product selectedProduct = null;
	        	for (Product products : productList) {
	    	        if (products.getId().equals(selectedProductId)) {
	    	        	selectedProduct = products;
	    	        }
	    	    } 
	        			
	        	System.out.printf("| %3s | %-10s | %-67s | %8s | %9s | %8s |\n", 
	        			num = num + 1,
	        			selectedInventory.getProductId(),
	        			selectedProduct.getName(),
	        			cart.getCount(),
	        			selectedProduct.getPrice(),
	    				(selectedInventory.getQuantity() * selectedProduct.getPrice()));
	        }
	        
	        System.out.print("Enter Product No to delete	: ");
            noInput = sc.nextInt();
            
            // Validate product number input
            while(noInput > cartList.size() || noInput <= 0) {
            	System.out.print("\nInvalid Product No. Please try again.\nProduct No	: ");
	            noInput = sc.nextInt();
            }
            
            System.out.println();
            
            // Update inventory list
            for (Inventory inventory: inventoryList) {
            	if (inventory == cartList.get(noInput - 1).getInventory()) {            		
            		inventory.setQuantity(inventory.getQuantity() + cartList.get(noInput - 1).getCount());
            		
            	}
            }
            
            // Remove item from cart list
            cartList.remove(noInput - 1);
            
            System.out.println("\nItem deleted from cart. ");
        	break;

        default:
        	System.out.println("Invalid choice. Please enter 1, 2 3, 4 or 5.");
        	break;
		}
		
	}
	
	public void makePayment(ArrayList <Cart> cartList, ArrayList <Inventory> inventoryList, ArrayList <Product> productList, ArrayList <ClientSales> clientSalesList) {
		// Display the header for the payment section
		String divider = "----------------------------------------------------------------------------------------------------";	//100-
    	System.out.println();
		System.out.printf("\n" + divider + "\n%52s\n" + divider, "Client Check");
		System.out.println();
		// Headers for the cart display table
		System.out.println("\n| Product ID | Product Name                                                        | Quantity | Price ($) | Subtotal |");
        System.out.println("|------------|---------------------------------------------------------------------|----------|-----------|----------|");

        double total = 0; // Total cost of all products in the cart
        
    	// Iterate through each cart item to display its details
        for (Cart cart : cartList) {
        	Inventory selectedInventory;
        	selectedInventory = cart.getInventory();
        	String selectedProductId = selectedInventory.getProductId();
        	Product selectedProduct = null;
        	// Find product details from product list using product ID
        	for (Product products : productList) {
    	        if (products.getId().equals(selectedProductId)) {
    	        	selectedProduct = products;
    	        }
    	    } 
        		
        	// Print formatted product details in the cart
        	System.out.printf("| %-10s | %-67s | %8s | %9s | %8s |\n", 
        			selectedInventory.getProductId(),
        			selectedProduct.getName(),
        			cart.getCount(),
        			selectedProduct.getPrice(),
    				(cart.getCount() * selectedProduct.getPrice()));
        	total += (cart.getCount() * selectedProduct.getPrice());
        }
    	
        // Display total price of the cart
        System.out.println("\nTotal price: $" + total);
        System.out.println("Make payment?");
    	System.out.print("Yes - Y, No - N : ");
    	String op = sc.nextLine();
    	
    	// Process payment if the user confirms
    	if (op.equals("Y")) {	
    		String salesID = String.format("S%d", (Integer.parseInt(clientSalesList.get(clientSalesList.size() - 1).getSalesID().replaceAll("[^0-9]", "")) + 1));

    		// Record each cart item as a client sale
    		for (Cart cart : cartList) {
    			LocalDate today = LocalDate.now();
    			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    			String todayString = today.format(formatter);
	        	String productId = cart.getInventory().getProductId();
	        	
	        	clientSalesList.add(new ClientSales(salesID, id, todayString, productId, cart.getCount()));	
	        }
    		
    		// Clear cart and write changes to the text files
    		cartList.clear();
    		ClientSales.writeSalesClientFile(clientSalesList);
    		Inventory.writeInventoryFile(inventoryList);
    		System.out.println("\nPayment made successfully.");
            System.out.println("We have sent a sales confirmation to your email. Thank you for shopping with us!");
    	}
    	else {
    		System.out.println("Payment cancelled.");
    	}
    	
	}
	
	// Displays the past purchases made by the client.
	public void viewPastPurchase(ArrayList <ClientSales> clientSalesList) {
        ClientSales cs;
        // Display header for past purchase data
        String divider = "----------------------------------------------------------------------------------------------------";
        System.out.println("\nPast Purchases:");
		System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s\n", "Sales ID", "Client ID", "Date", "Product ID", "Quantity");
		
		// Iterate over client sales list to find and display purchases by current client
		for (int i = 0; i < clientSalesList.size(); i++) {
        	cs = clientSalesList.get(i);
        	if (cs.getClientID().equals(getId())) {
        		System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s\n", cs.getSalesID(), cs.getClientID(), cs.getDate(), cs.getProductID(), cs.getQuantity());
        	}
        }
        System.out.println(divider);
		
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.print("Options:\n1. Print past purchase\n2. Exit\nEnter option:");
			String option = sc.nextLine();
			switch (option) {
				case "1":
					System.out.print("\nEnter sales ID: ");
					String inputID = sc.nextLine();
					// Display the sales data for the specified sales ID
					System.out.println("\nSales data for " + inputID + "\n" + divider);
					System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s\n", "Sales ID", "Client ID", "Date", "Product ID", "Quantity");
					for (int i = 0; i < clientSalesList.size(); i++) {
			        	cs = clientSalesList.get(i);
			        	if (cs.getSalesID().equals(inputID)) {
			        		System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s\n", cs.getSalesID(), cs.getClientID(), cs.getDate(), cs.getProductID(), cs.getQuantity());
			        	}
			        }
					break;
				case "2": 
					loop = false; // Exit the loop and end interaction
					break;
				default:
					System.out.println("Invalid choice. Please enter 1 or 2.");
					break;
					
			}
		
		}
    }
}