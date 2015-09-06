package pt.ipg.mcm.controller;


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


    public ResAddPadeiro addPadeiro(ReqAddUtilizador reqAddPadeiro) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.insert("addPadeiro", reqAddPadeiro);
            return new ResAddPadeiro(reqAddPadeiro.getId());
        } finally {
            session.close();
        }
    }

    public ResGetPadeiro getPadeiro(long idpadeiro) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectOne("getPadeiro", idpadeiro);
        } finally {
            session.close();
        }
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
