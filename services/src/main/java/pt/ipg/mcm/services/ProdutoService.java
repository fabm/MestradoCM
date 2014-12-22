package pt.ipg.mcm.services;

import pt.ipg.mcm.errors.ExceptionToTypeResponse;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqGetProduto;
import pt.ipg.mcm.xmodel.ResAddProduto;
import pt.ipg.mcm.xmodel.ResGetProduto;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.ws.WebServiceContext;

@WebService
public class ProdutoService {

  @Resource
  private WebServiceContext webServiceContext;

  @EJB
  private ProdutoDao produtoDao;

  private RolesAuthorized rolesAuthorized;

  @PostConstruct
  private void init() {
    rolesAuthorized = new RolesAuthorized(webServiceContext);
  }


  @WebMethod
  public ResAddProduto addProduto(@WebParam(name = "req-add-produto") ReqAddProduto reqAddProduto) throws LoginException {
    try {
      rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR);
      return produtoDao.addProduto(reqAddProduto);
    } catch (MestradoException e) {
      ResAddProduto resAddProduto = new ResAddProduto();
      resAddProduto.setTypeResponse(new ExceptionToTypeResponse(e).getTypeResponse());
      return resAddProduto;
    }
  }

  @WebMethod
  public ResGetProduto getProduto(@WebParam(name = "req-get-produto") ReqGetProduto reqGetProduto) {
    try {
      return produtoDao.getProduto(reqGetProduto);
    } catch (MestradoException e) {
      ResGetProduto resGetProduto = new ResGetProduto();
      resGetProduto.setTypeResponse(new ExceptionToTypeResponse(e).getTypeResponse());
      return resGetProduto;
    }
  }


}
