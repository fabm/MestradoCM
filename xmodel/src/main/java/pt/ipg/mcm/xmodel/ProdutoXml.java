package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProdutoXml {

  @XmlElement(required = true)
  private String nome;

  @XmlElement(name = "preco-unitario", required = true)
  private BigDecimal precoUnitario;

  @XmlElement(required = true)
  private long categoria;

  @XmlElement(required = false)
  private byte[] foto;

  @XmlElement(required = false)
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String value) {
    this.nome = value;
  }

  public BigDecimal getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(BigDecimal value) {
    this.precoUnitario = value;
  }

  public long getCategoria() {
    return categoria;
  }

  public void setCategoria(long value) {
    this.categoria = value;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] value) {
    this.foto = value;
  }
}
