package pt.ipg.mcm.calls.client.model;

public class CheckException extends Exception {
  public static final String CAMPO_VAZIO = "O campo %s não pode ser vazio";
  public static final String FORMATO_INVALIDO = "O formato do campo %s é inválido";

  private static boolean isEmpty(String str){
    if (str == null || str.length() == 0)
      return true;
    else
      return false;
  }

  public static void checkErroVazio(String field, String fieldName) throws CheckException {
    if (isEmpty(field)) {
      throw new CheckException(fieldName, String.format(CAMPO_VAZIO, fieldName));
    }
  }

  public static void checkErroVazio(Object field, String fieldName) throws CheckException {
    if (field == null) {
      throw new CheckException(fieldName, String.format(CAMPO_VAZIO, fieldName));
    }
  }

  public static CheckException erroFormato(String field) {
    return new CheckException(field, String.format(FORMATO_INVALIDO, field));
  }

  private String field;
  private String string;

  public CheckException(String field, String message) {
    this.field = field;
    this.string = message;
  }

  public String getField() {
    return field;
  }

  @Override
  public String getMessage() {
    return string;
  }
}
