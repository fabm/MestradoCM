package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ClienteDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.RetornoSoap;
import pt.ipg.mcm.xmodel.UserClienteCreationRequest;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import java.util.HashMap;

@WebService(serviceName = "Cliente", portName = "ClientePort")
public class ClienteService extends SecureService {

    @EJB
    private ClienteDao clienteDao;

    @Resource
    private WebServiceContext webServiceContext;

    @WebMethod
    public ResGetCliente getCliente(@WebParam(name = "id") long id) throws LoginException {
        setWsc(webServiceContext);
        checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
        return clienteDao.getCliente(id);
    }


    @WebMethod
    public RetornoSoap addClienteUtilizador(
            @WebParam(name = "request")
            @XmlElement(required = true)
            UserClienteCreationRequest userClienteCreationRequest) throws LoginException {

        setWsc(webServiceContext);
        checkAuthorization(Role.ADMINISTRADOR, Role.CONVIDADO);

        try {
            Validacao.getInstance().valida(userClienteCreationRequest, new HashMap<String, String>() {{
                put("dataNascimento", "date de nascimento");
            }});

            clienteDao.createUserCliente(userClienteCreationRequest);
            return new RetornoSoap(1, "Registo efectuado com sucesso");
        } catch (MestradoException e) {
            return new RetornoSoap(e);
        }
    }

    @WebMethod
    public RetornoSoap deleteCliente(@WebParam(name = "id") long id) {
        clienteDao.deleteCliente(id);
        return new RetornoSoap(1, "Cliente removido com sucesso");
    }


}
