package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Retorno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendas extends Retorno{

    public  ResMinhasEncomendas(){

    }

  private List<MinhaEncomenda> minhasEncomendasList;

    public ResMinhasEncomendas(MestradoException e) {
        super(e);

    }

    public List<MinhaEncomenda> getMinhasEncomendasList() {
    return minhasEncomendasList;
  }

  public void setMinhasEncomendasList(List<MinhaEncomenda> minhasEncomendasList) {
    this.minhasEncomendasList = minhasEncomendasList;
  }
}
