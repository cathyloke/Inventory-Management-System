package sd_assign;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * SkinCare_HealthSystem class manages skincare and health products-related activities.
 * It organises user account creation, login, product and inventory management, client purchases,
 * and report generation.
 * The class uses ArrayLists to store user, product, inventory, and client sales data.
 * It provides menus for administrators and clients, ensuring smooth interaction and operation
 * of the system.
 */

public class SkinCare_HealthSystem {

	// Declare global scanner
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Define divider for better UI
		String divider = "----------------------------------------------------------------------------------------------------";
		String option;
		String userOption = null;
		boolean loop = true;
		// Initialise ArrayLists for users, products, inventory, and client sales
		ArrayList<User> userList = new ArrayList<>();
		ArrayList<Product> productList = new ArrayList<>();
		ArrayList<Inventory> inventoryList = new ArrayList<>();
		ArrayList<ClientSales> clientSalesList = new ArrayList<>();
		
		try {		
			while (loop) {	
				// Populating productList, userList, inventoryList
				productList.clear();
				productList = Product.readProductFile();	
				userList.clear();
				userList = User.readUserFile();
				inventoryList.clear();
				inventoryList = Inventory.readInventoryFile();
				clientSalesList.clear();
				clientSalesList = ClientSales.readClientSalesFile();
				
				// Print welcome message
				System.out.println();
				System.out.printf(divider + "\n%68s\n" + divider, "Welcome to Skincare & Health System");
				System.out.print("\nWho are you?\n1. Admin\n2. Client\n3. Exit\nSelect your option (1/2/3): ");
				option = sc.next();
				
				// Handle the option
				switch (option) {
				case "1": userOption = "Admin"; break;
				case "2": userOption = "Client"; break;
				case "3": 
					System.out.println("Thank you for using our Skincare & Health system! Exiting...");
					System.exit(0);
					break;
				default:
					System.out.println("\nInvalid option. Please try again.");
					continue;
				}
				
				System.out.println();
				// Print user menu
				System.out.printf(divider + "\n%50s Page\n" + divider + "\n1. Create account\n2. Log in\n3. Exit\nSelect your option (1/2/3): ", userOption);
				option = sc.next();
				
				// Validate user input for user menu
				while (!option.equals("1") && !option.equals("2")  && !option.equals("3")) {
					System.out.print("\nInvalid option. Please re-enter a valid option.\n\nSelect your option (1/2/3): ");
					option = sc.next();
				}
				
				// Process user option
				if (option.equals("1")) {		
					User.createAccount(userOption, userList);
				} else if (option.equals("2")) {
					User user = User.logInAccount(userOption, userList);		
					userMenu(user, userOption, productList, inventoryList, clientSalesList);
				} else {
					System.out.println("\nThank you for using Skincare & Health System! Exiting system...");
					System.exit(0);
				}
			}
			sc.close();
		} catch (Exception ex) {
			// Handle exceptions
			ex.getMessage();
		}
	}
	
	// User menu method
	public static void userMenu(User user, String userOption, ArrayList<Product> productList, ArrayList<Inventory> inventoryList, ArrayList<ClientSales> clientSalesList) {
		boolean loop = true;	
		// Define divider for better UI
		String divider = "----------------------------------------------------------------------------------------------------";	// 100-
		
		if (userOption.equals("Admin")) {
			// Admin menu
			while (loop) {
				System.out.println();
				System.out.printf(divider + "\n%50s Menu\n" + divider, userOption);
				System.out.println();
				System.out.print("1. Manage inventory\n2. Manage products\n3. Retrieve client sales\n4. Generate report\n5. Exit\nSelect your option (1/2/3/4/5): ");
				int option = sc.nextInt();
				
				// Process admin menu options
				switch (option) {
				case 1:
					Admin.manageInventoryMenu(inventoryList);
					break;
				case 2:
					Admin.manageProductMenu(productList);
					break;
				case 3:
					Admin.retrieveClientSales(clientSalesList);
					break;
				case 4:
					Admin.generateReport(clientSalesList, inventoryList, productList);
					break;
				case 5:
					loop = false;
					break;
				default:
					System.out.println("\nInvalid option. Please try again.");
				}
			}
		} else if (userOption.equals("Client")) {	
			// Client menu
			while (loop) {
				System.out.println();
				System.out.printf(divider + "\n%50s Menu\n" + divider, userOption);
				System.out.println();
				System.out.print("1. Purchase item\n2. View past purchase\n3. Exit\nSelect your option (1/2/3): ");
				int option = sc.nextInt();
				
				// Process client menu options
				switch (option) {
				case 1:
					((Client) user).purchaseProducts(inventoryList, productList, clientSalesList);
					break;
				case 2:
					((Client) user).viewPastPurchase(clientSalesList);
					break;
				case 3:
					loop = false; // Exit the loop and end interaction
					break;
				default:
					System.out.println("\nInvalid option. Please try again.");
				}
			}
		}
	}
}
