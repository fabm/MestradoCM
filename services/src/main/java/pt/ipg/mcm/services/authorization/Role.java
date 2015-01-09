package pt.ipg.mcm.services.authorization;

public enum Role {
  ADMINISTRADOR("administrador"),
  CLIENTE("cliente"),
  CONVIDADO("convidado"),
  PADEIRO("padeiro")
  ;

  private String strRole;

  private Role(String strRole) {
    this.strRole = strRole;
  }

  public String getRoleName() {
    return strRole;
  }
}
