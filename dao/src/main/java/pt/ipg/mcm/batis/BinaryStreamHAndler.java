package pt.ipg.mcm.batis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BinaryStreamHandler implements TypeHandler<InputStream>{
    @Override
    public void setParameter(PreparedStatement ps, int i, InputStream parameter, JdbcType jdbcType) throws SQLException {
        ps.setBinaryStream(i,parameter);
    }

    @Override
    public InputStream getResult(ResultSet rs, String columnName) throws SQLException {
        throw new IllegalStateException("not applicable");
    }

    @Override
    public InputStream getResult(ResultSet rs, int columnIndex) throws SQLException {
        throw new IllegalStateException("not applicable");
    }

    @Override
    public InputStream getResult(CallableStatement cs, int columnIndex) throws SQLException {
        throw new IllegalStateException("not applicable");
    }
}
