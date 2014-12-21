package pt.ipg.mcm.errors.services;

import pt.ipg.mcm.errors.ExceptionToTypeResponse;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqGetProduto;
import pt.ipg.mcm.xmodel.ResAddProduto;
import pt.ipg.mcm.xmodel.ResGetProduto;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class ProdutoService {

  @EJB
  private ProdutoDao produtoDao;

  @WebMethod
  public ResAddProduto addProduto(@WebParam(name = "req-add-produto") ReqAddProduto reqAddProduto) {
    try {
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
