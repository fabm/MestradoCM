package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestIn;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.rs.conversors.encomenda.R2SOutEncomendas;
import pt.ipg.mcm.rs.conversors.encomenda.R2SinEncomendas;
import pt.ipg.mcm.rs.conversors.encomenda.ResMinhasEncomendasDetalhe2GetMinhasEncomendas;
import pt.ipg.mcm.services.EncomendaService;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasIn;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasOut;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
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
    public Response addEncomenda(AddEncomendasIn addEncomendasIn, @Context SecurityContext securityContext) {
        try {
            encomendaService.setRc(securityContext);
            return Response.ok(encomendaService.addEncomendas(addEncomendasIn)).build();
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
            AddEncomendasIn addEncomendasIn = new R2SinEncomendas(updateEncomendasRestIn).converted();
            AddEncomendasOut addEncomendasOut = encomendaService.addEncomendas(addEncomendasIn);
            final UpdateEncomendasRestOut response = new R2SOutEncomendas(addEncomendasOut).converted();
            return Response.ok(response).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
