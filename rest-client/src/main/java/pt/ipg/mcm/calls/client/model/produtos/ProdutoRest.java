package pt.ipg.mcm.calls.client.model.produtos;

import java.math.BigDecimal;

public class ProdutoRest {
  private String nome;
  private int precoUnitario;
  private long categoria;
  private String foto;
  private long id;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getPrecoUnitario() {
    return precoUnitario;
  }

  public void setPrecoUnitario(int precoUnitario) {
    this.precoUnitario = precoUnitario;
  }

  public long getCategoria() {
    return categoria;
  }

  public void setCategoria(long categoria) {
    this.categoria = categoria;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
