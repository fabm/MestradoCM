package pt.ipg.mcm.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarioEntity {
  private long idCalendario;
  private Date dataprevista;
  private PadeiroEntity padeiroEntity;
  private List<EncomendaEntity> encomendaEntityList;
  private int confirmacao;

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

  public Date getDataprevista() {
    return dataprevista;
  }

  public void setDataprevista(Date dataprevista) {
    this.dataprevista = dataprevista;
  }

  public PadeiroEntity getPadeiroEntity() {
    return padeiroEntity;
  }

  public void setPadeiroEntity(PadeiroEntity padeiroEntity) {
    this.padeiroEntity = padeiroEntity;
  }

  public void setConfirmacao(int confirmacao) {
    this.confirmacao = confirmacao;
  }

  public int getConfirmacao() {
    return confirmacao;
  }
}
