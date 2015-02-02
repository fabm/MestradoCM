package pt.ipg.mcm.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EncomendaEntity {
  private long idEncomenda;
  private Timestamp dataEntrega;
  private EncomendaEntity encomendaAssociada;
  private long sync;
  private long fatura;
  private Estado estado;
  private BigDecimal perco;
  private ClienteEntity clienteEntity;
  private List<EncomendaProdutoEntity> encomendaProdutoEntityList;
  private List<EncomendaEntity> encomendasAssociadas;
  private CalendarioEntity calendarioEntity;

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

  public long getSync() {
    return sync;
  }

  public void setSync(long sync) {
    this.sync = sync;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
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

  public CalendarioEntity getCalendarioEntity() {
    return calendarioEntity;
  }

  public void setCalendarioEntity(CalendarioEntity calendarioEntity) {
    this.calendarioEntity = calendarioEntity;
  }

  public ClienteEntity getClienteEntity() {
    return clienteEntity;
  }

  public void setClienteEntity(ClienteEntity clienteEntity) {
    this.clienteEntity = clienteEntity;
  }

  public BigDecimal getPerco() {
    return perco;
  }

  public void setPreco(BigDecimal perco) {
    this.perco = perco;
  }

  public static enum Estado {
    A_ESPERA_PADEIRO(1),
    TEMPO_ESGOTADO_UTILIZADOR(2),
    A_ESPERA_UTILIZADOR(3),
    AGUARDA_ENTREGA(4),
    CANCELAMENTO_POR_INCOMPATIBILIDADE_DE_ENTREGA(5),
    CANCELAMANTO_PARA_NOVA_ENTREGA_PARCIAL(6);

    int estado;

    Estado(int estado) {
      this.estado = estado;
    }

    public int getNumEstado() {
      return estado;
    }

    public static Estado getByNumber(int nEstado) {
      switch (nEstado) {
        case 1:
          return A_ESPERA_PADEIRO;
        case 2:
          return TEMPO_ESGOTADO_UTILIZADOR;
        case 3:
          return A_ESPERA_UTILIZADOR;
        case 4:
          return AGUARDA_ENTREGA;
        case 5:
          return CANCELAMENTO_POR_INCOMPATIBILIDADE_DE_ENTREGA;
        case 6:
          return CANCELAMANTO_PARA_NOVA_ENTREGA_PARCIAL;
        default:
          throw new IllegalStateException();
      }
    }
  }
}
