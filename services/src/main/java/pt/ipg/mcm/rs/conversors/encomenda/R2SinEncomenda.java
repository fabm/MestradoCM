package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.DateHelper;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaIn;
import pt.ipg.mcm.xmodel.encomendas.ProdutoAEncomendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class R2SinEncomenda extends AbstractConversor<EncomendaRest, EncomendaIn> {

  public R2SinEncomenda(EncomendaRest inObject) {
    super(inObject);
  }

  @Override
  public EncomendaIn converted() {
    return toEncomedaSoapIn();
  }

  private EncomendaIn toEncomedaSoapIn() {
    List<ProdutoAEncomendar> produtoAEncomendarList = new ArrayList<ProdutoAEncomendar>();
    for (ProdutoEncomendadoRest produtoEncomendadoRest: source.getProdutoEncomendadoRests()) {
      produtoAEncomendarList.add(new R2SinProdutoEncomendado(produtoEncomendadoRest).converted());
    }

    EncomendaIn encomendaIn = new EncomendaIn();
    encomendaIn.setProdutoAEncomendarList(produtoAEncomendarList);
    try {
      encomendaIn.setDataEntrega(new DateHelper(DateHelper.Format.COMPACT).toDate(source.getDate()));
    } catch (ParseException e) {
      throw new IllegalStateException(e);
    }
    return encomendaIn;
  }


}
