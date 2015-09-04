package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.ClienteService;
import pt.ipg.mcm.xmodel.ReqAddClienteUtilizador;
import pt.ipg.mcm.xmodel.UserClienteCreationRequest;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Date;

@Path("/utilizador")
public class ClienteRService {

  @Inject
  private ClienteService clienteService;

  @POST
  @Path("/registar")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addCliente(UserClienteCreationRequest reqAddClienteUtilizador, @Context SecurityContext securityContext) {
    try {
      clienteService.setRc(securityContext);
      return Response.ok(clienteService.addClienteUtilizador(reqAddClienteUtilizador)).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

}
