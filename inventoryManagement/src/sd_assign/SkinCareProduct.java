package sd_assign;

import java.util.Scanner;

/*
 * The SkinCareProduct class represents a specific type of product, namely skincare products, which extends the Product class.
 * It provides functionality for adding, updating, and deleting skincare products, and also includes methods to display product details.
 */

public class SkinCareProduct extends Product{

	Scanner sc = new Scanner(System.in);
	private String skinType;
	private String targetArea;
	
	// Constructor
	public SkinCareProduct(String id, String name, String category, String description, double price, String skinType, String targetArea) {
		super(id, name, category, description, price);
		this.skinType = skinType;
		this.targetArea = targetArea;
	}
	public SkinCareProduct() {}
	
	// Accessors
	public String getSkinType() {
		return skinType;
	}
	
	public String getTargetArea() {
		return targetArea;
	}
	
	// Mutators
	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}
	
	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}
	
	// Method to add a skincare product
	@Override
	public void addProduct() {
		char agree;
		String id, name, description, skinType, targetArea;
		double price;
		
		// Loop to add product with user confirmation
		do {
			// Prompt user to enter product details
			System.out.print("Enter product ID \t\t: ");
			id = sc.next();
			System.out.print("Enter product name \t\t: ");
			sc.nextLine();
			name = sc.nextLine();
			System.out.print("Enter product description \t: ");
			description = sc.nextLine();
			System.out.print("Enter product price \t\t: ");
			price = sc.nextDouble();
			System.out.print("Enter intended skin type \t: ");
			sc.nextLine();
			skinType = sc.nextLine();
			System.out.print("Enter product target area \t: ");
			targetArea = sc.nextLine();
			
			// Display entered details for confirmation
			System.out.println("\nPlease confirm the skin care product details.");
			System.out.printf("Product ID\t: %s\nName\t\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nSkin type\t: %s\nTarget area\t: %s\n", id, name, description, price, skinType, targetArea);
	
			// Prompt user to confirm addition
			System.out.print("Add the product? (Yes - Y, No - N) : ");			
			agree = sc.next().charAt(0);
			
			// Validate user input for confirmation
			while(agree != 'Y' && agree != 'N') {
				System.out.print("Wrong option. Please choose again. \nAdd the product? (Yes - Y, No - N) : ");			
				agree = sc.next().charAt(0);
			}		
		} while(agree == 'N');	// Continue looping until user confirms addition
		
		// If user confirms addition
		if (agree =='Y') {
			// Add product to product list
			productList.add(new SkinCareProduct(id, name, "skincare", description, price, skinType, targetArea));
			System.out.println("\nProduct added successfully.");
			writeProductFile(productList);
		}
	}
	
	// Method to update a skincare product
	public void updateProduct() {
		char agree;
		String updateId, updateName, updateDescription, updateSkinType, updateTargetArea;
		double updatePrice;
		// Prompt user to enter product ID
		System.out.print("Enter product ID : ");
		String id = sc.next();
		
		// Iterate through the product list
		for (int i = 0; i < productList.size(); i++) {
			// Check if product ID matches
			if (productList.get(i).getId().equals(id)) {
				// Display existing product details for confirmation
				System.out.println("\nThe existing product details for Product ID " + id);
				
				// Cast product to SkinCareProduct to access specific fields
				SkinCareProduct product = (SkinCareProduct) productList.get(i);
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nSkin type\t: %s\nTarget area\t: %s\n", 
							product.getId(), 
							product.getName(), 
							product.getCategory(), 
							product.getDescription(), 
							product.getPrice(), 
							product.getSkinType(), 
							product.getTargetArea());
				
				// Prompt user to enter updated details
				System.out.println("\nPlease enter the updated details for the product.");
				System.out.print("Enter product ID \t\t: ");
				updateId = sc.next();
				System.out.print("Enter product name \t\t: ");
				sc.nextLine();
				updateName = sc.nextLine();
				System.out.print("Enter product description \t: ");
				updateDescription = sc.nextLine();
				System.out.print("Enter product price \t\t: ");
				updatePrice = sc.nextDouble();
				System.out.print("Enter intended skin type \t: ");
				sc.nextLine();
				updateSkinType = sc.nextLine();
				System.out.print("Enter product target area \t: ");
				updateTargetArea = sc.nextLine();

				// Display updated details for confirmation
				System.out.println("\nPlease confirm the skincare product details.");
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nSkin type\t: %s\nTarget area\t: %s\n", updateId, updateName, updateDescription, updatePrice, updateSkinType, updateTargetArea);
				
				// Prompt user to confirm modification
				System.out.print("Modify the product? (Yes - Y, No - N) : ");           		
        		agree = sc.next().charAt(0);	
        		
        		// Validate user input for confirmation
        		while(agree != 'Y' && agree != 'N') {
        			System.out.print("Invalid option. Please choose again. \nModify the product? (Yes - Y, No - N) : ");			
        			agree = sc.next().charAt(0);
        		}	
        		// Perform modification or cancellation based on user input
				if (agree == 'Y') {
					product.setId(updateId);
					product.setName(updateName);;
					product.setDescription(updateDescription);
					product.setPrice(updatePrice);
					product.setSkinType(updateSkinType);
					product.setTargetArea(updateTargetArea);
					System.out.println("\nProduct modified successfully.");
				} else {
					System.out.println("\nModification cancelled.");
				}
				break; // Exit loop after processing
			}
		}
		writeProductFile(productList); // Update product file
	}
	
	// Method to delete a skincare product
	public void deleteProduct() {
		char agree;
		// Prompt user to enter product ID
		System.out.print("Enter product ID : ");
		String id = sc.next();
		
		// Iterate through the product list
		for (int i = 0; i < productList.size(); i++) {
			// Check if product ID matches
			if (productList.get(i).getId().equals(id)) {
				// Display product details for confirmation
				System.out.println("Please confirm the product details.");
				
				// Cast product to SkinCareProduct to access specific fields
				SkinCareProduct product = (SkinCareProduct) productList.get(i);
				System.out.printf("Product ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %.2f\nSkin type\t: %s\nTarget area\t: %s\n", 
						product.getId(), 
						product.getName(), 
						product.getCategory(), 
						product.getDescription(), 
						product.getPrice(), 
						product.getSkinType(), 
						product.getTargetArea());
				
				// Prompt user to confirm deletion
				System.out.print("\nDelete the product? (Yes - Y, No - N) : ");           		
        		agree = sc.next().charAt(0);	
        		
        		// Validate user input for confirmation
        		while (agree != 'Y' && agree != 'N') {
        			System.out.print("Invalid option. Please choose again.\nDelete the product? (Yes - Y, No - N) : ");			
        			agree = sc.next().charAt(0);
        		}	
        		// Perform deletion or cancellation based on user input
				if (agree == 'Y') {
					productList.remove(i);
					System.out.println("\nProduct deleted successfully.");
					writeProductFile(productList);
				}
				else {
					System.out.println("\nDeletion cancelled.");
				}
				break; // Exit loop after processing
			}
		}
	}
	
	// Method to display skincare product details
	public void displayProductDetails() {
		System.out.printf("\nProduct details for product ID %s. ",id);
		System.out.printf("\nProduct ID\t: %s\nName\t\t: %s\nCategory\t: %s\nDescription\t: %s\nPrice\t\t: %s\nSkin type\t: %s\nTarget area\t: %s\n", 
				getId(),
				getName(), 
				getCategory(), 
				getDescription(), 
				getPrice(), 
				getSkinType(), 
				getTargetArea()); 
	}
}
