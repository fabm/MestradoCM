package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ReqUpdateCategoria;
import pt.ipg.mcm.xmodel.ResGetCategoria;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CategoriaDao {

    @EJB
    private MappedSql mappedSql;

    public RetornoSoap deleteCategoria(long id) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.delete("deleteCategoria", id);
            return new RetornoSoap(1, "Categoria removida com sucesso");
        } finally {
            session.close();
        }
    }

    public Long addCategoria(final Categoria categoria) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.insert("addCategoria", categoria);
            return categoria.getId();
        } finally {
            session.close();
        }
    }

    public void updateCategoria(ReqUpdateCategoria reqUpdateCategoria) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.update("updateCategoria", reqUpdateCategoria);
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
        } finally {
            session.close();
        }
    }

    public List<Categoria> getAll() throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getCategorias");
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
