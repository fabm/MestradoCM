package pt.ipg.mcm.controller;


import pt.ipg.mcm.entities.PadeiroEntity;
import pt.ipg.mcm.entities.UtilizadorPadeiroEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ResAddUtilizador;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by BrrF on 19-01-2015.
 */
public class UtilizadorDao {

    private static Logger LOGGER = Logger.getLogger(UtilizadorDao.class.getName());

    @Resource(lookup = "jdbc/mestrado")
    private DataSource mestradoDataSource ;


    public void addUtilizador (UtilizadorPadeiroEntity utilizadorPadeiroEntity) throws MestradoException {
        try {
            Connection connection = mestradoDataSource.getConnection();
            ResAddUtilizador resAddUtilizador = new ResAddUtilizador();

            CallableStatement call;
            //                             (?,?,?) (LOGIN, PASSWORD, NOME)
            call = connection.prepareCall("{call P_ADD_UTILIZADOR_PADEIRO(?,?,?)");
            call.setString(1, utilizadorPadeiroEntity.getLogin());
            call.setString(2, utilizadorPadeiroEntity.getPassword());
            call.setString(3, utilizadorPadeiroEntity.getNome());

            call.executeUpdate();
        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }

    }

//OBTER PADEIRO


    public PadeiroEntity getPadeiro (long idpadeiro)throws  MestradoException{
        PadeiroEntity padeiroEntity =  new PadeiroEntity();
        try{
            String sqlString = "SELECT PADEIRO.NOME,\n" +
                    "FROM PADEIRO\n" +
                    "WHERE PADEIRO.ID_PADEIRO = ?";

            Connection connection = mestradoDataSource.getConnection();
            PreparedStatement call = connection.prepareStatement(sqlString);
            call.setLong(1,idpadeiro);
            ResultSet rs = call.executeQuery();

            if (!rs.next()){
                throw new MestradoException(Erro.PADEIRO_NAO_ENCONTRADO, idpadeiro);
            }

            padeiroEntity.setNome(rs.getString(1));


        }catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }

        return padeiroEntity;

    }



}