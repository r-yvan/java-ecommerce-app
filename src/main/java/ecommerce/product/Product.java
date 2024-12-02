package main.java.ecommerce.product;

public class Product {
  private int productId;
  private String name;
  private double price;
  private int stock;
  
  // Constructor
  public Product(int productId, String name, double price, int stock) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
  
  // Getters and Setters
  public int getProductId() {
    return productId;
  }
  
  public void setProductId(int productId) {
    this.productId = productId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public int getStock() {
    return stock;
  }
  
  public void setStock(int stock) {
    this.stock = stock;
  }
  
  // Display product details
  public void displayProductDetails() {
    System.out.println("Product ID: " + productId);
    System.out.println("Name: " + name);
    System.out.println("Price: $" + price);
    System.out.println("Stock: " + stock + " units");
  }
}
