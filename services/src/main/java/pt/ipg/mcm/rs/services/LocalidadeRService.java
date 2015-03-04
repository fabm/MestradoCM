package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.LocalidadeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/localidade")
public class LocalidadeRService {

  @Inject
  private LocalidadeService localidadeService;

  @GET
  @Path("/todas")
  @Produces(MediaType.APPLICATION_JSON)
  public Response todas() {
    return Response.ok(localidadeService.getAllLocalidades()).build();
  }

}
