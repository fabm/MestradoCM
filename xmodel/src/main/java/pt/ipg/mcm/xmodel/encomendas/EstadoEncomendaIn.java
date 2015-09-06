package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EstadoEncomendaIn {

  private int estado;
  private long idEncomenda;

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }
}
