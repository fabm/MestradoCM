package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JaxbDateSerializer extends XmlAdapter<String,Date>{
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  @Override
  public Date unmarshal(String v) throws Exception {
    return dateFormat.parse(v);
  }

  @Override
  public String marshal(Date v) throws Exception {
    return dateFormat.format(v);
  }
}
