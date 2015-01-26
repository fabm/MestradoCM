package pt.ipg.mcm.services.authorization;

import java.security.Principal;

/**
 * Created by francisco on 25/01/15.
 */
public interface SecurityCommon {
  Principal getUserPrincipal();

  boolean isUserInRole(String s);
}
