package sd_assign;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
 * The ClientSales class represents sales made by clients, providing methods to read
 * and write sales data to a file.
 */

public class ClientSales {

	private String salesID, clientID;
	private String date;
	private String productID;
	private int quantity;
	private static ArrayList<ClientSales> clientSalesList = new ArrayList<>();
	
	// Constructor
	public ClientSales(String salesID, String clientID, String date, String productID, int quantity) {
		this.salesID = salesID;
		this.clientID = clientID;
		this.date = date;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	// Accessors
	public String getSalesID() {
        return salesID;
    }

    public String getClientID() {
        return clientID;
    }

    public String getDate() {
        return date;
    }
    
    public String getProductID() {
    	return productID;
    }
    
    public int getQuantity() {
    	return quantity;
    }
	
    // Mutators
    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public void setProductID(String productID) {
    	this.productID = productID;
    }
    
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }
      
    // Method to read client sales data from a file
	public static ArrayList <ClientSales> readClientSalesFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("clientSales.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ClientSales clientSales;
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                
                String salesID = fields[0];
                String clientID = fields[1];
                String date = fields[2];
                String productID = fields[3];
                int quantity = Integer.parseInt(fields[4]);
               	
                clientSales = new ClientSales(salesID, clientID, date, productID, quantity);
                clientSalesList.add(clientSales);
	        }
	        
	        // Close the BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return clientSalesList;
	}
	
	// Method to write client sales data to a file
	public static void writeSalesClientFile(ArrayList<ClientSales> clientSalesList) {
		try {
			
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("clientSales.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        
	        // Write data to the file
            for (ClientSales clientSales : clientSalesList) {
            	String line = String.format("%s|%s|%s|%s|%s",
            			clientSales.getSalesID(),
            			clientSales.getClientID(),
            			clientSales.getDate(),
            			clientSales.getProductID(),
            			clientSales.getQuantity());
            	
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
}
