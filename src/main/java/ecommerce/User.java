package main.java.ecommerce;

public abstract class User {
  protected int userId;
  protected String name;
  protected String email;
  
  public User(int userId, String name, String email) {
    this.userId = userId;
    this.name = name;
    this.email = email;
  }
  
  public abstract void displayRole();
  
  public String getName() {
    return name;
  }
  
  public String getEmail() {
    return email;
  }
}

