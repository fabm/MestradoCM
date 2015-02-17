package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendasDetalhe extends Retorno{
  private List<Encomenda> listaEncomendas;

  public ResMinhasEncomendasDetalhe() {
  }

  public ResMinhasEncomendasDetalhe(MestradoException e) {
    super(e);
  }

  public List<Encomenda> getListaEncomendas() {
    return listaEncomendas;
  }

  public void setListaEncomendas(List<Encomenda> listaEncomendas) {
    this.listaEncomendas = listaEncomendas;
  }
}
