package main.java.ecommerce.main;
import main.java.ecommerce.buyer.Buyer;
import main.java.ecommerce.buyer.Cart;
import main.java.ecommerce.product.Product;

public class Main {
  public static void main(String[] args) {
    // Sample Product
    Product product1 = new Product(1, "Laptop", 1200.0, 10);
    Product product2 = new Product(2, "Smartphone", 800.0, 20);
    
    // Sample Buyer
    Buyer buyer = new Buyer(101, "John Doe", "john@example.com");
    buyer.displayRole();
    
    // Buyer's Cart
    Cart cart = new Cart();
    cart.addToCart(product1);
    cart.addToCart(product2);
    
    // View Cart
    cart.viewCart();
  }
}

