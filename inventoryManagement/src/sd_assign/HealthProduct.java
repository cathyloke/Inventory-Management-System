package sd_assign;

import java.util.Scanner;

/*
 * The HealthProduct class represents health-related products that extend the Product class.
 * It provides methods to add, update, delete, and display product details.
 */

public class HealthProduct extends Product {
	// Scanner object for user input
	Scanner sc = new Scanner(System.in);
	// Additional property for health products
	private String formulation;
	
	// Constructors
	public HealthProduct(String id, String name, String category, String description, double price, String formulation) {
		super(id, name, category, description, price);
		this.formulation = formulation;
	}
	
	public HealthProduct() {}
	
	// Accessor
	public String getFormulation() {
		return formulation;
	}
	
	// Mutator
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}
	
	// Method to add a health product
	@Override
	public void addProduct() {
		char agree;
		String id, name, description, formulation;
		double price;
		// Loop to ensure user confirmation before adding the product
		do {
			// Prompting user for product details
			System.out.print("Enter product ID \t\t: ");
			id = sc.next();
			System.out.print("Enter product name \t\t: ");
			sc.nextLine(); // Consuming newline character
			name = sc.nextLine();
			System.out.print("Enter product description \t: ");
			description = sc.nextLine();
			System.out.print("Enter product price \t\t: ");
			price = sc.nextDouble();
			System.out.print("Enter product formulation \t: ");
			sc.nextLine(); // Consuming newline character
			formulation = sc.nextLine();

			// Displaying entered product details for confirmation
			System.out.println("\nPlease confirm the health product details.");
			System.out.printf("Product ID\t: %s\nName\t\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nFormulation\t: %s\n", id, name, description, price, formulation);
			
			// Asking for user confirmation
			System.out.print("Add the product? (Yes - Y, No - N) : ");			
			agree = sc.next().charAt(0);
			
			// Loop to handle invalid options for confirmation
			while(agree != 'Y' && agree != 'N') {
				System.out.print("Wrong option. Please choose again. \nAdd the product? (Yes - Y, No - N) : ");			
				agree = sc.next().charAt(0);
			}	
		} while(agree == 'N');
		// Adding the product to the list and writing to the product file if user confirms
		if (agree =='Y') {
			productList.add(new HealthProduct(id, name, "health", description, price, formulation));
			System.out.println("\nProduct added successfully");
			writeProductFile(productList);	
		}
	}
	
	// Method to update a health product
	public void updateProduct() {
		char agree;
		String updateId, updateName, updateDescription, updateFormulation;
		double  updatePrice;
		// Prompting user for product ID to be updated
		System.out.print("Enter product ID : ");
		String id = sc.next();
		
		// Searching for the product in the productList
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getId().equals(id)) {
				// Displaying existing details of the product
				System.out.println("\nThe existing product details for Product ID " + id);
				
				HealthProduct product = (HealthProduct) productList.get(i);
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nFormulation\t: %s\n", 
							product.getId(), 
							product.getName(), 
							product.getCategory(), 
							product.getDescription(), 
							product.getPrice(), 
							product.getFormulation());
				
				// Prompting user for updated details
				System.out.println("\nPlease enter the updated details for the product.");
				System.out.print("Enter product ID \t\t: ");
				updateId = sc.next();
				System.out.print("Enter product name \t\t: ");
				sc.nextLine(); // Consuming newline character
				updateName = sc.nextLine();
				System.out.print("Enter product description \t: ");
				updateDescription = sc.nextLine();
				System.out.print("Enter product price \t\t: ");
				updatePrice = sc.nextDouble();
				System.out.print("Enter product formulation \t: ");
				sc.nextLine(); // Consuming newline character
				updateFormulation = sc.nextLine();
				
				// Displaying updated details for confirmation
				System.out.println("\nPlease confirm the health product details.");
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nFormulation\t: %s\n", updateId, updateName, updateDescription, updatePrice, updateFormulation);
				
				// Asking for user confirmation to modify the product
				System.out.print("Modify the product? (Yes - Y, No - N) : ");           		
        		agree = sc.next().charAt(0);	
        		
        		// Loop to handle invalid options for confirmation
        		while(agree != 'Y' && agree != 'N') {
        			System.out.print("Invalid option. Please choose again. \nModify the product? (Yes - Y, No - N) : ");			
        			agree = sc.next().charAt(0);
        		}	
        		// Modifying the product if user confirms
				if (agree == 'Y') {
					product.setId(updateId);
					product.setName(updateName);;
					product.setDescription(updateDescription);
					product.setPrice(updatePrice);
					product.setFormulation(updateFormulation);	
					System.out.println("\nProduct modified successfully.");
				}
				else {
					System.out.println("\nModification cancelled.");
				}
				break;
			}
		}
		// Writing the updated list to the product file
		writeProductFile(productList);
	}
	
	// Method to delete a health product
	public void deleteProduct() {
		char agree;
		// Prompting user for product ID to be deleted
		System.out.print("Enter product ID : ");
		String id = sc.next();
		
		// Searching for the product in the productList
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getId().equals(id)) {
				// Displaying details of the product to confirm deletion
				System.out.println("\nPlease confirm the product details.");
				
				HealthProduct product = (HealthProduct) productList.get(i);
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nFormulation\t: %s\n", 
						product.getId(), 
						product.getName(), 
						product.getCategory(), 
						product.getDescription(), 
						product.getPrice(), 
						product.getFormulation());
				
				// Asking for user confirmation to delete the product
				System.out.print("\nDelete the product? (Yes - Y, No - N) : ");           		
        		agree = sc.next().charAt(0);	
        		// Loop to handle invalid options for confirmation
        		while(agree != 'Y' && agree != 'N') {
        			System.out.print("Invalid option. Please choose again. \nDelete the product? (Yes - Y, No - N) : ");			
        			agree = sc.next().charAt(0);
        		}	
        		// Deleting the product if user confirms
				if (agree == 'Y') {
					productList.remove(i);
					System.out.println("\nProduct deleted successfully.");
					writeProductFile(productList);
				}
				else {
					System.out.println("\nDeletion cancelled.");
				}
				break;
			}
		}
	}
	
	// Method to display product details
	public void displayProductDetails() {
		System.out.printf("\nProduct details for product ID %s. ",id);
		System.out.printf("\nProduct ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nFormulation\t: %s\n", 
				getId(),
				getName(), 
				getCategory(), 
				getDescription(), 
				getPrice(), 
				getFormulation());
	}
}