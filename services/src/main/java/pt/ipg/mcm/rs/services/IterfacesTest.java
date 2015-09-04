package pt.ipg.mcm.rs.services;

import pt.ipg.mcm.calls.client.model.encomendas.GetEncomendasRestOut;
import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestIn;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.rs.conversors.encomenda.ResMinhasEncomendasDetalhe2GetMinhasEncomendas;
import pt.ipg.mcm.xmodel.EncomendaDetalheXml;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


@Path("/itest")
public class IterfacesTest {
  @POST
  @Path("/updateEncomendasRestIn")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateEncomendasRestIn(UpdateEncomendasRestIn request) {
    return Response.ok(request).build();
  }

  @POST
  @Path("/updateEncomendasRestOut")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateEncomendasOut(UpdateEncomendasRestOut updateEncomendasRestOut) {
    return Response.ok(updateEncomendasRestOut).build();
  }

  @POST
  @Path("/getEncomendasRestOut")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEncomendasOut(GetMinhasEncomendasRest getMinhasEncomendasRest) {
    return Response.ok(getMinhasEncomendasRest).build();
  }

  @GET
  @Path("/teste")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEncomendasMinhas() {

    EncomendaDetalheXml encomendaDetalheXml = new EncomendaDetalheXml();
    encomendaDetalheXml.setId(1);
    encomendaDetalheXml.setObservacoes("teste obs");
    encomendaDetalheXml.setEstado(4);
    encomendaDetalheXml.setDataEntrega(new GregorianCalendar(2015, 2, 2).getTime());
    encomendaDetalheXml.setDataCriacao(new GregorianCalendar(2015, 2, 3).getTime());
    List<ProdutoEncomendadoComPreco> lista = new ArrayList<ProdutoEncomendadoComPreco>();

    ProdutoEncomendadoComPreco produtoComPreco = new ProdutoEncomendadoComPreco();
    produtoComPreco.setPreco(BigDecimal.valueOf(200));
    produtoComPreco.setIdProduto(2);
    produtoComPreco.setQuantidade(3);

    lista.add(produtoComPreco);
    encomendaDetalheXml.setProdutosEncomendados(lista);

    final ArrayList<EncomendaDetalheXml> encomendaDetalheXmlList = new ArrayList<>();
    ResMinhasEncomendasDetalhe resMinhasEncomendas = new ResMinhasEncomendasDetalhe(encomendaDetalheXmlList);
    encomendaDetalheXmlList.add(encomendaDetalheXml);

    GetMinhasEncomendasRest minhasEncomendasRest = new ResMinhasEncomendasDetalhe2GetMinhasEncomendas(resMinhasEncomendas)
        .converted();

    return Response.ok(minhasEncomendasRest).build();
  }

}
