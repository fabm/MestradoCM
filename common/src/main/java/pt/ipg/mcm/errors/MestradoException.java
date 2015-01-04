package pt.ipg.mcm.errors;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class MestradoException extends Exception {

  private final int codigo;

  public MestradoException(Erro erro,Object...params){
    super(MestradoException.msgFormat(erro.getMensagem(),params));
    codigo = erro.getCodigo();
  }

  private static String msgFormat(String mensagem, Object[] params) {
    MessageFormat messageFormat = new MessageFormat(mensagem);
    return messageFormat.format(params);
  }

  private static String format(String msg, Object... objects) {
    if (objects.length == 0) {
      return msg;
    }
    return String.format(msg, objects);
  }

  public int getCodigo() {
    return codigo;
  }

  public Map<String,Object> toMap(){
    Map<String,Object> returnMap = new HashMap<String, Object>();
    returnMap.put("erro",this.getCodigo());
    returnMap.put("mensagem",this.getMessage());
    return returnMap;
  }

}
