package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.CategoriaDao;
import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ReqAddCategoria;
import pt.ipg.mcm.xmodel.ResAddCategoria;
import pt.ipg.mcm.xmodel.ResGetAllCategorias;
import pt.ipg.mcm.xmodel.Retorno;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.ResponseWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "categoria", portName = "categoriaPort")
public class CategoriaService {
  @EJB
  private CategoriaDao categoriaDao;


  @WebResult(name = "response")
  @WebMethod(operationName = "add-categoria")
  public ResAddCategoria addCategoria(@XmlElement(required = true) @WebParam(name = "request") ReqAddCategoria reqAddCategoria) {
    try {
      Map<String,String> aliasMap = new HashMap<String, String>();
      aliasMap.put("descricao","descrição");
      Validacao.getInstance().valida(reqAddCategoria,aliasMap);
      return categoriaDao.addCategoria(reqAddCategoria);
    } catch (MestradoException e) {
      ResAddCategoria resAddCategoria = new ResAddCategoria();
      resAddCategoria.setRetorno(new Retorno(e));
      return resAddCategoria;
    }
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
        resCategorias.add(categoria);
      }
      return resGetAllCategorias;
    } catch (MestradoException e) {
      resGetAllCategorias.setRetorno(new Retorno(e));
      return resGetAllCategorias;
    }
  }

}
