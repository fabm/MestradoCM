package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.entities.VProdutoCategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqGetProdutosCategorias;
import pt.ipg.mcm.xmodel.ResAddProduto;
import pt.ipg.mcm.xmodel.ResGetProduto;
import pt.ipg.mcm.xmodel.ResGetProdutos;
import pt.ipg.mcm.xmodel.ResGetProdutosCategorias;
import pt.ipg.mcm.xmodel.Retorno;

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

@WebService(serviceName = "Produto", portName = "ProdutoPort")
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
  public ResAddProduto addProduto(@WebParam(name = "req-add-produto") @XmlElement(required = true) ReqAddProduto reqAddProduto) throws LoginException {
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("precoUnitario", "preço unitário");
      Validacao.getInstance().valida(reqAddProduto, aliasMap);
      rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR);
      return produtoDao.addProduto(reqAddProduto);
    } catch (MestradoException e) {
      ResAddProduto resAddProduto = new ResAddProduto();
      resAddProduto.setRetorno(new Retorno(e));
      return resAddProduto;
    }
  }

  @WebMethod
  public ResGetProdutosCategorias getProdutosCategorias(@WebParam(name = "req-get-produtos-categorias") @XmlElement(required = true) ReqGetProdutosCategorias
                                                            reqGetProdutosCategorias) {

    ResGetProdutosCategorias resGetProdutosCategorias = new ResGetProdutosCategorias();
    try {
      List<ProdutoCategoria> produtosCategorias = resGetProdutosCategorias.getProdutoCategoriaList();
      List<VProdutoCategoriaEntity> allProdutos = produtoDao.getProdutos(reqGetProdutosCategorias.isComFoto(), reqGetProdutosCategorias.getIdCategoria());
      for (VProdutoCategoriaEntity vProdutoCategoriaEntity : allProdutos) {
        ProdutoCategoria produtoCategoria = new ProdutoCategoria();

        produtoCategoria.setIdproduto(vProdutoCategoriaEntity.getIdProduto());

        produtoCategoria.setNomeCategoria(vProdutoCategoriaEntity.getNomeCategoria());
        produtoCategoria.setNomeProduto(vProdutoCategoriaEntity.getNomeProduto());
        produtoCategoria.setPrecoActual(vProdutoCategoriaEntity.getPrecoAtual());
        produtoCategoria.setFoto(vProdutoCategoriaEntity.getFoto());
        produtoCategoria.setNomeProduto(vProdutoCategoriaEntity.getNomeProduto());
        produtosCategorias.add(produtoCategoria);
      }
    } catch (MestradoException e) {
      resGetProdutosCategorias = new ResGetProdutosCategorias();
      resGetProdutosCategorias.setRetorno(new Retorno(e));
    }
    return resGetProdutosCategorias;
  }

 @WebMethod
  public ResGetProdutos getProdutosDeSync(@WebParam(name = "versao") Long versao) {

    ResGetProdutos resGetProdutos = new ResGetProdutos();

    try {
      List<ProdutoEntity> produtos = produtoDao.getProdutos(versao);
      List<ResGetProduto> resGetProdutoList = new ArrayList<ResGetProduto>();

      for (ProdutoEntity produtoEntity : produtos) {
        ResGetProduto resGetProduto = new ResGetProduto();
        resGetProduto.setNome(produtoEntity.getNome());
        resGetProduto.setCategoria(produtoEntity.getIdCategoria());
        resGetProduto.setFoto(produtoEntity.getFoto());
      }
      resGetProdutos.setResGetProdutos(resGetProdutoList);
    } catch (SQLException e) {
      resGetProdutos.setRetorno(new Retorno(new MestradoException(Erro.TECNICO)));
    }
    return resGetProdutos;
  }
}
