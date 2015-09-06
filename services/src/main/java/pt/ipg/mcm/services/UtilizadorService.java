package pt.ipg.mcm.services;


import pt.ipg.mcm.controller.ClienteDao;
import pt.ipg.mcm.controller.PadeiroDao;
import pt.ipg.mcm.controller.UtilizadorDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import java.util.HashMap;
import java.util.Map;

@WebService(serviceName = "Utilizador", portName = "UtilizadorPort")
public class UtilizadorService extends SecureService {

    @Inject
    private UtilizadorDao utilizadorDao;

    @Inject
    private PadeiroDao padeiroDao;

    @Inject
    private ClienteDao clienteDao;

    @Resource
    private WebServiceContext webServiceContext;

    @WebMethod
    public ResAddPadeiro addPadeiro(@WebParam(name = "reqAddPadeiro")
                                    @XmlElement(required = true
                                    ) ReqAddUtilizador reqAddUtilizador) throws LoginException {
        setWsc(webServiceContext);
        try {
            Map<String, String> aliasMap = new HashMap<String, String>();
            aliasMap.put("idUtilizador", "utilizador");
            Validacao.getInstance().valida(reqAddUtilizador, aliasMap);
            checkAuthorization(Role.ADMINISTRADOR);

            return utilizadorDao.addPadeiro(reqAddUtilizador);
        } catch (MestradoException e) {
            return new ResAddPadeiro(e);
        }

    }

    @WebMethod
    public ResGetPadeiro getUtilizadorPadeiro(@WebParam(name = "id") Long id) {
        return utilizadorDao.getPadeiro(id);
    }

    @WebMethod
    public ResGetCliente getCliente(@WebParam(name = "id") long id) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
        return clienteDao.getCliente(id);
    }

    @WebMethod
    public RetornoSoap deleteCliente(@WebParam(name = "id") long id) {
        setWsc(webServiceContext);
        clienteDao.deleteCliente(id);
        return new RetornoSoap(1, "Cliente removido com sucesso");
    }


}
