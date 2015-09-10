package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoComPrecoRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProdutoEncomendadoComPreco extends ProdutoEncomendado{

  private BigDecimal preco;

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public ProdutoEncomendadoComPrecoRest convert() {
    ProdutoEncomendadoComPrecoRest produtoEncomendadoComPrecoRest = super.convert(new ProdutoEncomendadoComPrecoRest());
    produtoEncomendadoComPrecoRest.setPreco(preco.multiply(new BigDecimal(100)).intValue());
    return produtoEncomendadoComPrecoRest;
  }

}
