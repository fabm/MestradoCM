package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.DateHelper;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaSoapIn;
import pt.ipg.mcm.xmodel.encomendas.ProdutoSoapIn;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class R2SinEncomenda extends AbstractConversor<EncomendaRest, EncomendaSoapIn> {

  public R2SinEncomenda(EncomendaRest inObject) {
    super(inObject);
  }

  @Override
  public EncomendaSoapIn converted() {
    return toEncomedaSoapIn();
  }

  private EncomendaSoapIn toEncomedaSoapIn() {
    List<ProdutoSoapIn> produtoSoapInList = new ArrayList<ProdutoSoapIn>();
    for (ProdutoEncomendadoRest produtoEncomendadoRest: source.getProdutoEncomendadoRests()) {
      produtoSoapInList.add(new R2SinProdutoEncomendado(produtoEncomendadoRest).converted());
    }

    EncomendaSoapIn encomendaSoapIn = new EncomendaSoapIn();
    encomendaSoapIn.setXInProdutos(produtoSoapInList);
    try {
      encomendaSoapIn.setDataEntrega(new DateHelper(DateHelper.Format.COMPACT).toDate(source.getDate()));
      encomendaSoapIn.setClientId(source.getClientId());
    } catch (ParseException e) {
      throw new IllegalStateException(e);
    }
    return encomendaSoapIn;
  }


}
