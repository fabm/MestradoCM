package pt.ipg.mcm.rs.services;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.calls.client.model.delete.GetRegistosAApagarRest;
import pt.ipg.mcm.calls.client.model.delete.RegistoAApagarRest;
import pt.ipg.mcm.rs.conversors.deleted.GetRegistosAApagarSoap2Rest;
import pt.ipg.mcm.services.SyncDeleteService;

import javax.ejb.EJB;
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
  public Response desync(@PathParam(value = "desync") long syncId) {
    final GetRegistosAApagarRest response = new GetRegistosAApagarSoap2Rest(syncDeleteService.getRegistosAApagar(syncId)).converted();
    return Response.ok(response).build();
  }


}
