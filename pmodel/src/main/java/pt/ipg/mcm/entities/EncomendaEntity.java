package pt.ipg.mcm.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EncomendaEntity {
  private long idEncomenda;
  private Timestamp dataEntrega;
  private EncomendaEntity encomendaAssociada;
  private long quantidade;
  private long sync;
  private long fatura;
  private ClienteEntity clienteEntity;
  private List<EncomendaProdutoEntity> encomendaProdutoEntityList;
  private List<EncomendaEntity> encomendasAssociadas;
  private CalendarioviagensEntity calendarioviagensEntity;

  public List<EncomendaProdutoEntity> getEncomendaProdutoEntityList() {
    if (encomendaProdutoEntityList == null) {
      encomendaProdutoEntityList = new ArrayList<EncomendaProdutoEntity>();
    }
    return encomendaProdutoEntityList;
  }

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  public Timestamp getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Timestamp dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public EncomendaEntity getEncomendaAssociada() {
    return encomendaAssociada;
  }

  public void setEncomendaAssociada(EncomendaEntity encomendaAssociada) {
    this.encomendaAssociada = encomendaAssociada;
  }

  public long getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(long quantidade) {
    this.quantidade = quantidade;
  }

  public long getSync() {
    return sync;
  }

  public void setSync(long sync) {
    this.sync = sync;
  }

  public long getFatura() {
    return fatura;
  }

  public void setFatura(long fatura) {
    this.fatura = fatura;
  }

  public List<EncomendaEntity> getEncomendasAssociadas() {
    if (encomendasAssociadas == null) {
      return new ArrayList<EncomendaEntity>();
    }
    return encomendasAssociadas;
  }

  public CalendarioviagensEntity getCalendarioviagensEntity() {
    return calendarioviagensEntity;
  }

  public void setCalendarioviagensEntity(CalendarioviagensEntity calendarioviagensEntity) {
    this.calendarioviagensEntity = calendarioviagensEntity;
  }

  public ClienteEntity getClienteEntity() {
    return clienteEntity;
  }

  public void setClienteEntity(ClienteEntity clienteEntity) {
    this.clienteEntity = clienteEntity;
  }
}
