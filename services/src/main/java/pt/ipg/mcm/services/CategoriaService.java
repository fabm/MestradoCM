package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.CategoriaDao;
import pt.ipg.mcm.xmodel.ReqAddCategoria;
import pt.ipg.mcm.xmodel.ResAddCategoria;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

@WebService(serviceName = "categoria", portName = "categoriaPort")
public class CategoriaService {
  @EJB
  private CategoriaDao categoriaDao;


  @WebResult(name = "response")
  @WebMethod(operationName = "add-categoria")
  @ResponseWrapper(localName = "add")
  public ResAddCategoria addCategoria(@WebParam(name = "request") ReqAddCategoria reqAddCategoria) {
    return categoriaDao.addCategoria(reqAddCategoria);
  }

}
