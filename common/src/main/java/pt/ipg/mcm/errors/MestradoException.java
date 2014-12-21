package pt.ipg.mcm.errors;

public class MestradoException extends Exception{
  public MestradoException(String msg,Object...objects) {
    super(String.format(msg,objects));
  }
}
