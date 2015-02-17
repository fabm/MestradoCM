package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.ProdutoService;
import pt.ipg.mcm.xmodel.ReqGetProdutosCategorias;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/produto")
public class ProdutoRService {

  @Inject
  private ProdutoService produtoService;

  @GET
  @Path("/porCategoria/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response porCategroia(@PathParam(value = "id") Long id) {
    ReqGetProdutosCategorias reqGetProdutosCategorias = new ReqGetProdutosCategorias();
    reqGetProdutosCategorias.setComFoto(false);
    reqGetProdutosCategorias.setIdCategoria(id);
    return Response.ok(produtoService.getProdutosCategorias(reqGetProdutosCategorias)).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response detalhe(@PathParam(value = "id") Long id) {
    return Response.ok(produtoService.getProduto(id.intValue())).build();
  }

  @GET
  @Path("desync/{desync}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response desync(@PathParam(value = "desync") Long id) {
    return Response.ok(produtoService.getProdutosDeSync(id)).build();
  }


}
