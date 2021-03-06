package pt.ipg.mcm.myBatis.test.sql;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.myBatis.test.BeanUtil;
import pt.ipg.mcm.xmodel.Categoria;

import static pt.ipg.mcm.myBatis.test.SqlUtils.SQL_SESSION_FACTORY;

public class CategoriaTests {
    public void deleteCategoria(long id) {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        session.delete("deleteCategoria",id);

        session.commit();
        session.close();
    }

    public void getCategoria(long id){
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(Categoria.class,20)
            .printProperties(session.<Categoria>selectList("getCategoria", id));

        session.close();

    }

    public void getCategorias() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(Categoria.class,20)
            .printProperties(session.<Categoria>selectList("getCategorias", 1));

        session.close();
    }

    public void getCategoriasDesync(long id) {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(Categoria.class,20)
            .printProperties(session.<Categoria>selectList("getCategoriasDesync",0));

        session.close();
    }
}
