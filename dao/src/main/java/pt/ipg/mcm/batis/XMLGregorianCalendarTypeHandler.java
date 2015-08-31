package pt.ipg.mcm.batis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

public class XMLGregorianCalendarTypeHandler implements TypeHandler<XMLGregorianCalendar> {
    @Override
    public void setParameter(PreparedStatement ps, int i, XMLGregorianCalendar parameter, JdbcType jdbcType) throws SQLException {
        ps.setDate(i, new java.sql.Date(parameter.toGregorianCalendar().getTimeInMillis()));
    }

    @Override
    public XMLGregorianCalendar getResult(ResultSet rs, String columnName) throws SQLException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(rs.getDate(columnName));
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public XMLGregorianCalendar getResult(ResultSet rs, int columnIndex) throws SQLException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(rs.getDate(columnIndex));
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public XMLGregorianCalendar getResult(CallableStatement cs, int columnIndex) throws SQLException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(cs.getDate(columnIndex));
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }
}
