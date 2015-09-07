package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.calls.client.model.encomendas.AddEncomendasOutRest;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaOutRest;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddEncomendasOut extends RetornoSoap {

  public AddEncomendasOut(MestradoException e) {
    super(e);
  }

  public AddEncomendasOut() {
    encomendaOutList = new ArrayList<EncomendaOut>();
  }

  @XmlElementWrapper(name = "encomendas")
  @XmlElement(name = "encomenda")
  private List<EncomendaOut> encomendaOutList;

  public List<EncomendaOut> getEncomendaOutList() {
    return encomendaOutList;
  }

  public AddEncomendasOutRest convert() {
    AddEncomendasOutRest addEncomendasOutRest = new AddEncomendasOutRest();

    List<EncomendaOutRest> encomendaOutRestList = new ArrayList<EncomendaOutRest>();

    for(EncomendaOut encomendaOut:encomendaOutList){
      encomendaOutRestList.add(encomendaOut.convert());
    }
    addEncomendasOutRest.setEncomendaOutRestList(encomendaOutRestList);

    return null;
  }
}
