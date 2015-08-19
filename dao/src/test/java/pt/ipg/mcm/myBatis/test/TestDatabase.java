package pt.ipg.mcm.myBatis.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ProdutoXml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class TestDatabase {
    private static SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(TestDatabase.class.getResource("/config.xml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDatabase() throws IOException, URISyntaxException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        getCategoria(3L);
    }

    private void deleteCategoria() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        session.delete("deleteCategoria",201);

        session.commit();
        session.close();
    }

    private void getCategoria(Long id){
        if(id == null){
            id = 1L;
        }
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<Categoria>selectList("getCategoria", id), Categoria.class);

        session.close();

    }

    private void getCategorias() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<Categoria>selectList("getCategorias", 1), Categoria.class);

        session.close();
    }

    private void getCategoriasDesync() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<Categoria>selectList("getCategoriasDesync",0), Categoria.class);

        session.close();
    }

    private <T> void printProperties(int padding, Iterable<Map<String, Object>> mapIterable) {
        for (Map<String, Object> map : mapIterable) {
            System.out.println();
            printProperties(padding, map);
        }
    }

    private <T> void printProperties(int padding, Map<String, Object> o) {
        for (Map.Entry<String, Object> entry : o.entrySet()) {
            Object value = entry.getValue();
            String stringed;
            if (value == null) {
                stringed = "\u001B[32mnull\u001B[0m";
            } else {
                stringed = value.toString();
            }
            System.out.printf("%" + padding + "s -> %s\n", entry.getKey(), stringed);
        }
    }

    private void getProduto() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<ProdutoXml>selectList("getProduto", 1), ProdutoXml.class);

        session.close();
    }

    private void getProdutosCategoriaComFoto() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<ProdutoCategoria>selectList("getProdutosCategoria", new HashMap<String, Object>() {{
            put("comFoto", true);
        }}), ProdutoCategoria.class);

        session.close();
    }

    private void getProdutosCategoria() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        printProperties(20, session.<ProdutoCategoria>selectList("getProdutosCategoria", new HashMap<String, Object>() {{
            put("comFoto", false);
        }}), ProdutoCategoria.class);

        session.close();
    }

    private <T> void printProperties(int padding, Iterable<T> iterable, Class<T> beanClass) {
        for (T o : iterable) {
            System.out.println();
            printProperties(padding, beanClass, o);
        }
    }

    private <T> void printProperties(int padding, Class<T> beanClass, T o) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            throw new IllegalStateException(e);
        }
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            final String name = propertyDescriptor.getName();
            if (name.equals("class")) {
                continue;
            }
            final Object value;
            try {
                value = propertyDescriptor.getReadMethod().invoke(o);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            String stringed;
            if (value == null) {
                stringed = "\u001B[32mnull\u001B[0m";
            } else {
                stringed = value.toString();
            }
            System.out.printf("%" + padding + "s -> %s\n", name, stringed);
        }
    }


    private void getProdutos() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        SqlSession session = SQL_SESSION_FACTORY.openSession();
        printProperties(20, session.<ProdutoXml>selectList("getProdutos", 0), ProdutoXml.class);
        session.close();
    }


}
