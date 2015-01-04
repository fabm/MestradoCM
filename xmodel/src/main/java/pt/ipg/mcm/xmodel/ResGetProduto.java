package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-produto")
public class ResGetProduto {

  @NotNull
  @XmlElement(required = true)
  private String nome;

  @NotNull
  @XmlElement(name = "preco-unitario", required = true)
  private BigDecimal precoUnitario;

  @NotNull
  @XmlElement(required = true)
  private long categoria;

  @XmlElement(required = false)
  private byte[] foto;

  @XmlElement(required = false)
  private Retorno retorno;

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

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
