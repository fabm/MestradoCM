package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.EncomendaDetalheXml;
import pt.ipg.mcm.xmodel.MinhaEncomenda;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;
import pt.ipg.mcm.xmodel.ResMinhasEncomendasDetalhe;
import pt.ipg.mcm.xmodel.encomendas.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static pt.ipg.mcm.controller.EncomendaState.*;
import static pt.ipg.mcm.errors.Erro.ESTADO_FINAL_INVALIDO;

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
            addEncomendasOut.getEncomendaOutList().add(new EncomendaOut(encomendaIn.getIdEncomenda()));
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


    public AddEncomendasOut addAndUpdateEncomendasIn(AddAndUpdateEncomendasIn addAndUpdateEncomendasIn, String login) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            for (EstadoEncomendaIn estadoEncomendaIn : addAndUpdateEncomendasIn.getEstadoEncomendasList()) {
                validateNextState(estadoEncomendaIn.getIdEncomenda(),session,estadoEncomendaIn.getEstado());
                session.update("updateEstado", estadoEncomendaIn);
            }

            AddEncomendasOut addEncomendasOut = addEncomendas(addAndUpdateEncomendasIn.getEncomendaInList(), login, session);
            addEncomendasOut.setCodigo(1);
            addEncomendasOut.setMensagem("Encomendas inseridas e estados atualizados com sucesso");


            return addEncomendasOut;
        } finally {
            session.close();
        }
    }

    private String getEstado(int estado) {
        switch (estado) {
            case AGUARDA_CONFIRMACAO_PADEIRO:
                return "a aguardar confirmação de um padeiro";
            case AGUARDA_ENTREGA:
                return "a aguardar entrega";
            case CANCELADO_INCOMP_ENTREGA:
                return "cancelado por incompatibilidade de entraga";
            case CANCELADO_CLIENTE:
                return "cancelado pelo cliente";
            case ENTREGA_CONFIRMADA_PADEIRO:
                return "entrega confirmada pelo padeiro";
            case ENTREGA_CONFIRMADA_CLIENTE:
                return "entrega confirmada pelo cliente";
            case REJEICAO_ENTREGA:
                return "rejeição de entrega por parte do cliente";

            default:
                return null;
        }
    }

    private void validateNextState(long idEncomenda, SqlSession session, int nextState) throws MestradoException {
        int currentState = session.selectOne("getEncomendaState", idEncomenda);
        List<Integer> estadosValidos;
        switch (currentState) {
            case AGUARDA_CONFIRMACAO_PADEIRO:
                estadosValidos = asList(CANCELADO_CLIENTE, AGUARDA_ENTREGA, CANCELADO_INCOMP_ENTREGA);
                break;
            case AGUARDA_ENTREGA:
                estadosValidos = asList(CANCELADO_INCOMP_ENTREGA, AGUARDA_CONFIRMACAO_PADEIRO);
                break;
            case ENTREGA_CONFIRMADA_PADEIRO:
                estadosValidos = asList(ENTREGA_CONFIRMADA_CLIENTE, REJEICAO_ENTREGA);
                break;
            default:
                throw new MestradoException(Erro.ESTADO_E_INALTERAVEL, getEstado(currentState));
        }

        if(!estadosValidos.contains(nextState)){
            throw new MestradoException(ESTADO_FINAL_INVALIDO,getEstado(currentState),getEstado(nextState));
        }
    }
}
