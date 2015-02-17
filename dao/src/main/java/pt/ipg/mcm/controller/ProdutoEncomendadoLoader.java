package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.EncomendaProdutoEntity;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.xmodel.ProdutoEncomendado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ProdutoEncomendadoLoader implements Iterator<EncomendaProdutoEntity>,Iterable<EncomendaProdutoEntity> {
  private Connection connection;
  private long idEncomenda;
  private ResultSet resultSet;
  private Erro erro = null;

  public ProdutoEncomendadoLoader(Connection connection,long idEncomenda) {
    this.connection = connection;
    this.idEncomenda = idEncomenda;
  }

  @Override
  public Iterator<EncomendaProdutoEntity> iterator() {
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
  public EncomendaProdutoEntity next() {
    try {
      EncomendaProdutoEntity encomendaProdutoEntity = new EncomendaProdutoEntity();
      ProdutoEntity produto = new ProdutoEntity();
      produto.setIdProduto(resultSet.getLong(1));
      produto.setPrecoAtual(resultSet.getBigDecimal(2));
      encomendaProdutoEntity.setProduto(produto);
      encomendaProdutoEntity.setQuantidade(resultSet.getInt(3));
      return encomendaProdutoEntity;
    } catch (SQLException e) {
      erro = Erro.TECNICO;
      return null;
    }
  }

  @Override
  public void remove() {

  }
}
