package sd_assign;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This abstract class represents a User in the system.
 * It contains common attributes and methods for both Admin and Client users.
 */

public abstract class User {
	protected String id, name, username, password, email, phone, address;
	protected static ArrayList<User> userList = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	// Constructors
	public User(String id, String name, String username, String password, String email, String phone, String address) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public User() {}
	
	// Accessors
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public ArrayList<User> getList() {
		return userList;
	}
	
	// Mutators
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	// Reads user data from the "user.txt" file and populates the userList ArrayList.
	public static ArrayList<User> readUserFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("user.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			User user;
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
               
                String id = fields[0];
                String name = fields[1];
                String username = fields[2];
                String password = fields[3];
                String email = fields[4];
                String phone = fields[5];
                String address = fields[6];
                
                if (id.charAt(0) == 'A')
                	user = new Admin(id, name, username, password, email, phone, address);
                else
                	user = new Client(id, name, username, password, email, phone, address);
                userList.add(user);
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	// Writes user data from the userList ArrayList to the "user.txt" file.
	public static void writeUserFile(ArrayList<User> userList) {
		try {
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("user.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	
	        // Write data to the file
            for (User user : userList) {
            	String line = String.format("%s|%s|%s|%s|%s|%s|%s",
            		    user.getId(),
            		    user.getName(),
            		    user.getUsername(),
            		    user.getPassword(),
            		    user.getEmail(),
            			user.getPhone(),
            			user.getAddress());
            	
            	bufferedWriter.write(line);
            	bufferedWriter.newLine(); // add new line character
            }
	
	        // Close the BufferedWriter and FileWriter
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Create account method
	public static void createAccount(String userOption, ArrayList <User> userList) {
		Scanner sc = new Scanner(System.in);
		String id, name, username, password, email, phone, address;
		char agree;		
		String divider = "----------------------------------------------------------------------------------------------------";	// 100-
		System.out.printf(divider + "\n%60s\n" + divider, "Create Account Page");

		// Loop to ensure user confirmation before proceeding
		do {
			System.out.println("\nPlease provide your personal details.");
			System.out.print("Enter ID (must start with 'A' for Admin or 'C' for Client): ");
		    id = sc.next();
		    
		    // Validate the ID format based on user option
		    while (!(id.startsWith("A") && userOption.equals("Admin") || id.startsWith("C") && userOption.equals("Client"))) {
		        System.out.println("ID must start with 'A' for Admin or 'C' for Client. Please try again.");
		        System.out.print("Enter ID (must start with 'A' for Admin or 'C' for Client): ");
			    id = sc.next();
		    }
	  
		    System.out.print("Enter name    : ");
		    sc.nextLine();				// Consumes newline character
		    name = sc.nextLine();
		    System.out.print("Enter username: ");
		    username = sc.next();
		    System.out.print("Enter password: ");
		    password = sc.next();
		    System.out.print("Enter email   : ");
		    email = sc.next();
		    System.out.print("Enter phone   : ");
		    phone = sc.next();
		    System.out.print("Enter address : ");
		    sc.nextLine();
		    address = sc.nextLine();
			
		    // Display the entered details for confirmation
			System.out.println("\nPlease confirm your personal details.");
			System.out.printf("ID\t: %s\n" + "Name\t: %s\n" + "Username: %s\n" + "Password: %s\n" + "Email\t: %s\n" + "Phone\t: %s\n" + "Address\t: %s\n", id, name, username, password, email, phone, address);
					
			// Prompt for confirmation with error handling
			System.out.print("Create account? (Yes - Y, No - N) : ");			
			agree = sc.next().charAt(0);
			
			while (agree != 'Y' && agree != 'N') {
				System.out.println("Invalid option. Please re-enter a valid option.\nCreate account? (Yes - Y, No - N) : ");			
				agree = sc.next().charAt(0);
			}	
		} while (agree == 'N');
		
		// Add the new user to the userList based on user option
		if (userOption.equals("Admin"))
			userList.add(new Admin(id, name, username, password, email, phone, address));
		else
			userList.add(new Client(id, name, username, password, email, phone, address));
		
		// Write the updated userList to the file
		User.writeUserFile(userList);
		// Display success message
		System.out.println("\nAccount created successfully!");
	}
		
	// Log in account method
	public static User logInAccount(String userOption, ArrayList <User> userList) {
		User user1 = null;
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		boolean found = false;
		String divider = "----------------------------------------------------------------------------------------------------";	// 100-
		System.out.println();
		System.out.printf(divider + "\n%56s\n" + divider, "Log In Page");
		
		// Prompt for username and password
		System.out.print("\nEnter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.next();
		
		// Loop to handle login attempts
		while (loop) {
			// Iterate through the userList to find a match for the provided credentials
			for (User user : userList) {
	            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
	                // Check if the user type matches the userOption
	                if ((userOption.equals("Admin") && user instanceof Admin) || (userOption.equals("Client") && user instanceof Client)) {
	                	// Assign the logged-in user to user1
	                	if (userOption.equals("Admin")) {
	                		user1 = (Admin) user;
	                	}
	                	else {
	                		user1 = (Client) user;
	                	}
	                    loop = false;
	                    found = true;
	                    System.out.println("\nLog in successful!");
	                    break;
	                } else {
	                	// Invalid user type for the specified userOption
	                	System.out.println("\nInvalid username or password. Please try again.\n");
	                    System.out.print("Enter username: ");
	                    sc.nextLine();
	                    username = sc.nextLine();
	                    System.out.print("Enter password: ");
	                    password = sc.next();  
	                }	                
	            }  
	        }
			// Handle invalid login attempts
			if (found == false) {
				System.out.println("\nInvalid username or password. Please try again.\n");
                System.out.print("Enter username: ");
                sc.nextLine();
                username = sc.nextLine();
                System.out.print("Enter password: ");
                password = sc.next(); 
			}
	    }
		// Return the logged-in User object if successful, otherwise return null
		return user1;
	}
}
