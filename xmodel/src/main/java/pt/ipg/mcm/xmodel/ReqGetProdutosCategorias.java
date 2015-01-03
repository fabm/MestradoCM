package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-produtos-categorias")
public class ReqGetProdutosCategorias {
  @XmlElement(name = "id-categoria", required = false)
  private Long idCategoria;

  @XmlElement(name = "comFoto", defaultValue = "true")
  private boolean comFoto;

  public boolean isComFoto() {
    return comFoto;
  }

  public void setComFoto(boolean comFoto) {
    this.comFoto = comFoto;
  }

  public Long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Long idCategoria) {
    this.idCategoria = idCategoria;
  }
}
