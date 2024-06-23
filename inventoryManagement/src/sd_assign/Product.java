package sd_assign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The Product class represents a generic product in the inventory management system.
 * It serves as the superclass for more specific product types like SkinCareProduct and HealthProduct.
 */

public abstract class Product {

	Scanner sc = new Scanner(System.in);
	protected String id, name, category, description;
	protected double price;
	protected static ArrayList<Product> productList = new ArrayList<>();
	
	// Constructors
	public Product(String id, String name, String category, String description, double price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}
	
	public Product() {}
	
	// Accessors
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public ArrayList<Product> getList() {
		return productList;
	}
	
	// Mutators
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	// Read product data from file
	public static ArrayList<Product> readProductFile() {
		try {
			// Create a new file object
			FileReader fileReader = new FileReader ("product.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Product product;
			// Read each line of the file and add it to the ArrayList
			String line;
	        while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");
                
                String id = fields[0];
                String name = fields[1];
                String category = fields[2];
                String description = fields[3];
                double price = Double.parseDouble(fields[4]);

               // Differentiate product categories
                if (category.equals("skincare") && fields.length >= 7) {
                    String skinType = fields[5];
                    String targetArea = fields[6];
                    product = new SkinCareProduct(id, name, category, description, price, skinType, targetArea);
                } else if (category.equals("health") && fields.length >= 6) {
                    String formulation = fields[5];
                    product = new HealthProduct(id, name, category, description, price, formulation);
                } else
                	continue;
	            	
	            productList.add(product);
	        }
	        
	        // Close BufferedWriter and FileWriter
	        bufferedReader.close();
	        fileReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	// Write product data to file
	public static void writeProductFile(ArrayList<Product> productList) {
		try {
	        // Create a new FileWriter object with the file path as parameter
	        FileWriter fileWriter = new FileWriter("product.txt");
	
	        // Create a new BufferedWriter object to write data to the file
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        
	        // Write data to the file
	        String line;
            for (Product product : productList) {
            	if (product.getCategory().equals("skincare")) {
            		SkinCareProduct SkincareProduct = (SkinCareProduct) product;
	            	line = String.format("%s|%s|%s|%s|%s|%s|%s",
	            			SkincareProduct.getId(),
	            			SkincareProduct.getName(),
	            			SkincareProduct.getCategory(),
	            			SkincareProduct.getDescription(),
	            			SkincareProduct.getPrice(), 
	            			SkincareProduct.getSkinType(), 
	            			SkincareProduct.getTargetArea());
            	} else {
            		HealthProduct healthProduct = (HealthProduct) product;
            		line = String.format("%s|%s|%s|%s|%s|%s",
            				healthProduct.getId(),
	            			healthProduct.getName(),
	            			healthProduct.getCategory(),
	            			healthProduct.getDescription(),
	            			healthProduct.getPrice(), 
	            			healthProduct.getFormulation());	
            	} 
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
	
	// Abstract methods
	public abstract void displayProductDetails();
	public abstract void addProduct();	
	public abstract void updateProduct();
	public abstract void deleteProduct();
	
	// Retrieve product details by ID
	public void retrieveProduct() {
		boolean found;
		do {
			found = false;
			System.out.print("Enter Product ID : ");
			String id = sc.next();
			sc.nextLine();
			for (Product product : productList) {
				if (product.getId().equals(id)) {
					found = true;
					product.displayProductDetails();	
					break;
				}
			}
			if (found == false)
				System.out.println("Invalid ID. Please try again.");
		} while(!found);
	}	
}
