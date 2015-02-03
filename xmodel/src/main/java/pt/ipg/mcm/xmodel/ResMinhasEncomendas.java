package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.xmodel.Retorno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendas extends Retorno{
  private List<MinhaEncomenda> minhasEncomendasList;

  public List<MinhaEncomenda> getMinhasEncomendasList() {
    return minhasEncomendasList;
  }

  public void setMinhasEncomendasList(List<MinhaEncomenda> minhasEncomendasList) {
    this.minhasEncomendasList = minhasEncomendasList;
  }
}
