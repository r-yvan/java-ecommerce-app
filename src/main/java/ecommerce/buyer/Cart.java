package main.java.ecommerce.buyer;

import main.java.ecommerce.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {
  private List<Product> cartItems;
  
  public Cart() {
    this.cartItems = new ArrayList<>();
  }
  
  public void addToCart(Product product) {
    cartItems.add(product);
    System.out.println(product.getName() + " added to cart.");
  }
  
  public void viewCart() {
    System.out.println("Items in Cart:");
    for (Product product : cartItems) {
      product.displayProductDetails();
    }
  }
}

