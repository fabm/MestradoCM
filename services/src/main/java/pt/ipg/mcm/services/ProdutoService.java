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
public class ProdutoService extends SecureService {

    @EJB
    private ProdutoDao produtoDao;

    @Resource
    private WebServiceContext webServiceContext;

    @WebMethod
    public ResAddProduto addProduto(@WebParam(name = "req-add-produto") @XmlElement(required = true) ReqAddProduto reqAddProduto) throws LoginException {
        try {
            setWsc(webServiceContext);
            checkAuthorization(Role.ADMINISTRADOR);

            Map<String, String> aliasMap = new HashMap<>();
            aliasMap.put("precoUnitario", "preço unitário");
            Validacao.getInstance().valida(reqAddProduto, aliasMap);
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
            return produtoDao.getProduto(reqGetProduto);
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
            resGetProdutosCategorias.setProdutoCategoriaList(produtoDao.getProdutos(reqGetProdutosCategorias));
        } catch (MestradoException e) {
            resGetProdutosCategorias = new ResGetProdutosCategorias();
            resGetProdutosCategorias.setRetorno(new RetornoSoap(e));
        }
        return resGetProdutosCategorias;
    }

    @WebMethod
    public ResGetProdutos getProdutosDeSync(@WebParam(name = "versao") Long versao) {

        ResGetProdutos resGetProdutos = new ResGetProdutos();

        List<ProdutoXml> produtos;
        try {
            produtos = produtoDao.getProdutos(versao);
        } catch (MestradoException e) {
            return new ResGetProdutos(new RetornoSoap(e));
        }
        long versaoMax = 0;

        for (ProdutoXml produtoXml : produtos) {
            versaoMax = Math.max(versaoMax, produtoXml.getSync());
        }

        resGetProdutos.setVersaoMax(versaoMax);
        resGetProdutos.setResGetProdutos(produtos);
        return resGetProdutos;
    }


    @WebMethod
    public ResUpdateProduto updateProduto(@WebParam(name = "req-update-produtp") @XmlElement(required = true) ReqUpdateProduto reqUpdateProduto) {

        ResUpdateProduto resUpdateProduto = new ResUpdateProduto();

        try {
            Map<String, String> aliasMap = new HashMap<>();
            aliasMap.put("descricao", "descrição");
            Validacao.getInstance().valida(reqUpdateProduto, aliasMap);
            produtoDao.updateProduto(reqUpdateProduto);
            resUpdateProduto.setRetorno(new RetornoSoap(1, "Produto atualizado com sucesso"));
        } catch (MestradoException e) {
            return new ResUpdateProduto(new RetornoSoap(e));
        }
        return resUpdateProduto;

    }
}
