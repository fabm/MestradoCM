package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.SyncDeleteDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.RegistosAApagar;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SyncDelete", portName = "SyncDeletePort")
public class SyncDeleteService {

    @EJB
    private SyncDeleteDao syncDeleteDao;

    @WebMethod
    public RegistosAApagar getRegistosAApagar(@WebParam(name = "versao") long versao) {
        try {
            return syncDeleteDao.getRowsToDelete(versao);
        } catch (MestradoException e) {
            return new RegistosAApagar(e);
        }
    }
}
