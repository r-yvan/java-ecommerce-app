package main.java.ecommerce.seller;

import main.java.ecommerce.User;
import main.java.ecommerce.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
  private List<Product> sellerProducts;
  
  public Seller(int userId, String name, String email) {
    super(userId, name, email);
    this.sellerProducts = new ArrayList<>();
  }
  
  @Override
  public void displayRole() {
    System.out.println(name + " is a Seller.");
  }
  
  public void addProduct(Product product) {
    sellerProducts.add(product);
    System.out.println("Product added by " + name + ": " + product.getName());
  }
  
  public void removeProduct(int productId) {
    boolean productFound = false;
    for (Product product : sellerProducts) {
      if (product.getProductId() == productId) {
        sellerProducts.remove(product);
        System.out.println("Product removed by " + name + ": " + product.getName());
        productFound = true;
        break;
      }
    }
    if (!productFound) {
      System.out.println("Product with ID " + productId + " not found in " + name + "'s inventory.");
    }
  }
  
  public void viewProducts() {
    System.out.println(name + "'s Products:");
    for (Product product : sellerProducts) {
      product.displayProductDetails();
    }
  }
  
  public void respond(String buyerName, Product product, String action) {
    switch(action.toLowerCase()) {
      case "add_to_cart":
        System.out.println("Seller " + name + " responds to " + buyerName + ": "
                + product.getName() + " has been added to your cart.");
        break;
      
      case "buy":
        if (product.getQuantity() <= 0) {
          System.out.println("Seller " + name + " responds to " + buyerName + ": Sorry, "
                  + product.getName() + " is out of stock.");
        } else {
          System.out.println("Seller " + name + " responds to " + buyerName + ": Your purchase of "
                  + product.getName() + " is successful!");
          // Logic for reducing stock after purchase can go here
        }
        break;
      
      case "check_stock":
        System.out.println("Seller " + name + " responds to " + buyerName + ": The stock for "
                + product.getName() + " is " + product.getQuantity() + " units.");
        break;
      
      default:
        System.out.println("Seller " + name + " has no response for the action: " + action);
        break;
    }
  }
}

