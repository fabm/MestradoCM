package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.UtilizadorService;
import pt.ipg.mcm.xmodel.UserClienteCreationRequest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UtilizadorRService {

  @Inject
  private UtilizadorService utilizadorService;

  @POST
  @Path("/create")
  @Produces(MediaType.APPLICATION_JSON)
  public Response cria(UserClienteCreationRequest cliente) {
    return Response.ok(utilizadorService.createUserCliente(cliente)).build();
  }

}
