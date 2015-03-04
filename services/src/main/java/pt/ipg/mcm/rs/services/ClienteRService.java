package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.ClienteService;
import pt.ipg.mcm.xmodel.ReqAddClienteUtilizador;

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
  public Response addEncomenda(ReqAddClienteUtilizador reqAddClienteUtilizador, @Context SecurityContext securityContext) {
    try {
      clienteService.setRc(securityContext);
      return Response.ok(clienteService.addClienteUtilizador(reqAddClienteUtilizador)).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @GET
  @Path("/teste")
  @Produces(MediaType.APPLICATION_JSON)
  public Response teste() {
    ReqAddClienteUtilizador reqAddClienteUtilizador = new ReqAddClienteUtilizador();
    reqAddClienteUtilizador.setContribuinte(1234);
    reqAddClienteUtilizador.setNome("Francisco Monteiro");
    reqAddClienteUtilizador.setMorada("Guarda");
    reqAddClienteUtilizador.setPorta("2Esq");
    reqAddClienteUtilizador.setDataNascimento(new Date());
    reqAddClienteUtilizador.setEmail("francisco@sapo.pt");
    reqAddClienteUtilizador.setContacto("969999999");
    reqAddClienteUtilizador.setLocalidade(6300559);
    reqAddClienteUtilizador.setLogin("francisco");
    reqAddClienteUtilizador.setPassword("francisco");
    return Response.ok(reqAddClienteUtilizador).build();
  }


}
