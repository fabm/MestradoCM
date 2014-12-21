package pt.ipg.mcm.errors.services.authorization;

public enum Role {
  ADMINISTRADOR("administrador"),
  CLIENTE("CLIENTE");

  private String strRole;

  private Role(String strRole) {
    this.strRole = strRole;
  }

  public String getRoleName() {
    return strRole;
  }
}
