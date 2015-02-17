package pt.ipg.mcm.entities;

public class SyncDeleteEntity {
  private String tabela;
  private long idSync;


  public String getTabela() {
    return tabela;
  }

  public void setTabela(String tabela) {
    this.tabela = tabela;
  }

  public long getIdSync() {
    return idSync;
  }

  public void setIdSync(long idSync) {
    this.idSync = idSync;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SyncDeleteEntity that = (SyncDeleteEntity) o;

    if (tabela != null ? !tabela.equals(that.tabela) : that.tabela != null) {
      return false;
    }

    return true;
  }

}
