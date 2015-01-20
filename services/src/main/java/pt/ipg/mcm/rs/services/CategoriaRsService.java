package pt.ipg.mcm.rs.services;


import pt.ipg.mcm.services.CategoriaService;
import pt.ipg.mcm.xmodel.ReqAddCategoria;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categoria")
public class CategoriaRsService {

  @Inject
  private CategoriaService categoriaService;


  @GET
  @Path("/todas")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategorias() {
    return Response.ok(categoriaService.getAllCategorias()).build();
  }


  @POST
  @Path("/inserir")
  @Produces(MediaType.APPLICATION_JSON)
  public Response insertEncomenda(ReqAddCategoria reqAddCategoria) {
    return Response.ok(categoriaService.addCategoria(reqAddCategoria)).build();
  }

  @GET
  @Path("/desync/{versao}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategorias(@PathParam("versao") Long versao) {
    return Response.ok(categoriaService.getCategoriasDeSync(versao)).build();
  }



}
