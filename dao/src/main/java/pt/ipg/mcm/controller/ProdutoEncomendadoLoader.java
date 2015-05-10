package pt.ipg.mcm.controller;

import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ProdutoEncomendadoLoader implements Iterator<ProdutoEncomendadoComPreco>, Iterable<ProdutoEncomendadoComPreco> {
  private Connection connection;
  private long idEncomenda;
  private ResultSet resultSet;
  private Erro erro = null;

  public ProdutoEncomendadoLoader(Connection connection,long idEncomenda) {
    this.connection = connection;
    this.idEncomenda = idEncomenda;
  }

  @Override
  public Iterator<ProdutoEncomendadoComPreco> iterator() {
    try {
      PreparedStatement ps = connection.prepareStatement("SELECT ID_PRODUTO, ID_ENCOMENDA, PRECO_UNITARIO, QUANTIDADE FROM ENCOMENDA_PRODUTO\n" +
          "WHERE ID_ENCOMENDA = ?");
        ps.setLong(1,idEncomenda);
       resultSet = ps.executeQuery();
    } catch (SQLException e) {
      erro = Erro.TECNICO;
    }
    return this;
  }

  public boolean isIteradoComSucesso(){
    return erro == null;
  }

  public Erro getErro() {
    return erro;
  }

  @Override
  public boolean hasNext() {
    try {
      return resultSet.next();
    } catch (SQLException e) {
      erro = Erro.TECNICO;
      return false;
    }
  }

  @Override
  public ProdutoEncomendadoComPreco next() {
    try {
      ProdutoEncomendadoComPreco produtoEncomendadoComPreco = new ProdutoEncomendadoComPreco();
      produtoEncomendadoComPreco.setIdProduto(resultSet.getLong(1));
      produtoEncomendadoComPreco.setPreco(resultSet.getBigDecimal(3));
      produtoEncomendadoComPreco.setQuantidade(resultSet.getInt(3));
      return produtoEncomendadoComPreco;
    } catch (SQLException e) {
      erro = Erro.TECNICO;
      return null;
    }
  }

  @Override
  public void remove() {

  }
}
