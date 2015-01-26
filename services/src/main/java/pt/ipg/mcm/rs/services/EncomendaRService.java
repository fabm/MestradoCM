package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.EncomendaService;
import pt.ipg.mcm.services.authorization.SecurityContext4Rest;
import pt.ipg.mcm.xmodel.ReqAddEncomenda;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/encomenda")
public class EncomendaRService {

  @Inject
  private EncomendaService encomendaService;

  @POST
  @Path("/add")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEncomenda(ReqAddEncomenda reqAddEncomenda,@Context SecurityContext securityContext){
    try {
      encomendaService.setRc(securityContext);
      return Response.ok(encomendaService.addEncomenda(reqAddEncomenda)).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }
}
