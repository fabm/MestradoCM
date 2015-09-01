package pt.ipg.mcm.services;


import pt.ipg.mcm.controller.PadeiroDao;
import pt.ipg.mcm.controller.imp.UtilizadorDao;
import pt.ipg.mcm.entities.PadeiroEntity;
import pt.ipg.mcm.entities.UtilizadorPadeiroEntity;
import pt.ipg.mcm.entities.VUtilizadorClienteEntity;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.*;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

@WebService(serviceName = "Utilizador", portName = "UtilizadorPort")
public class UtilizadorService extends SecureService {

  @Inject
  private UtilizadorDao utilizadorDao;

  @Inject
  private PadeiroDao padeiroDao;


  @WebMethod
  public ResAddUtilizador addUtilizador(@WebParam(name = "req-add-utilizador") @XmlElement(required = true) ReqAddUtilizador reqAddUtilizador) throws
      LoginException {
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("idUtilizador", "Utilizador");
      Validacao.getInstance().valida(reqAddUtilizador, aliasMap);
      checkAuthorization(Role.ADMINISTRADOR);

      utilizadorDao.addUtilizador(reqAddUtilizador);

      ResAddUtilizador resAddUtilizador = new ResAddUtilizador();
      resAddUtilizador.setRetorno(new RetornoSoap(1, "Padeiro Adicionado Com Sucesso. "));

      return resAddUtilizador;

    } catch (MestradoException e) {
      ResAddUtilizador resAddUtilizador = new ResAddUtilizador();
      resAddUtilizador.setRetorno(new RetornoSoap(e));
      return resAddUtilizador;
    }

  }

  @WebMethod
  public ResGetPadeiro getUtilizadorPadeiro(@WebParam(name = "id") Long id) {
      return utilizadorDao.getPadeiro(id);
  }

  @WebMethod
  public ResCreationUserClient createUserCliente(@WebParam(name = "cliente") UserClienteCreationRequest userClienteCreationRequest) {
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("dataNascimento", "data de nascimento");
      Validacao.getInstance().valida(userClienteCreationRequest, aliasMap);
      utilizadorDao.createUserCliente(userClienteCreationRequest);
      return new ResCreationUserClient();
    } catch (MestradoException e) {
      return new ResCreationUserClient(e);
    }
  }

}
