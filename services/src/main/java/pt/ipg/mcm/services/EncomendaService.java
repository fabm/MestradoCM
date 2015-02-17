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
import pt.ipg.mcm.xmodel.Encomenda;
import pt.ipg.mcm.xmodel.MinhaEncomenda;
import pt.ipg.mcm.xmodel.ProdutoEncomendado;
import pt.ipg.mcm.xmodel.ReqAddEncomenda;
import pt.ipg.mcm.xmodel.ResAddEncomenda;
import pt.ipg.mcm.xmodel.ResMinhasEncomendas;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.Retorno;

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

    for (ProdutoEncomendado produtoEncomendado : reqAddEncomenda.getProdutosEncomendados()) {
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
      return new ResAddEncomenda(encomendaEntity.getIdEncomenda(), new Retorno(1, "Encomenda inserida com sucesso"));
    } catch (MestradoException e) {
      return new ResAddEncomenda(new Retorno(e));
    }
  }

  @WebMethod
  public ResMinhasEncomendas getMinhasEncomendasTodas() throws LoginException {
    return getMinhasEncomendas(0);
  }

  @WebMethod
  public ResMinhasEncomendas getMinhasEncomendas(@WebParam(name = "idSync")long id) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);
    String login = getSecurityCommon().getUserPrincipal().getName();
    ResMinhasEncomendas resMinhasEncomendas = new ResMinhasEncomendas();

    List<MinhaEncomenda> minhasEncomendas = new ArrayList<MinhaEncomenda>();
    resMinhasEncomendas.setMinhasEncomendasList(minhasEncomendas);
    try {
      for (VEncomendasLoginEntity vEncomendasLoginEntity : encomendaDao.getMinhasEncomendas(login, id)) {
        MinhaEncomenda minhaEncomenda = new MinhaEncomenda();
        minhaEncomenda.setDataPrevista(vEncomendasLoginEntity.getDataEntrega());
        minhaEncomenda.setId(vEncomendasLoginEntity.getIdEncomenda());
        minhaEncomenda.setPreco(vEncomendasLoginEntity.getPrecoAtual());
        minhasEncomendas.add(minhaEncomenda);
      }
      resMinhasEncomendas.getMinhasEncomendasList();
      return resMinhasEncomendas;
    } catch (MestradoException e) {
      return new ResMinhasEncomendas(e);
    }

  }

  @WebMethod
  public ResMinhasEncomendasDetalhe getMinhasEncomendasDetalhe(@WebParam(name = "idSync")long id) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.CLIENTE);
    String login = getSecurityCommon().getUserPrincipal().getName();
    ResMinhasEncomendasDetalhe resMinhasEncomendas = new ResMinhasEncomendasDetalhe();

    List<Encomenda> minhasEncomendas = new ArrayList<Encomenda>();
    try {
      for (EncomendaEntity encomendaEntity : encomendaDao.getMinhasEncomendasSync(login, id)) {
        Encomenda encomenda = new Encomenda();
        encomenda.setObservacoes(encomendaEntity.getObservacoes());
        encomenda.setDataCriacao(encomendaEntity.getDataCriacao());
        encomenda.setDataPrevista(encomendaEntity.getDataEntrega());
        if (encomendaEntity.getEstado() != null) {
          encomenda.setEstado(encomendaEntity.getEstado().getNumEstado());
        }
        encomenda.setId(encomendaEntity.getIdEncomenda());
        List<ProdutoEncomendado> produtoEncomendadoList = new ArrayList<ProdutoEncomendado>();
        for (EncomendaProdutoEntity encomendaProdutoEntity : encomendaEntity.getEncomendaProdutoEntityList()) {
          ProdutoEncomendado produtoEncomendado = new ProdutoEncomendado();
          produtoEncomendado.setIdProduto(encomendaProdutoEntity.getProduto().getIdProduto());
          produtoEncomendado.setQuantidade(encomendaProdutoEntity.getQuantidade());
          produtoEncomendadoList.add(produtoEncomendado);
        }
        encomenda.setProdutosEncomendados(produtoEncomendadoList);
        minhasEncomendas.add(encomenda);

      }
      resMinhasEncomendas.setListaEncomendas(minhasEncomendas);
      return resMinhasEncomendas;
    } catch (MestradoException e) {
      return new ResMinhasEncomendasDetalhe(e);
    }

  }


}
