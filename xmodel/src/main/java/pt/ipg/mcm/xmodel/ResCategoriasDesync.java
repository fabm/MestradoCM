package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResCategoriasDesync {
  @XmlElementWrapper(name = "categorias")
  @XmlElement(name = "categoria")
  private List<Categoria> categorias;

  private long maxVersao;

  private Retorno retorno;

  public List<Categoria> getCategorias() {
    if (categorias == null) {
      categorias = new ArrayList<Categoria>();
    }
    return categorias;
  }

  public long getMaxVersao() {
    return maxVersao;
  }

  public void setMaxVersao(long maxVersao) {
    this.maxVersao = maxVersao;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
