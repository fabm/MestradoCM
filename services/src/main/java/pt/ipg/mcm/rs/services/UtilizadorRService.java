package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.calls.client.model.user.CreateUserClientRestIn;
import pt.ipg.mcm.rs.conversors.RetornoSoap2Rest;
import pt.ipg.mcm.rs.conversors.user.CreateUserClientInRest2Soap;
import pt.ipg.mcm.services.UtilizadorService;
import pt.ipg.mcm.xmodel.ResCreationUserClient;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
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
  public Response cria(CreateUserClientRestIn cliente) {
    ResCreationUserClient userCliente = utilizadorService.createUserCliente(new CreateUserClientInRest2Soap(cliente).converted());
    return Response.ok(new RetornoSoap2Rest(userCliente).converted()).build();
  }

}
