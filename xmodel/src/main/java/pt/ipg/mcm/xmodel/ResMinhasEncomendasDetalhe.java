package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendasDetalhe extends RetornoSoap {
  private List<EncomendaDetalheXml> listaEncomendasDetalheXmls;
  private long maxSync;

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

  public long getMaxSync() {
    return maxSync;
  }

  public void setMaxSync(long maxSync) {
    this.maxSync = maxSync;
  }
}
