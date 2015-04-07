package pt.ipg.mcm.calls;

public class AuthBasicUtf8 {
  private String login;
  private String password;

  public AuthBasicUtf8(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public AuthBasicUtf8() {
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
