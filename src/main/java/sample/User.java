package sample;

import org.springframework.data.mongodb.core.mapping.Document;

import java.security.SecureRandom;

@Document(collection = "users")
public class User {

  private String login;
  private String password;
  private String phone;
  private int code;

  public User(String login, String password, String phone) {
    this.login = login;
    this.password = password;
    this.phone = phone;
  }

  public void generateCode() {
    SecureRandom random = new SecureRandom();
    this.code = 100000 + random.nextInt(900000);
  }

  public int getCode() {
    return this.code;
  }

}
