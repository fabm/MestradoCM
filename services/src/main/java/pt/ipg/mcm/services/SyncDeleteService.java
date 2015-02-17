package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.SyncDeleteDao;
import pt.ipg.mcm.entities.SyncDeleteEntity;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.RegistoAApagar;
import pt.ipg.mcm.xmodel.RegistosAApagar;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "SyncDelete", portName = "SyncDeletePort")
public class SyncDeleteService {

  @EJB
  private SyncDeleteDao syncDeleteDao;

  @WebMethod
  public RegistosAApagar getRegistosAApagar(@WebParam(name = "versao")long versao) {
    try {
      List<SyncDeleteEntity> rowsToDelete = syncDeleteDao.getRowsToDelete(versao);
      List<RegistoAApagar> registosAApagar = new ArrayList<RegistoAApagar>();
      for (SyncDeleteEntity syncDeleteEntity : rowsToDelete) {
        RegistoAApagar registo = new RegistoAApagar();
        registo.setId(syncDeleteEntity.getIdSync());
        registo.setTabela(syncDeleteEntity.getTabela());
        registosAApagar.add(registo);
      }
      return new RegistosAApagar(registosAApagar);
    } catch (MestradoException e) {
      return new RegistosAApagar(e);
    }
  }
}
