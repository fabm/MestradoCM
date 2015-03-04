package pt.ipg.mcm.controller.dao;

import pt.ipg.mcm.entities.PadeiroEntity;
import pt.ipg.mcm.entities.UtilizadorPadeiroEntity;
import pt.ipg.mcm.errors.MestradoException;

public interface UtilizadorDao {
  void addUtilizador (UtilizadorPadeiroEntity utilizadorPadeiroEntity) throws MestradoException;

  PadeiroEntity getPadeiro(long idpadeiro)throws  MestradoException;
}
