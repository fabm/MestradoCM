package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.entities.VProdutoCategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.*;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "Produto", portName = "ProdutoPort")
public class ProdutoService extends SecureService {

  @EJB
  private ProdutoDao produtoDao;

  @WebMethod
  public ResAddProduto addProduto(@WebParam(name = "req-add-produto") @XmlElement(required = true) ReqAddProduto reqAddProduto) throws LoginException {
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("precoUnitario", "preço unitário");
      Validacao.getInstance().valida(reqAddProduto, aliasMap);
      checkAuthorization(Role.ADMINISTRADOR);
      return produtoDao.addProduto(reqAddProduto);
    } catch (MestradoException e) {
      ResAddProduto resAddProduto = new ResAddProduto();
      resAddProduto.setRetorno(new RetornoSoap(e));
      return resAddProduto;
    }
  }

  @WebMethod
  public ResGetProduto getProduto(@WebParam(name = "req-get-produto") @XmlElement(required = true) Integer reqGetProduto) {
    try {

      ProdutoEntity produtoEntity = produtoDao.getProduto(reqGetProduto);
      ResGetProduto resGetProduto = new ResGetProduto();
      resGetProduto.setNome(produtoEntity.getNome());
      resGetProduto.setPrecoUnitario(produtoEntity.getPrecoAtual());

      return resGetProduto;

    } catch (MestradoException e) {
      ResGetProduto resGetProduto = new ResGetProduto();
      resGetProduto.setRetorno(new RetornoSoap(e));
      return resGetProduto;
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
      resGetProdutosCategorias.setRetorno(new RetornoSoap(e));
    }
    return resGetProdutosCategorias;
  }

  @WebMethod
  public ResGetProdutos getProdutosDeSync(@WebParam(name = "versao") Long versao) {

    ResGetProdutos resGetProdutos = new ResGetProdutos();

    List<ProdutoEntity> produtos;
    try {
      produtos = produtoDao.getProdutos(versao);
    } catch (MestradoException e) {
      return new ResGetProdutos(new RetornoSoap(e));
    }
    List<ProdutoXml> produtoXmlList = new ArrayList<ProdutoXml>();

    long versaoMax = 0;

    for (ProdutoEntity produtoEntity : produtos) {
      ProdutoXml produtoXml = new ResGetProduto();
      produtoXml.setNome(produtoEntity.getNome());
      produtoXml.setCategoria(produtoEntity.getIdCategoria());
      produtoXml.setFoto(produtoEntity.getFoto());
      produtoXml.setId(produtoEntity.getIdProduto());
      versaoMax=Math.max(versaoMax, produtoEntity.getSync());
      produtoXml.setPrecoUnitario(produtoEntity.getPrecoAtual());
      produtoXmlList.add(produtoXml);
    }
    resGetProdutos.setVersaoMax(versaoMax);
    resGetProdutos.setResGetProdutos(produtoXmlList);
    return resGetProdutos;
  }


/*  @WebMethod
  public ResUpdateProduto updateProduto (@WebParam(name = "req-update-produtp") @XmlElement(required = true) ResUpdateProduto reqUpdateProduto){

    ResUpdateProduto resUpdateProduto = new ResUpdateProduto();

    /*try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("descricao", "descrição");
      Validacao.getInstance().valida(reqUpdateProduto, aliasMap);
      ProdutoEntity produtoEntity = new ProdutoEntity();
      produtoEntity.setIdCategoria(reqUpdateProduto.getIdCategoria());
      produtoEntity.setDescricao(reqUpdateCategoria.getDescricao());
      produtoEntity.setNome(reqUpdateCategoria.getNome());
      categoriaDao.updateCategoria(produtoEntity);
      resUpdateProduto.setRetorno(new RetornoSoap(1, "Categoria atualizada com sucesso"));
    } catch (MestradoException e) {
      resUpdateProduto.setRetorno(new RetornoSoap(e));
    } catch (SQLException e) {
      resUpdateProduto.setRetorno(new RetornoSoap(new MestradoException(Erro.TECNICO)));
    }


    return resUpdateProduto;

  }*/
}
