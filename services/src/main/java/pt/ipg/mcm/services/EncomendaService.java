package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.EncomendaDao;
import pt.ipg.mcm.xmodel.Retorno;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(serviceName = "Encomenda", portName = "EncomendaPort")
public class EncomendaService {
  @Resource
  private WebServiceContext webServiceContext;

  @EJB
  private EncomendaDao encomendaDao;

  @WebMethod
  public Retorno addEncomenda(){
    return new Retorno(1,"teste");
  }

}
