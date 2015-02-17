package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.EncomendaService;
import pt.ipg.mcm.xmodel.EncomendaXml;
import pt.ipg.mcm.xmodel.EncomendaXmlComPreco;
import pt.ipg.mcm.xmodel.EncomendaXmlSemPreco;
import pt.ipg.mcm.xmodel.ProdutoEncomendado;
import pt.ipg.mcm.xmodel.ReqAddEncomendas;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/encomenda")
public class EncomendaRService {

  @Inject
  private EncomendaService encomendaService;

  @POST
  @Path("/add")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEncomenda(ReqAddEncomendas reqAddEncomendas, @Context SecurityContext securityContext) {
    try {
      encomendaService.setRc(securityContext);
      return Response.ok(encomendaService.addEncomendas(reqAddEncomendas)).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @GET
  @Path("/minhas/{sync}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response minhasEncomenda(@Context SecurityContext securityContext, @PathParam("sync") long sync) {
    try {
      encomendaService.setRc(securityContext);
      return Response.ok(encomendaService.getMinhasEncomendasDetalhe(sync)).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @GET
  @Path("/teste")
  @Produces(MediaType.APPLICATION_JSON)
  public Response teste() {
    ReqAddEncomendas reqAddEncomendas = new ReqAddEncomendas();
    List<EncomendaXmlSemPreco> lista = new ArrayList<EncomendaXmlSemPreco>();

    EncomendaXmlSemPreco encomenda = new EncomendaXmlSemPreco();
    encomenda.setIdCliente(1L);
    encomenda.setDataEntrega(new Date());
    List<ProdutoEncomendado> produtos = new ArrayList<ProdutoEncomendado>();
    ProdutoEncomendado produto = new ProdutoEncomendado();
    produto.setIdProduto(2);
    produto.setQuantidade(2);
    produtos.add(produto);
    encomenda.setProdutoList(produtos);
    lista.add(encomenda);

    encomenda = new EncomendaXmlSemPreco();
    encomenda.setIdCliente(2L);
    encomenda.setDataEntrega(new Date());
    produtos = new ArrayList<ProdutoEncomendado>();
    produto = new ProdutoEncomendado();
    produto.setIdProduto(2);
    produto.setQuantidade(2);
    produtos.add(produto);
    encomenda.setProdutoList(produtos);
    lista.add(encomenda);

    reqAddEncomendas.setEncomendas(lista);

    return Response.ok(reqAddEncomendas).build();
  }


}
