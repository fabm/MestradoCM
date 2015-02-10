package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.CategoriaDao;
import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.*;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.ResponseWrapper;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "Categoria", portName = "CategoriaPort")
public class CategoriaService {
  @EJB
  private CategoriaDao categoriaDao;


  @WebResult(name = "response")
  @WebMethod(operationName = "add-categoria")
  public ResAddCategoria addCategoria(@XmlElement(required = true) @WebParam(name = "request") ReqAddCategoria reqAddCategoria) {
    ResAddCategoria resAddCategoria = new ResAddCategoria();
    try {
      Map<String,String> aliasMap = new HashMap<String, String>();
      aliasMap.put("descricao","descrição");
      Validacao.getInstance().valida(reqAddCategoria,aliasMap);
      CategoriaEntity categoriaEntity = new CategoriaEntity();
      categoriaEntity.setDescricao(reqAddCategoria.getDescricao());
      categoriaEntity.setNome(reqAddCategoria.getNome());
      categoriaDao.addCategoria(categoriaEntity);
      resAddCategoria.setRetorno(new Retorno(1,"Categoria inserida com sucesso"));
    } catch (MestradoException e) {
      resAddCategoria.setRetorno(new Retorno(e));
    } catch (SQLException e) {
      resAddCategoria.setRetorno(new Retorno(new MestradoException(Erro.TECNICO)));
    }
    return resAddCategoria;
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "get-all-categorias")
  @ResponseWrapper(localName = "get-all-categorias-response")
  public ResGetAllCategorias getAllCategorias() {
    ResGetAllCategorias resGetAllCategorias = new ResGetAllCategorias();
    List<CategoriaEntity> allCategorias;
    try {
      allCategorias = categoriaDao.getAll();
      List<Categoria> resCategorias = resGetAllCategorias.getCategorias();

      for (CategoriaEntity categoriaEntity : allCategorias) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaEntity.getDescricao());
        categoria.setNome(categoriaEntity.getNome());
        categoria.setId(categoriaEntity.getIdCategoria());
        resCategorias.add(categoria);
      }
      return resGetAllCategorias;
    } catch (MestradoException e) {
      resGetAllCategorias.setRetorno(new Retorno(e));
      return resGetAllCategorias;
    }
  }

  public ResCategoriasDesync getCategoriasDeSync(@XmlElement(required = true) @WebParam(name = "versao") Long versao) {
    ResCategoriasDesync resCategoriasDesync = new ResCategoriasDesync();
    try {
      List<CategoriaEntity> categoriaEntities = categoriaDao.getDesync(versao);
      List<Categoria> categorias = resCategoriasDesync.getCategorias();
      for (CategoriaEntity categoriaEntity:categoriaEntities){
        Categoria categoria = new Categoria();
        categoria.setDescricao(categoriaEntity.getDescricao());
        categoria.setNome(categoriaEntity.getDescricao());
        categoria.setId(categoriaEntity.getIdCategoria());
        categorias.add(categoria);
      }
      return resCategoriasDesync;
    } catch (MestradoException e) {
      resCategoriasDesync.setRetorno(new Retorno(e));
    }
    return resCategoriasDesync;
  }

    @WebMethod
    public ResGetCategoria getCategoria (@WebParam(name = "req-get-categoria")@XmlElement(required = true) Integer reqGetCategoria) {

        try {
            CategoriaEntity categoriaEntity =  categoriaDao.getCategoria(reqGetCategoria);
            ResGetCategoria resGetcategoria = new ResGetCategoria();
            resGetcategoria.setNome(categoriaEntity.getNome());
            resGetcategoria.setDescricao(categoriaEntity.getDescricao());


            return resGetcategoria;

        } catch (MestradoException e) {
            ResGetCategoria resGetCategoria = new ResGetCategoria();
            resGetCategoria.setRetorno(new Retorno(e));
            return resGetCategoria;
        }


    }
}
