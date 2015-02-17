package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/authentication")
public class AuthenticationRService {
  @Inject
  private AuthenticationService authenticationService;

  @GET
  @Path("/login")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEncomenda(@Context SecurityContext securityContext) {
    return Response.ok(authenticationService.login()).build();
  }


}
