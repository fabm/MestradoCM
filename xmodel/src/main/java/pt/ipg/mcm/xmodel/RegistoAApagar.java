package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RegistoAApagar {
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
