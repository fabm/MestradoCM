package pt.ipg.mcm.services.authorization;

import javax.security.auth.login.LoginException;
import javax.xml.ws.WebServiceContext;

public class RolesAuthorized {

  private WebServiceContext webServiceContext;

  public RolesAuthorized(WebServiceContext webServiceContext) {
    this.webServiceContext = webServiceContext;
  }


  public void checkAuthorization(Role... roles) throws LoginException {
    for (Role role : roles) {
      if (webServiceContext.isUserInRole(role.getRoleName())) {
        return;
      }
    }
    throw new LoginException("Login " + webServiceContext.getUserPrincipal().getName() + " not authorized for this context");
  }
}
