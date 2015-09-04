package pt.ipg.mcm.controller.imp;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UtilizadorDao {

    @EJB
    private MappedSql mappedSql;


    public RetornoSoap addUtilizador(ReqAddUtilizador reqAddUtilizador) {
        SqlSession session = mappedSql.getSqlSession();
        session.insert("addPadeiro", reqAddUtilizador);
        return new RetornoSoap(1, "Padeiro criado com sucesso");
    }

//OBTER PADEIRO


    public ResGetPadeiro getPadeiro(long idpadeiro) {
        SqlSession session = mappedSql.getSqlSession();
        ResGetPadeiro resGetPadeiro = session.selectOne("getPadeiro", idpadeiro);
        session.close();
        return resGetPadeiro;
    }




    public void createUserCliente(UserClienteCreationRequest utilizadorCliente) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            int count = session.selectOne("countUtilizadorByLogin", utilizadorCliente.getLogin());

            if (count > 0) {
                throw new MestradoException(Erro.LOGIN_JA_EXISTENTE, utilizadorCliente.getLogin());
            }

            session.insert("addCliente", utilizadorCliente);
        } finally {
            session.close();
        }
    }
}
