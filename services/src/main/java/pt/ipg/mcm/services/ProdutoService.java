package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ProdutoDao;
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
    public ResGetProdutosCategorias getProdutosCategorias(@WebParam(name = "categoria") long categoria) {
        ResGetProdutosCategorias resGetProdutosCategorias = new ResGetProdutosCategorias();
        resGetProdutosCategorias.setProdutoCategoriaList(produtoDao.getProdutosCategoria(categoria));
        return resGetProdutosCategorias;
    }

    @WebMethod
    public ResGetProdutos getProdutosDeSync(@WebParam(name = "versao") Long versao) {

        ResGetProdutos resGetProdutos = new ResGetProdutos();

        List<ProdutoXml> produtos;
        produtos = produtoDao.getProdutos(versao);
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

        try {
            Map<String, String> aliasMap = new HashMap<>();
            aliasMap.put("descricao", "descrição");
            Validacao.getInstance().valida(reqUpdateProduto, aliasMap);
            produtoDao.updateProduto(reqUpdateProduto);
            return new ResUpdateProduto();
        } catch (MestradoException e) {
            return new ResUpdateProduto(e);
        }
    }

    @WebMethod
    public ResDeleteProduto deleteProduto(@XmlElement(name = "id") Long id) {
        produtoDao.deleteProduto(id);
        return new ResDeleteProduto();
    }
}
