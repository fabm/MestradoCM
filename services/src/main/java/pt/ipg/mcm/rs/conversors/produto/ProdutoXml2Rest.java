package pt.ipg.mcm.rs.conversors.produto;

import org.apache.commons.codec.binary.Base64;
import pt.ipg.mcm.calls.client.model.produtos.ProdutoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.ProdutoXml;


public class ProdutoXml2Rest extends AbstractConversor<ProdutoXml, ProdutoRest> {
  public ProdutoXml2Rest(ProdutoXml source) {
    super(source);
  }

  @Override
  public ProdutoRest converted() {
    ProdutoRest produtoRest = new ProdutoRest();
    produtoRest.setNome(source.getNome());
    produtoRest.setCategoria(source.getCategoria());
    if (source.getFoto() != null) {
      produtoRest.setFoto(new String(Base64.encodeBase64(source.getFoto())));
    }
    produtoRest.setId(source.getId());
    produtoRest.setPrecoUnitario(source.getPrecoUnitario());
    return produtoRest;
  }
}
