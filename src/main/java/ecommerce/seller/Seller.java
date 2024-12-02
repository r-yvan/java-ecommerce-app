package main.java.ecommerce.seller;

import main.java.ecommerce.User;
import main.java.ecommerce.product.Product;
import util.DatabaseConnector;
import java.sql.*;
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
  
  public int getUserId(){
   return userId;
  }
  
  // Add product to database
  public void addProduct(Product product) {
    String query = "INSERT INTO products (product_id, name, price, quantity) VALUES (?, ?, ?, ?)";
    try (Connection connection = DatabaseConnector.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, product.getProductId());
      preparedStatement.setString(2, product.getName());
      preparedStatement.setDouble(3, product.getPrice());
      preparedStatement.setInt(4, product.getQuantity());
      
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        sellerProducts.add(product);
        System.out.println("Product added by " + name + ": " + product.getName());
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to add product.");
    }
  }
  
  // Remove product from database
  public void removeProduct(int productId) {
    String query = "DELETE FROM products WHERE product_id = ?";
    try (Connection connection = DatabaseConnector.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, productId);
      
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        sellerProducts.removeIf(product -> product.getProductId() == productId);
        System.out.println("Product removed by " + name + ": Product ID " + productId);
      } else {
        System.out.println("Product with ID " + productId + " not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to remove product.");
    }
  }
  
  // Update product in the database
  public void updateProduct(Product product) {
    String query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";
    try (Connection connection = DatabaseConnector.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, product.getName());
      preparedStatement.setDouble(2, product.getPrice());
      preparedStatement.setInt(3, product.getQuantity());
      preparedStatement.setInt(4, product.getProductId());
      
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Product updated by " + name + ": " + product.getName());
      } else {
        System.out.println("Product with ID " + product.getProductId() + " not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to update product.");
    }
  }
  
  // View products from database
  public void viewProducts() {
    String query = "SELECT * FROM products";
    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      
      System.out.println(name + "'s Products:");
      while (resultSet.next()) {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        Product product = new Product(id, name, price, quantity);
        product.displayProductDetails(); // Display the product details
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch products.");
    }
  }
  
  // Seller response to buyer actions
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
