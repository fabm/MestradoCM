package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestIn;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.rs.conversors.encomenda.R2SOutEncomendas;
import pt.ipg.mcm.rs.conversors.encomenda.R2SinEncomendas;
import pt.ipg.mcm.rs.conversors.encomenda.ResMinhasEncomendasDetalhe2GetMinhasEncomendas;
import pt.ipg.mcm.services.EncomendaService;
import pt.ipg.mcm.xmodel.ReqAddEncomendas;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.XInEncomendas;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomendas;

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
  public Response getEncomendas(@Context SecurityContext securityContext, @PathParam("sync") long sync) {
    try {
      encomendaService.setRc(securityContext);
      ResMinhasEncomendasDetalhe minhasEncomendasDetalhe = encomendaService.getMinhasEncomendasDetalhe(sync);
      GetMinhasEncomendasRest converted = new ResMinhasEncomendasDetalhe2GetMinhasEncomendas(minhasEncomendasDetalhe).converted();
      return Response.ok(converted).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  @POST
  @Path("/update")
  @Produces(MediaType.APPLICATION_JSON)
  public Response update(@Context SecurityContext securityContext, UpdateEncomendasRestIn updateEncomendasRestIn) {
    try {
      encomendaService.setRc(securityContext);
      XInEncomendas xInEncomendas = new R2SinEncomendas(updateEncomendasRestIn).converted();
      XOutEncomendas xOutEncomendas = encomendaService.postEncomendas(xInEncomendas);
      final UpdateEncomendasRestOut response = new R2SOutEncomendas(xOutEncomendas).converted();
      return Response.ok(response).build();
    } catch (LoginException e) {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }



}
