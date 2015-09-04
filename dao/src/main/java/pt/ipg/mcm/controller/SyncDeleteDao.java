package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.RegistoAApagar;
import pt.ipg.mcm.xmodel.RegistosAApagar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SyncDeleteDao {
    @EJB
    private MappedSql mappedSql;

    public RegistosAApagar getRowsToDelete(long versao) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return new RegistosAApagar(session.<RegistoAApagar>selectList("getRegistosAApagar", versao));
        } finally {
            session.close();
        }

    }
}
