package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.xmodel.PostEncomenda;
import pt.ipg.mcm.xmodel.PostEncomendaDetalhe;
import pt.ipg.mcm.xmodel.ResPostMinhasEncomendas;
import pt.ipg.mcm.xmodel.ResPostMinhasEncomendasDetalhe;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/itest")
public class IterfacesTest {
  @GET
  @Path("/resPostMinhasEncomendas")
  @Produces(MediaType.APPLICATION_JSON)
  public Response resPostMinhasEncomendas() {
    ResPostMinhasEncomendas resPostMinhasEncomendas = new ResPostMinhasEncomendas();
    ResPostMinhasEncomendasDetalhe resPostMinhasEncomendasDetalhe = new ResPostMinhasEncomendasDetalhe();
    resPostMinhasEncomendasDetalhe.setClientId(1);
    resPostMinhasEncomendasDetalhe.setServerId(1L);
    resPostMinhasEncomendas.getResPostMinhasEncomendasDetalhes().add(resPostMinhasEncomendasDetalhe);
    return Response.ok(resPostMinhasEncomendas).build();
  }

  @POST
  @Path("/reqPostMinhasEncomendas")
  @Produces(MediaType.APPLICATION_JSON)
  public Response reqPostMinhasEncomendas(PostEncomenda postEncomenda) {
    return Response.ok(postEncomenda).build();
  }

}
