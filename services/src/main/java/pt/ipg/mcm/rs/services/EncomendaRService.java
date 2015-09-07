package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.calls.client.model.encomendas.AddAndUpdateEncomendasInRest;
import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.calls.client.model.encomendas.AddEncomendasInRest;
import pt.ipg.mcm.rs.conversors.encomenda.*;
import pt.ipg.mcm.services.EncomendaService;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.AddAndUpdateEncomendasIn;
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
    public Response addEncomenda(AddEncomendasInRest addEncomendasInRest, @Context SecurityContext securityContext) {
        try {
            encomendaService.setRc(securityContext);
            final AddEncomendasInFromRest addEncomendasInFromRest = new AddEncomendasInFromRest(addEncomendasInRest);
            final AddEncomendasOut addEncomendasOut = encomendaService.addEncomendas(addEncomendasInFromRest.converted());
            return Response.ok(new AddEncomendasOut2Rest(addEncomendasOut).converted()).build();
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
    public Response update(@Context SecurityContext securityContext, AddAndUpdateEncomendasInRest addAndUpdateEncomendasInRest) {
        try {
            encomendaService.setRc(securityContext);
            AddAndUpdateEncomendasIn addAndUpdateEncomendasIn = new AddAndUpdateEncomendasIn();
            addAndUpdateEncomendasIn.convert(addAndUpdateEncomendasInRest);
            AddEncomendasOut addEncomendasOut = encomendaService.addAndUpdateEncomendas(addAndUpdateEncomendasIn);
            return Response.ok(addEncomendasOut.convert()).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
