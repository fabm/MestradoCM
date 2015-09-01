package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.imp.CategoriaDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "Categoria", portName = "CategoriaPort")
public class CategoriaService {
    @EJB
    private CategoriaDao categoriaDao;


    @WebResult(name = "response")
    @WebMethod(operationName = "add-categoria")
    public ResAddCategoria addCategoria(@XmlElement(required = true) @WebParam(name = "request") Categoria categoria) {
        ResAddCategoria resAddCategoria = new ResAddCategoria();
        try {
            Map<String, String> aliasMap = new HashMap<String, String>();
            aliasMap.put("descricao", "descrição");
            Validacao.getInstance().valida(categoria, aliasMap);
            resAddCategoria.setId(categoriaDao.addCategoria(categoria));
            resAddCategoria.setRetorno(new RetornoSoap(1, "Categoria inserida com sucesso"));
        } catch (MestradoException e) {
            resAddCategoria.setRetorno(new RetornoSoap(e));
        }
        return resAddCategoria;
    }

    @WebResult(name = "response")
    @WebMethod(operationName = "get-all-categorias")
    @ResponseWrapper(localName = "get-all-categorias-response")
    public ResGetAllCategorias getAllCategorias() {
        ResGetAllCategorias resGetAllCategorias = new ResGetAllCategorias();
        try {
            resGetAllCategorias.setCategorias(categoriaDao.getAll());
            return resGetAllCategorias;
        } catch (MestradoException e) {
            resGetAllCategorias.setRetorno(new RetornoSoap(e));
            return resGetAllCategorias;
        }
    }

    public ResCategoriasDesync getCategoriasDeSync(@XmlElement(required = true) @WebParam(name = "versao") Long versao) {
        List<Categoria> categorias = categoriaDao.getDesync(versao);
        long maxVersao = 0;
        for (Categoria categoria : categorias) {
            maxVersao = Math.max(maxVersao, categoria.getVersao());
        }
        ResCategoriasDesync resCategoriasDesync = new ResCategoriasDesync(categorias);
        resCategoriasDesync.setMaxVersao(maxVersao);
        return resCategoriasDesync;
    }

    @WebMethod
    public ResGetCategoria getCategoria(@WebParam(name = "id") @XmlElement(required = true) Integer id) {
        try {
            return categoriaDao.getCategoria(id);
        } catch (MestradoException e) {
            return new ResGetCategoria(e);
        }
    }

//  @WebMethod
//  public ResGetCategoria getCategoria(@WebParam(name = "id") @XmlElement(required = true) Integer id) {
//    try {
//      return categoriaDao.getCategoria(id);
//    } catch (MestradoException e) {
//      ResGetCategoria resGetCategoria = new ResGetCategoria();
//      resGetCategoria.setRetorno(new RetornoSoap(e));
//      return resGetCategoria;
//    }
//  }

    @WebMethod
    public ResUpdateCategoria updateCategoria(@WebParam(name = "req-update-categoria") @XmlElement(required = true) ReqUpdateCategoria reqUpdateCategoria) {

        ResUpdateCategoria resUpdateCategoria = new ResUpdateCategoria();

        try {
            Map<String, String> aliasMap = new HashMap<>();
            aliasMap.put("descricao", "descrição");
            Validacao.getInstance().valida(reqUpdateCategoria, aliasMap);
            categoriaDao.updateCategoria(reqUpdateCategoria);
            resUpdateCategoria.setRetorno(new RetornoSoap(1, "Categoria atualizada com sucesso"));
        } catch (MestradoException e) {
            resUpdateCategoria.setRetorno(new RetornoSoap(e));
        }

        return resUpdateCategoria;

    }

    @WebMethod
    public RetornoSoap deleteCategoria(@WebParam(name = "id")long id){
        return categoriaDao.deleteCategoria(id);
    }

}
