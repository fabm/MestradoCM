package pt.ipg.mcm.controller.imp;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ReqUpdateCategoria;
import pt.ipg.mcm.xmodel.ResGetCategoria;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CategoriaDao {

    @EJB
    private MappedSql mappedSql;

    public Integer addCategoria(final Categoria categoria) {
        SqlSession session = mappedSql.getSqlSession();
        final HashMap<String, Object> map;
        try {
            session.insert("addCategoria", categoria);
        } finally {
            session.close();
        }
        return categoria.getId();
    }

    public void updateCategoria(ReqUpdateCategoria reqUpdateCategoria) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.update("updateCategoria", reqUpdateCategoria);
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }

    public ResGetCategoria getCategoria(long idCategoria) throws MestradoException {

        ResGetCategoria resGetCategoria;
        SqlSession session = mappedSql.getSqlSession();
        try {
            resGetCategoria = session.selectOne("getCategoria", idCategoria);

            if (resGetCategoria == null) {
                throw new MestradoException(Erro.CATEGORIA_NAO_ENCONTRADO, idCategoria);
            }
            return resGetCategoria;
        } catch (PersistenceException e) {
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }

    public List<Categoria> getAll() throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getCategorias");
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }

    public List<Categoria> getDesync(final Long versao) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getCategoriasDesync", new HashMap<String, Long>() {{
                put("sync", versao);
            }});
        } finally {
            session.close();
        }
    }


}
