package pt.ipg.mcm.myBatis.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlUtils {
    public static SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(TestDatabase.class.getResource("/config.xml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
