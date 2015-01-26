package pt.ipg.mcm.services.authorization;

import javax.xml.ws.WebServiceContext;
import java.security.Principal;

public class SecurityContext4Soap implements SecurityCommon {
  private WebServiceContext webServiceContext;

  public SecurityContext4Soap(WebServiceContext securityContext) {
    this.webServiceContext = securityContext;
  }

  @Override
  public Principal getUserPrincipal() {
    return webServiceContext.getUserPrincipal();
  }

  @Override
  public boolean isUserInRole(String role) {
    return webServiceContext.isUserInRole(role);
  }

}
