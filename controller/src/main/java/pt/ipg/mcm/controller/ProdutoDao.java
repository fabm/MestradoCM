package pt.ipg.mcm.controller;

import pt.ipg.mcm.errors.GlobalErrors;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqGetProduto;
import pt.ipg.mcm.xmodel.ResAddProduto;
import pt.ipg.mcm.xmodel.ResGetProduto;
import pt.ipg.mcm.xmodel.TypeEnumResponse;
import pt.ipg.mcm.xmodel.TypeResponse;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

@Stateless
public class ProdutoDao {
  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  public ResAddProduto addProduto(ReqAddProduto reqAddProduto) throws MestradoException {
    ResAddProduto resAddProduto = new ResAddProduto();

    TypeResponse responseOK;
    try {
      Connection connection = mestradoDataSource.getConnection();
      CallableStatement call = connection.prepareCall("INSERT INTO PRODUTO (ID_PRODUTO,NOME,PRECO_ATUAL,CATEGORIAS_ID_CATEGORIA,FOTO)VALUES(SEQ_PRODUTO.nextval,?,?,?,?)");

      call.setString(1, reqAddProduto.getNome());
      call.setBigDecimal(2, reqAddProduto.getPrecoUnitario());
      call.setLong(3, reqAddProduto.getCategoria());
      InputStream in = new FileInputStream("/Users/francisco/Pictures/bebe3.jpg");
      //InputStream in = new ByteArrayInputStream(reqAddProduto.getFoto());


      call.setBinaryStream(4, in);


      responseOK = new TypeResponse();
      responseOK.setTipoResposta(TypeEnumResponse.OK);
      responseOK.setMensagem("Produto inserido com sucesso");

      //call.registerOutParameter(5, Types.NUMERIC);

      call.executeUpdate();

      //resAddProduto.setId(call.getLong(5));
    } catch (SQLException e) {
      throw new MestradoException(GlobalErrors.SQL_EXCEPTION);
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("");
    }

    resAddProduto.setTypeResponse(responseOK);

    return resAddProduto;
  }

  public ResGetProduto getProduto(ReqGetProduto reqGetProduto) throws MestradoException {
    ResGetProduto resGetProduto = new ResGetProduto();

    try {
      String sqlString = "SELECT PRODUTO.NOME,\n" +
          "  PRODUTO.PRECO_ATUAL,\n" +
          "  PRODUTO.CATEGORIAS_ID_CATEGORIA,\n" +
          "  PRODUTO.FOTO\n" +
          "FROM PRODUTO\n" +
          "WHERE PRODUTO.ID_PRODUTO = 1";

      Connection connection = mestradoDataSource.getConnection();

      Statement call = connection.createStatement();
      ResultSet rs = call.executeQuery(sqlString);

      if (!rs.next()) {
        throw new MestradoException("produto %d não encontrado", reqGetProduto.getId());
      }

      resGetProduto.setNome(rs.getString(1));
      resGetProduto.setPrecoUnitario(rs.getBigDecimal(2));
      resGetProduto.setCategoria(rs.getLong(3));
      resGetProduto.setFoto(rs.getBytes(4));

    } catch (SQLException e) {
      throw new MestradoException(GlobalErrors.SQL_EXCEPTION);
    }

    return resGetProduto;
  }

}
