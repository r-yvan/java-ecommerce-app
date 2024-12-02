package main.java.ecommerce.buyer;

import main.java.ecommerce.User;

public class Buyer extends User {
  public Buyer(int userId, String name, String email) {
    super(userId, name, email);
  }
  
  @Override
  public void displayRole() {
    System.out.println(name + " is a Buyer.");
  }
}

