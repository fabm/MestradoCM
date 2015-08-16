package pt.ipg.mcm.batis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.sql.DataSource;
import java.io.InputStream;

@Singleton
public class MappedSql {
    @Resource(lookup = "jdbc/mestrado")
    private DataSource mestradoDataSource;
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    private void init(){
        InputStream inputStream = getClass().getResourceAsStream("/config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, mestradoDataSource);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setEnvironment(environment);
    }

    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
