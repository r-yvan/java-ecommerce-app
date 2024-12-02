package main.java.ecommerce.admin;

import main.java.ecommerce.User;
import main.java.ecommerce.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
  private List<Product> allProducts;
  
  // Constructor
  public Admin(int userId, String name, String email) {
    super(userId, name, email);
    this.allProducts = new ArrayList<>();
  }
  
  @Override
  public void displayRole() {
    System.out.println(name + " is an Admin.");
  }
  
  // Add a new product to the system
  public void addProduct(Product product) {
    allProducts.add(product);
    System.out.println("Product added: " + product.getName());
  }
  
  // Remove a product from the system by its ID
  public void removeProduct(int productId) {
    boolean productFound = false;
    for (Product product : allProducts) {
      if (product.getProductId() == productId) {
        allProducts.remove(product);
        System.out.println("Product removed: " + product.getName());
        productFound = true;
        break;
      }
    }
    if (!productFound) {
      System.out.println("Product with ID " + productId + " not found.");
    }
  }
  
  // View all products in the system
  public void viewAllProducts() {
    System.out.println("All Products:");
    for (Product product : allProducts) {
      product.displayProductDetails();
    }
  }
}

