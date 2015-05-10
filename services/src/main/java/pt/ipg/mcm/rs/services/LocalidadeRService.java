package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.rs.conversors.LocalidadesSoap2Rest;
import pt.ipg.mcm.services.LocalidadeService;
import pt.ipg.mcm.xmodel.ResGetAllLocalidades;

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

  @GET
  @Path("/filtro/{pagina}/{filtro}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response comFiltro(@PathParam("pagina") int pagina, @PathParam("filtro") String filtro) {
    ResGetAllLocalidades localidadesComFiltroEPagina = localidadeService.getLocalidadesComFiltroEPagina(filtro, pagina);
    LocalidadesSoap2Rest localidadesSoap2Rest = new LocalidadesSoap2Rest(localidadesComFiltroEPagina);
    return Response.ok(localidadesSoap2Rest).build();
  }

  @GET
  @Path("/filtro/{pagina}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response comFiltro(@PathParam("pagina") int pagina) {
    return Response.ok(localidadeService.getLocalidadesComPagina(pagina)).build();
  }

}
