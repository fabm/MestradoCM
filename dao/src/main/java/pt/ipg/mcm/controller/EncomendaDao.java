package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.xmodel.EncomendaDetalheXml;
import pt.ipg.mcm.xmodel.MinhaEncomenda;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

@Stateless
public class EncomendaDao {

    @EJB
    private MappedSql mappedSql;


    public AddEncomendasOut addEncomendas(List<EncomendaIn> encomendaInList, String login) {

        SqlSession session = mappedSql.getSqlSession();
        try {
            AddEncomendasOut addEncomendasOut = addEncomendas(encomendaInList, login, session);
            addEncomendasOut.setCodigo(1);
            addEncomendasOut.setMensagem("Encomendas inseridas com sucesso");
            return addEncomendasOut;
        } finally {
            session.close();
        }

    }

    private AddEncomendasOut addEncomendas(List<EncomendaIn> encomendaInList, String login, SqlSession session) {
        AddEncomendasOut addEncomendasOut = new AddEncomendasOut();
        for (EncomendaIn encomendaIn : encomendaInList) {
            encomendaIn.setLogin(login);
            session.insert("addEncomenda", encomendaIn);
            for (ProdutoAEncomendar produtoAEncomendar : encomendaIn.getProdutoAEncomendarList()) {
                produtoAEncomendar.setServerId(encomendaIn.getIdEncomenda());
                session.insert("addProdutoEncomenda", produtoAEncomendar);
            }
            addEncomendasOut.getEncomendaOuts().add(new EncomendaOut(encomendaIn.getIdEncomenda()));
        }
        return addEncomendasOut;
    }

    public List<MinhaEncomenda> getMinhasEncomendas(final String login, final long idSync) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getMinhasEncomendas", new HashMap<String, Object>() {{
                put("login", login);
                put("sync", idSync);
            }});
        } finally {
            session.close();
        }
    }


    public ResMinhasEncomendasDetalhe getMinhasEncomendasSync(final String login, final long idSync) {

        SqlSession session = mappedSql.getSqlSession();
        try {
            List<EncomendaDetalheXml> encomendaDetalheXmlList = session.selectList("getMihasEncomendasDetalhe", new HashMap<String, Object>() {{
                put("login", login);
                put("sync", idSync);
            }});

            for (EncomendaDetalheXml encomendaDetalheXml : encomendaDetalheXmlList) {
                encomendaDetalheXml.setProdutosEncomendados(session.<ProdutoEncomendadoComPreco>selectList("produtoComPreco", encomendaDetalheXml.getId()));
            }
            return new ResMinhasEncomendasDetalhe(encomendaDetalheXmlList);
        } finally {
            session.close();
        }
    }


    public AddEncomendasOut addAndUpdateEncomendasIn(AddAndUpdateEncomendasIn addAndUpdateEncomendasIn, String login) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            AddEncomendasOut addEncomendasOut = addEncomendas(addAndUpdateEncomendasIn.getEncomendaInList(), login, session);
            addEncomendasOut.setCodigo(1);
            addEncomendasOut.setMensagem("Encomendas inseridas e estados atualizados com sucesso");

            for (EstadoEncomendaIn estadoEncomendaIn : addAndUpdateEncomendasIn.getEstadoEncomendasList()) {
                session.update("updateEstado", estadoEncomendaIn);
            }

            return addEncomendasOut;
        } finally {
            session.close();
        }
    }
}
