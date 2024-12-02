package main.java.ecommerce.admin;

import main.java.ecommerce.User;
import main.java.ecommerce.product.Product;
import main.java.ecommerce.seller.Seller;
import util.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
  private List<Product> allProducts;
  
  public Admin(int userId, String name, String email) {
    super(userId, name, email);
    this.allProducts = new ArrayList<>();
  }
  
  @Override
  public void displayRole() {
    System.out.println(name + " is an Admin.");
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
        allProducts.add(product);
        System.out.println("Product added: " + product.getName());
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
        allProducts.removeIf(product -> product.getProductId() == productId);
        System.out.println("Product removed: Product ID " + productId);
      } else {
        System.out.println("Product with ID " + productId + " not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to remove product.");
    }
  }
  
  // View all products from the database
  public void viewAllProducts() {
    String query = "SELECT * FROM products";
    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      
      System.out.println("All Products:");
      while (resultSet.next()) {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        Product product = new Product(id, name, price, quantity);
        product.displayProductDetails();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch products.");
    }
  }
  
  // Add a new seller to the database
  public void addSeller(Seller seller) {
    String query = "INSERT INTO sellers (name, email, user_id) VALUES (?, ?, ?)";
    try (Connection connection = DatabaseConnector.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, seller.getName());
      preparedStatement.setString(2, seller.getEmail());
      preparedStatement.setInt(3, seller.getUserId());  // Assume seller has userId from User class
      
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Seller added: " + seller.getName());
      } else {
        System.out.println("Failed to add seller.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to add seller.");
    }
  }
  
  // View all sellers from the database
  public void viewAllSellers() {
    String query = "SELECT * FROM sellers";
    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {
      
      System.out.println("All Sellers:");
      while (resultSet.next()) {
        int sellerId = resultSet.getInt("seller_id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        Seller seller = new Seller(sellerId, name, email);
        System.out.println("Seller ID: " + sellerId + ", Name: " + name + ", Email: " + email);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Failed to fetch sellers.");
    }
  }
}
