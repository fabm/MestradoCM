package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class EncomendaXml<T extends ProdutoEncomendado> {
  private Long idCliente;

  private Date dataEntrega;

  public Long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Long idCliente) {
    this.idCliente = idCliente;
  }

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public abstract List<T> getProdutoList();

  public abstract void setProdutoList(List<T> produtoList);
}
