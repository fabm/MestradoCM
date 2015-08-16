package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-add-produto")
public class ReqAddProduto {

  @XmlTransient
  private int id;

  @NotEmpty
  @XmlElement(required = true)
  private String nome;

  @DecimalMin("0.00")
  @XmlElement(name = "preco-unitario", required = true)
  private BigDecimal precoUnitario;

  @Min(0)
  @XmlElement(required = true)
  private long categoria;

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

  public int getId() {
    return id;
  }
}
