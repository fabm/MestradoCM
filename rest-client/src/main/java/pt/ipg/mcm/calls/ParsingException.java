package pt.ipg.mcm.calls;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ParsingException extends RuntimeException{
  public static final String DESERIALIZING_EXCEPTION ="Problem when deserialize object";
  public static final String SERIALIZING_EXCEPTION ="Problem when serialize object";
  public ParsingException(){

  }

  public ParsingException(String serializingException, Exception e) {
    super(serializingException,e);
  }
}
