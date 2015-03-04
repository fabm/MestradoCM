package pt.ipg.mcm.controller.dao;

import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.errors.MestradoException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by francisco on 01/03/15.
 */
public interface CategoriaDao {
  Long addCategoria(CategoriaEntity categoriaEntity) throws SQLException;

  CategoriaEntity getCategoria(long idCategoria) throws MestradoException;

  List<CategoriaEntity> getAll() throws MestradoException;

  List<CategoriaEntity> getDesync(Long versao) throws MestradoException;
}
