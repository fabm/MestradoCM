package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-all-categorias")
public class ResGetAllCategorias {

  @XmlElementWrapper(name = "categorias",required = true)
  @XmlElement(name = "categoria",required = false)
  private List<Categoria> categorias;

  @XmlElement(required = true)
  private Retorno retorno;

  public List<Categoria> getCategorias() {
    if(categorias == null){
        categorias = new ArrayList<Categoria>();
    }
    return categorias;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
