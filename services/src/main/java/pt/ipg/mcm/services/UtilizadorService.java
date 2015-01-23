package pt.ipg.mcm.services;


import pt.ipg.mcm.controller.PadeiroDao;
import pt.ipg.mcm.controller.UtilizadorDao;
import pt.ipg.mcm.entities.PadeiroEntity;
import pt.ipg.mcm.entities.UtilizadorEntity;
import pt.ipg.mcm.entities.UtilizadorPadeiroEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BrrF on 20-01-2015.
 */
@WebService (serviceName = "Utilizador", portName = "UtilizadorPort")
public class UtilizadorService {

    @Resource
    private WebServiceContext webServiceContext;

    @EJB
    private UtilizadorDao utilizadorDao;

    @EJB
    private PadeiroDao padeiroDao;

    private RolesAuthorized rolesAuthorized;

    @PostConstruct
    private void init(){rolesAuthorized = new RolesAuthorized(webServiceContext);}


    @WebMethod
    public ResAddUtilizador addUtilizador (@WebParam(name ="req-add-utilizador") @XmlElement(required = true) ReqAddUtilizador reqAddUtilizador) throws LoginException{
        try {
            Map<String, String> aliasMap = new HashMap<String, String>();
            aliasMap.put("idUtilizador","Utilizador");
            Validacao.getInstance().valida(reqAddUtilizador, aliasMap);
            rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR);
            UtilizadorPadeiroEntity utilizadorPadeiroEntity = new UtilizadorPadeiroEntity();

            utilizadorPadeiroEntity.setLogin(reqAddUtilizador.getLogin());
            utilizadorPadeiroEntity.setPassword(reqAddUtilizador.getPassword());
            utilizadorPadeiroEntity.setNome(reqAddUtilizador.getName());

            utilizadorDao.addUtilizador(utilizadorPadeiroEntity);

            ResAddUtilizador resAddUtilizador = new ResAddUtilizador();
            resAddUtilizador.setRetorno(new Retorno(1,"Padeiro Adicionado Com Sucesso. "));

            return  resAddUtilizador;

        }catch (MestradoException e){
            ResAddUtilizador resAddUtilizador =  new ResAddUtilizador();
            resAddUtilizador.setRetorno(new Retorno(e));
            return resAddUtilizador;
        }

    }

    @WebMethod
    public ResGetUtilizador getUtilizadorPadeiro (@WebParam(name = "versao") Long versao) {
        ResGetUtilizador resGetUtilizador = new ResGetUtilizador();
        try {
            PadeiroEntity padeiro = utilizadorDao.getPadeiro(versao);

        } catch (MestradoException e) {
            resGetUtilizador.setRetorno(new Retorno(e));
        }
        return resGetUtilizador;
    }



}
