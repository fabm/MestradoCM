package pt.ipg.mcm.services.authorization;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecurityContext4Rest implements SecurityCommon{
  private SecurityContext securityContext;

  public SecurityContext4Rest(SecurityContext securityContext) {
    this.securityContext = securityContext;
  }

  @Override
  public Principal getUserPrincipal() {
    return securityContext.getUserPrincipal();
  }

  @Override
  public boolean isUserInRole(String role) {
    return securityContext.isUserInRole(role);
  }

}
