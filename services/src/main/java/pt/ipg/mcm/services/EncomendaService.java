package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.EncomendaDao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(serviceName = "Encomenda", portName = "Encomenda")
public class EncomendaService {
  @Resource
  private WebService webService;

  @EJB
  private EncomendaDao encomendaDao;


}
