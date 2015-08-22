package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-add-categoria")
public class ReqAddCategoria {

  @NotEmpty
  @XmlElement(required = true)
  private String nome;

  @NotEmpty
  @XmlElement(required = true)
  private String descricao;

  public String getNome() {
    return nome;
  }

  public void setNome(String value) {
    this.nome = value;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String value) {
    this.descricao = value;
  }

}
