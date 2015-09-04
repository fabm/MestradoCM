package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.LocalidadeDao;
import pt.ipg.mcm.xmodel.ResGetAllLocalidades;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

@WebService(serviceName = "Localidade", portName = "LocalidadePort")
public class LocalidadeService {


  @EJB
  private LocalidadeDao localidadeDao;


  @WebResult(name = "response")
  @WebMethod(operationName = "get-all-localidades")
  @ResponseWrapper(localName = "get-all-localidades-response")
  public ResGetAllLocalidades getAllLocalidades() {
    return localidadeDao.getAll();
  }

  @WebMethod
  public ResGetAllLocalidades getLocalidadesComFiltroEPagina(
      @WebParam(name = "filtro") String filter,
      @WebParam(name = "pagina") int page) {
      return localidadeDao.getFiltered(page, "%" + filter + "%");
  }

  @WebMethod
  public ResGetAllLocalidades getLocalidadesComPagina(@WebParam(name = "pagina") int page) {
    return localidadeDao.getLocalidadesPaginada(page);
  }

}
