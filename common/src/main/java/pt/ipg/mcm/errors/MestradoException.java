package pt.ipg.mcm.errors;

import java.util.HashMap;
import java.util.Map;

public class MestradoException extends Exception {

  private final int codigo;

  public MestradoException(int codigo, String msg, Object... objects) {
    super(MestradoException.format(msg, objects));
    this.codigo = codigo;
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
