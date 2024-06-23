package sd_assign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * The Inventory class represents the inventory of products in the system.
 * It manages the storage and retrieval of inventory items, and provides methods
 * to add, retrieve, update, and delete inventory items.
 */

public class Inventory {
	private String productId;
	private int quantity;
	private String expDate;
	private static ArrayList<Inventory> inventoryList = new ArrayList<>();
	
	// Accessors
	public String getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getExpDate() {
		return expDate;
	}
	
	// Mutators
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	// Constructor
	public Inventory(String productId, int quantity, String expDate) {
		this.productId = productId;
		this.quantity = quantity;
		this.expDate = expDate;
	}
	
	// Read inventory data from file and populate the inventory list
	public static ArrayList <Inventory> readInventoryFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("inventory.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Inventory inventory;
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                
                String id = fields[0];
                int quantity = Integer.parseInt(fields[1]);
                String expDate = fields[2];
	            	
                inventory = new Inventory(id, quantity, expDate);
	            inventoryList.add(inventory);
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return inventoryList;
	}
	
	// Write inventory data to file
	public static void writeInventoryFile(ArrayList<Inventory> inventoryList) {
		try {
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("inventory.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        
	        // Write data to the file
	        String line;
            for (Inventory inventory : inventoryList) {
        		Inventory Inventory = (Inventory) inventory;
            	line = String.format("%s|%s|%s",
            			Inventory.getProductId(),
            			Inventory.getQuantity(),
            			Inventory.getExpDate());
            
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
	
	// Retrieve inventory item by productId
	public static ArrayList<Inventory> retrieveInventoryItem(String productId) {
		ArrayList<Inventory> matchingItems = new ArrayList<>();
		for (Inventory item : inventoryList) {
			if (item.getProductId().equals(productId)) {
			      matchingItems.add(item);
			}
		}
		return matchingItems;
	}
	
	// Update existing inventory item
	public static void updateInventoryItem(String productId, String expDate, int quantity) {
		for (Inventory item : inventoryList) {
			if (item.getProductId().equals(productId)) {
				item.setProductId(productId);
				item.setExpDate(expDate);
				item.setQuantity(quantity);
				writeInventoryFile(inventoryList); // Update file
				return;
			}
		}
	}

}
