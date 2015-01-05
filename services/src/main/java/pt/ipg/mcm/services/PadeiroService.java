package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.PadeiroDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.ReqAddPadeiro;
import pt.ipg.mcm.xmodel.ResAddPadeiro;
import pt.ipg.mcm.xmodel.Retorno;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

@WebService(serviceName = "Padeiro", portName = "PadeiroPort")
public class PadeiroService {

  @EJB
  private PadeiroDao padeiroDao;

  @WebMethod(operationName = "add-padeiro")
  public ResAddPadeiro addPadeiro(@WebParam(name = "req-add-padeiro") @XmlElement(required = true) ReqAddPadeiro reqAddPadeiro) {
    Map<String, String> aliasMap = new HashMap<String, String>();
    aliasMap.put("idUtilizador", "utilizador");
    try {
      Validacao.getInstance().valida(reqAddPadeiro, aliasMap);
      return padeiroDao.addPadeiro(reqAddPadeiro);
    } catch (MestradoException e) {
      ResAddPadeiro resAddPadeiro = new ResAddPadeiro();
      resAddPadeiro.setRetorno(new Retorno(e));
      return resAddPadeiro;
    }
  }
}
