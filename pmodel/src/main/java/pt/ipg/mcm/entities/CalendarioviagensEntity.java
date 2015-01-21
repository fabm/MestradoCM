package pt.ipg.mcm.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CalendarioviagensEntity {
  private long idCalendario;
  private Timestamp dataprevista;
  private PadeiroEntity padeiroEntity;
  private List<EncomendaEntity> encomendaEntityList;

  public List<EncomendaEntity> getEncomendaEntityList() {
    if (encomendaEntityList == null) {
      encomendaEntityList = new ArrayList<EncomendaEntity>();
    }
    return encomendaEntityList;
  }

  public long getIdCalendario() {
    return idCalendario;
  }

  public void setIdCalendario(long idCalendario) {
    this.idCalendario = idCalendario;
  }

  public Timestamp getDataprevista() {
    return dataprevista;
  }

  public void setDataprevista(Timestamp dataprevista) {
    this.dataprevista = dataprevista;
  }

  public PadeiroEntity getPadeiroEntity() {
    return padeiroEntity;
  }

  public void setPadeiroEntity(PadeiroEntity padeiroEntity) {
    this.padeiroEntity = padeiroEntity;
  }

}
