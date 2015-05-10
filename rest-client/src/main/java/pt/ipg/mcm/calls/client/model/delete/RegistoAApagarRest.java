package pt.ipg.mcm.calls.client.model.delete;

public class RegistoAApagarRest {
  private String tabela;
  private Long id;

  public String getTabela() {
    return tabela;
  }

  public void setTabela(String tabela) {
    this.tabela = tabela;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
