package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.services.SyncDeleteService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/delete")
public class DeleteSyncRService {

  @Inject
  private SyncDeleteService syncDeleteService;

  @GET
  @Path("/desync/{desync}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response desync(@PathParam(value = "desync") long sincId) {
    return Response.ok(syncDeleteService.getRegistosAApagar(sincId)).build();
  }


}
