package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-add-padeiro")
public class ReqAddPadeiro {

  @NotEmpty
  @XmlElement(required = true)
  private String nome;

  @NotNull
  @XmlElement(name = "id-utilizador",required = true)
  private Long idUtilizador;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Long getIdUtilizador() {
    return idUtilizador;
  }

  public void setIdUtilizador(Long idUtilizador) {
    this.idUtilizador = idUtilizador;
  }
}
