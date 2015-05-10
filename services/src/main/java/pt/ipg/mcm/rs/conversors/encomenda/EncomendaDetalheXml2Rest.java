package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.DateHelper;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaDetalheRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoComPrecoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.EncomendaDetalheXml;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;

import java.util.List;

public class EncomendaDetalheXml2Rest extends AbstractConversor<EncomendaDetalheXml, EncomendaDetalheRest> {
  public EncomendaDetalheXml2Rest(EncomendaDetalheXml source) {
    super(source);
  }

  @Override
  public EncomendaDetalheRest converted() {
    EncomendaDetalheRest encomendaDetalheRest = new EncomendaDetalheRest();
    encomendaDetalheRest.setDataCriacao(new DateHelper(DateHelper.Format.COMPACT).toString(source.getDataCriacao()));
    if (source.getDataEntrega() != null) {
      encomendaDetalheRest.setDataEntrega(new DateHelper(DateHelper.Format.COMPACT).toString(source.getDataEntrega()));
    }
    encomendaDetalheRest.setEstado(source.getEstado());
    encomendaDetalheRest.setId(source.getId());
    encomendaDetalheRest.setObservacoes(source.getObservacoes());
    List<ProdutoEncomendadoComPrecoRest> list = encomendaDetalheRest.createProdutosEncomendados();
    for (ProdutoEncomendadoComPreco produtoEncomendadoComPreco : source.getProdutosEncomendados()) {
      list.add(new ProdutoEncomendadoComPreco2Rest(produtoEncomendadoComPreco).converted());
    }
    return encomendaDetalheRest;
  }
}
