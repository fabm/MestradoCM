package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.EncomendaDao;
import pt.ipg.mcm.entities.CalendarioEntity;
import pt.ipg.mcm.entities.EncomendaEntity;
import pt.ipg.mcm.entities.EncomendaProdutoEntity;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.entities.VEncomendasLoginEntity;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.xmodel.EncomendaXmlSemPreco;
import pt.ipg.mcm.xmodel.MinhaEncomenda;
import pt.ipg.mcm.xmodel.ProdutoEncomendado;
import pt.ipg.mcm.xmodel.ReqAddEncomenda;
import pt.ipg.mcm.xmodel.ReqAddEncomendas;
import pt.ipg.mcm.xmodel.ResAddEncomenda;
import pt.ipg.mcm.xmodel.ResAddEncomendas;
import pt.ipg.mcm.xmodel.ResMinhasEncomendas;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.RetornoSoap;
import pt.ipg.mcm.xmodel.encomendas.XInEncomendas;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomendas;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.ws.WebServiceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "Encomenda", portName = "EncomendaPort")
public class EncomendaService extends SecureService {

  @EJB
  private EncomendaDao encomendaDao;

  @Resource
  private WebServiceContext webServiceContext;

  @WebMethod
  public ResAddEncomenda addEncomenda(@WebParam(name = "addEncomenda") ReqAddEncomenda reqAddEncomenda) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);

    String login = getSecurityCommon().getUserPrincipal().getName();

    EncomendaEntity encomendaEntity = new EncomendaEntity();

    CalendarioEntity calendarioEncomeda = new CalendarioEntity();

    calendarioEncomeda.setDataprevista(new Timestamp(reqAddEncomenda.getDataEntrega().getTime()));

    encomendaEntity.setCalendarioEntity(calendarioEncomeda);

    for (ProdutoEncomendado produtoEncomendado : reqAddEncomenda.getProdutoList()) {
      EncomendaProdutoEntity encomendaProdutoEntity = new EncomendaProdutoEntity();
      encomendaProdutoEntity.setEncomenda(encomendaEntity);

      ProdutoEntity produtoEntity = new ProdutoEntity();
      produtoEntity.setIdProduto(produtoEncomendado.getIdProduto());
      encomendaProdutoEntity.setProduto(produtoEntity);

      encomendaProdutoEntity.setQuantidade(produtoEncomendado.getQuantidade());
      encomendaEntity.getEncomendaProdutoEntityList().add(encomendaProdutoEntity);
    }

    try {
      encomendaDao.inserirEncomenda(encomendaEntity, login);
      return new ResAddEncomenda(encomendaEntity.getIdEncomenda(), new RetornoSoap(1, "Encomenda inserida com sucesso"));
    } catch (MestradoException e) {
      return new ResAddEncomenda(new RetornoSoap(e));
    }
  }

  @WebMethod
  public ResAddEncomendas addEncomendas(@WebParam(name = "addEncomendas") ReqAddEncomendas reqAddEncomendas) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);

    String login = getSecurityCommon().getUserPrincipal().getName();

    List<EncomendaEntity> encomendaEntityList = new ArrayList<EncomendaEntity>();

    for (EncomendaXmlSemPreco encomendaXml : reqAddEncomendas.getEncomendas()) {
      EncomendaEntity encomendaEntity = new EncomendaEntity();

      CalendarioEntity calendarioEncomeda = new CalendarioEntity();

      calendarioEncomeda.setDataprevista(new Timestamp(encomendaXml.getDataEntrega().getTime()));

      encomendaEntity.setCalendarioEntity(calendarioEncomeda);

      for (ProdutoEncomendado produtoEncomendado : encomendaXml.getProdutoList()) {
        EncomendaProdutoEntity encomendaProdutoEntity = new EncomendaProdutoEntity();
        encomendaProdutoEntity.setEncomenda(encomendaEntity);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setIdProduto(produtoEncomendado.getIdProduto());
        encomendaProdutoEntity.setProduto(produtoEntity);

        encomendaProdutoEntity.setQuantidade(produtoEncomendado.getQuantidade());
        encomendaEntity.getEncomendaProdutoEntityList().add(encomendaProdutoEntity);
      }

      encomendaEntityList.add(encomendaEntity);
    }
    try {
      List<Long> ids = encomendaDao.inserirEncomendas(encomendaEntityList, login);
      return new ResAddEncomendas(ids);
    } catch (MestradoException e) {
      return new ResAddEncomendas(e);
    }
  }

  @WebMethod
  public ResMinhasEncomendas getMinhasEncomendasTodas() throws LoginException {
    return getMinhasEncomendas(0);
  }

  @WebMethod
  public ResMinhasEncomendas getMinhasEncomendas(@WebParam(name = "idSync") long id) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);
    String login = getSecurityCommon().getUserPrincipal().getName();
    try {
      return new ResMinhasEncomendas(encomendaDao.getMinhasEncomendas(login,id));
    } catch (MestradoException e) {
      return new ResMinhasEncomendas(e);
    }

  }

  @WebMethod
  public ResMinhasEncomendasDetalhe getMinhasEncomendasDetalhe(@WebParam(name = "idSync") long id) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);
    String login = getSecurityCommon().getUserPrincipal().getName();

    try {
      return encomendaDao.getMinhasEncomendasSync(login, id);
    } catch (MestradoException e) {
      return new ResMinhasEncomendasDetalhe(e);
    }

  }

  @WebMethod
  public XOutEncomendas postEncomendas(XInEncomendas xInEncomendas) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);
    String login = getSecurityCommon().getUserPrincipal().getName();
    try {
      return encomendaDao.postEncomendas(xInEncomendas.getEncomendaSoapIns(), login);
    } catch (MestradoException e) {
      return new XOutEncomendas(e);
    }
  }

}
