package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaDetalheRest;
import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.EncomendaDetalheXml;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;

import java.util.List;

public class ResMinhasEncomendasDetalhe2GetMinhasEncomendas extends AbstractConversor<ResMinhasEncomendasDetalhe,GetMinhasEncomendasRest>{

  public ResMinhasEncomendasDetalhe2GetMinhasEncomendas(ResMinhasEncomendasDetalhe source) {
    super(source);
  }

  @Override
  public GetMinhasEncomendasRest converted() {
    GetMinhasEncomendasRest getMinhasEncomendasRest = new GetMinhasEncomendasRest();
    List<EncomendaDetalheRest> list = getMinhasEncomendasRest.createEncomendaDetalheRestList();
    for (EncomendaDetalheXml encomendaDetalheXml:source.getListaEncomendasDetalheXmls()){
      list.add(new EncomendaDetalheXml2Rest(encomendaDetalheXml).converted());
    }
    getMinhasEncomendasRest.setMaxSync(source.getMaxSync());
    return getMinhasEncomendasRest;
  }
}
