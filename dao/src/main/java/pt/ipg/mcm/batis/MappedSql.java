package pt.ipg.mcm.batis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.InputStream;

@Singleton
public class MappedSql {
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    private void init() {
        InputStream inputStream = getClass().getResourceAsStream("/config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
