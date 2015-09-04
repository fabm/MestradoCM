package pt.ipg.mcm.controller;

import pt.ipg.mcm.batis.MappedSql;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class PadeiroDao {

    @EJB
    private MappedSql mappedSql;

}
