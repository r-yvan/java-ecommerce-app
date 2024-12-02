package main.java.ecommerce.product;

public class Product {
  private int productId;
  private String name;
  private double price;
  private int stock;
  private int quantity;
  
  public Product(int productId, String name, double price, int stock) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }
  
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
  
  public void displayProductDetails() {
    System.out.println("Product ID: " + productId);
    System.out.println("Name: " + name);
    System.out.println("Price: $" + price);
    System.out.println("Stock: " + stock + " units");
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public void decreaseQuantity(int amount) {
    if (quantity >= amount) {
      this.quantity -= amount;
    } else {
      System.out.println("Not enough stock to decrease by " + amount);
    }
  }
  
  public void increaseQuantity(int amount) {
    this.quantity += amount;
  }
  
  public void displayProductInfo() {
    System.out.println("Product ID: " + productId + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity);
  }
}
