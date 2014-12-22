package pt.ipg.mcm.services.authorization;

import javax.security.auth.login.LoginException;
import javax.xml.ws.WebServiceContext;
import java.security.Principal;

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
    Principal principal = webServiceContext.getUserPrincipal();
    String name = null;
    if(principal!=null){
      name = principal.getName();
    }
    throw new LoginException("Login " + name + " not authorized for this context");
  }
}
