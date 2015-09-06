package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.EncomendaDao;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.xmodel.ResMinhasEncomendas;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.AddAndUpdateEncomendasIn;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasIn;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasOut;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.ws.WebServiceContext;

@WebService(serviceName = "Encomenda", portName = "EncomendaPort")
public class EncomendaService extends SecureService {

    @EJB
    private EncomendaDao encomendaDao;

    @Resource
    private WebServiceContext webServiceContext;


    @WebMethod
    public ResMinhasEncomendas getMinhasEncomendasTodas() throws LoginException {
        return getMinhasEncomendas(0);
    }

    @WebMethod
    public ResMinhasEncomendas getMinhasEncomendas(@WebParam(name = "idSync") long id) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.CLIENTE);
        String login = getSecurityCommon().getUserPrincipal().getName();
        return new ResMinhasEncomendas(encomendaDao.getMinhasEncomendas(login, id));
    }

    @WebMethod
    public ResMinhasEncomendasDetalhe getMinhasEncomendasDetalhe(@WebParam(name = "idSync") long id) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.CLIENTE);
        String login = getSecurityCommon().getUserPrincipal().getName();
        return encomendaDao.getMinhasEncomendasSync(login, id);
    }

    @WebMethod
    public AddEncomendasOut addEncomendas(@WebParam(name = "addEncomendas") AddEncomendasIn addEncomendasIn) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.CLIENTE);
        String login = getSecurityCommon().getUserPrincipal().getName();
        return encomendaDao.addEncomendas(addEncomendasIn.getEncomendaInList(), login);
    }

    @WebMethod
    public AddEncomendasOut addEUpdateEncomendas(@WebParam(name = "addAndUpdateEncomendas") AddAndUpdateEncomendasIn addAndUpdateEncomendasIn) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.CLIENTE);
        String login = getSecurityCommon().getUserPrincipal().getName();
        return encomendaDao.addAndUpdateEncomendasIn(addAndUpdateEncomendasIn, login);
    }


}
