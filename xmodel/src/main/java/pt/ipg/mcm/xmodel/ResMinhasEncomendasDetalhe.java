package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendasDetalhe extends Retorno{
  private List<EncomendaDetalheXml> listaEncomendasDetalheXmls;

  public ResMinhasEncomendasDetalhe() {
    super();
  }

  public ResMinhasEncomendasDetalhe(MestradoException e) {
    super(e);
  }

  public List<EncomendaDetalheXml> getListaEncomendasDetalheXmls() {
    return listaEncomendasDetalheXmls;
  }

  public void setListaEncomendasDetalheXmls(List<EncomendaDetalheXml> listaEncomendasDetalheXmls) {
    this.listaEncomendasDetalheXmls = listaEncomendasDetalheXmls;
  }
}
