package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.NotEmpty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-add-produto")
public class ReqAddProduto {

  @NotEmpty
  @XmlElement(required = true)
  private String nome;

  @NotEmpty
  @XmlElement(name = "preco-unitario", required = true)
  private BigDecimal precoUnitario;

  @NotEmpty
  @XmlElement(required = true)
  private long categoria;

  @NotEmpty
  @XmlElement(required = true)
  protected byte[] foto;

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
