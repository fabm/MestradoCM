package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.RetornoSoap;
import pt.ipg.mcm.xmodel.UserClienteCreationRequest;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ClienteDao {

    @EJB
    private MappedSql mappedSql;

    public RetornoSoap createUserCliente(UserClienteCreationRequest utilizadorCliente) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            int count = session.selectOne("countUtilizadorByLogin", utilizadorCliente.getLogin());

            if (count > 0) {
                throw new MestradoException(Erro.LOGIN_JA_EXISTENTE, utilizadorCliente.getLogin());
            }

            session.insert("addCliente", utilizadorCliente);
            return new RetornoSoap(1, "Registo efectuado com sucesso");
        } finally {
            session.close();
        }
    }

    public ResGetCliente getCliente(long id) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectOne("getCliente", id);
        } finally {
            session.close();
        }
    }

    public void deleteCliente(final long id) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.delete("deleteCliente", id);
        } finally {
            session.close();
        }
    }
}
