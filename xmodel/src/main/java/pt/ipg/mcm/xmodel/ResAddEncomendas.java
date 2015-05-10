package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResAddEncomendas extends RetornoSoap {
  private List<Long> idsEncomendas;
  public ResAddEncomendas(MestradoException e) {
    super(e);
  }

  public ResAddEncomendas(List<Long> idsEncomendas) {
    super(1,"Encomendas criadas com sucesso");
    this.idsEncomendas = idsEncomendas;
  }

  public ResAddEncomendas() {
  }

  public List<Long> getIdsEncomendas() {
    return idsEncomendas;
  }

  public void setIdsEncomendas(List<Long> idsEncomendas) {
    this.idsEncomendas = idsEncomendas;
  }
}
