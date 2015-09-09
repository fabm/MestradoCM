package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.logging.Logger;

@XmlType(name = "Categoria")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categoria {
  @XmlElement(name = "nome")
  private String nome;
  @XmlElement(name = "descricao")
  private String descricao;

  @XmlTransient
  protected Long id;
  @XmlTransient
  private Long versao;

  @XmlTransient
  public Long getVersao() {
    return versao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Long getId() {
    return id;
  }

  public void setVersao(Long versao) {
    this.versao = versao;
  }
}
