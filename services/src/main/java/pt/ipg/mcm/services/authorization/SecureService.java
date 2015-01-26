package pt.ipg.mcm.services.authorization;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.SecurityContext;
import javax.xml.ws.WebServiceContext;
import java.security.Principal;

public class SecureService {

  private WebServiceContext webServiceContext;

  private SecurityCommon securityCommon;

  protected SecurityCommon getSecurityCommon() {
    if (securityCommon != null) {
      return securityCommon;
    }
    return new SecurityContext4Soap(webServiceContext);
  }

  public SecurityCommon setWsc(WebServiceContext webServiceContext) {
    this.webServiceContext = webServiceContext;
    return getSecurityCommon();
  }

  public SecurityCommon setRc(SecurityContext securityContext) {
    this.securityCommon = new SecurityContext4Rest(securityContext);
    return getSecurityCommon();
  }

  public void checkAuthorization(Role... roles) throws LoginException {
    for (Role role : roles) {
      if (getSecurityCommon().isUserInRole(role.getRoleName())) {
        return;
      }
    }
    Principal principal = getSecurityCommon().getUserPrincipal();
    String name = null;
    if (principal != null) {
      name = principal.getName();
    }
    throw new LoginException("Login " + name + " not authorized for this context");
  }
}
