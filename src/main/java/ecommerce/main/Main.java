package main.java.ecommerce.main;

import main.java.ecommerce.buyer.Buyer;
import main.java.ecommerce.buyer.Cart;
import main.java.ecommerce.product.Product;
import main.java.ecommerce.seller.Seller;
import util.DatabaseConnector;

import java.sql.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try (Connection conn = DatabaseConnector.getConnection()) {
      Scanner scanner = new Scanner(System.in);
      
      Product product1 = new Product(1, "Laptop", 1200.0, 10);
      Product product2 = new Product(2, "Smartphone", 800.0, 20);
      Product product3 = new Product(3, "Tablet", 1400.0, 30);
      Product product4 = new Product(4, "Wireless Mouse", 600.0, 40);
      Product product5 = new Product(5, "Desktop Computer", 2000.0, 5);
      Product product6 = new Product(6, "AirPods Pro 5", 300.0, 90);
      Product product7 = new Product(7, "PC Stand", 40.0, 200);
      Product product8 = new Product(8, "Cell Phone", 15.0, 500);
      Product product9 = new Product(9, "PC Adapter", 50.0, 80);
      Product product10 = new Product(10, "Ergonomic Keyboard", 20.0, 70);
      
      boolean running = true;
      System.out.println("Welcome to the E-Commerce Platform!");
      
      while (running) {
        System.out.println("\nAvailable Sellers:");
        listSellers(conn);
        
        System.out.print("Enter Seller ID to interact with or 'exit' to quit: ");
        String input = scanner.nextLine();
        
        if (input.equalsIgnoreCase("exit")) {
          running = false;
          break;
        }
        
        int sellerId = Integer.parseInt(input);
        Seller seller = getSellerById(conn, sellerId);
        
        if (seller == null) {
          System.out.println("Seller not found. Please try again.");
          continue;
        }
        
        System.out.println("\nInteracting with Seller: " + seller.getName());
        listSellerProducts(conn, sellerId);
        
        Buyer buyer = new Buyer(101, "Rubuto Yvan", "rubuto.yvan@gmail.com");
        Cart cart = new Cart();
        
        boolean buyerSession = true;
        while (buyerSession) {
          System.out.println("\nOptions: [add, view-cart, notify-seller, buy, back]");
          System.out.print("Enter action: ");
          String action = scanner.nextLine();
          
          switch (action.toLowerCase()) {
            case "add":
              System.out.print("Enter Product ID to add to cart: ");
              int productId = Integer.parseInt(scanner.nextLine());
              Product product = getProductById(conn, productId);
              if (product != null) {
                cart.addToCart(product);
              } else {
                System.out.println("Product not found.");
              }
              break;
            
            case "view-cart":
              cart.viewCart();
              break;
            
            case "notify-seller":
              System.out.println("Stock is low for some products!");
              seller.respond("Rubuto Yvan", product1, "Thank You!!" );
              break;
            
            case "buy":
              System.out.println("Order placed successfully! Thank you for shopping.");
              buyerSession = false;
              running = false;
              break;
            
            case "back":
              buyerSession = false;
              break;
            
            default:
              System.out.println("Invalid action. Try again.");
              break;
          }
        }
      }
      
      System.out.println("Thank you for using the E-Commerce Platform!");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  private static void listSellers(Connection conn) throws SQLException {
    String sql = "SELECT seller_id, name FROM sellers";
    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        System.out.println("ID: " + rs.getInt("seller_id") + ", Name: " + rs.getString("name"));
      }
    }
  }
  
  private static Seller getSellerById(Connection conn, int sellerId) throws SQLException {
    String sql = "SELECT * FROM sellers WHERE seller_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, sellerId);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return new Seller(rs.getInt("seller_id"), rs.getString("name"), rs.getString("email"));
        }
      }
    }
    return null;
  }
  
  private static void listSellerProducts(Connection conn, int sellerId) throws SQLException {
    String sql = "SELECT * FROM products WHERE seller_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, sellerId);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          System.out.println("Product ID: " + rs.getInt("product_id") +
                  ", Name: " + rs.getString("name") +
                  ", Price: $" + rs.getDouble("price") +
                  ", Stock: " + rs.getInt("stock"));
        }
      }
    }
  }
  
  private static Product getProductById(Connection conn, int productId) throws SQLException {
    String sql = "SELECT * FROM products WHERE product_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, productId);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return new Product(
                  rs.getInt("product_id"),
                  rs.getString("name"),
                  rs.getDouble("price"),
                  rs.getInt("stock")
          );
        }
      }
    }
    return null;
  }
}
