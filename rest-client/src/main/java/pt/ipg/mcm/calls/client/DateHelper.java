package pt.ipg.mcm.calls.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
  private SimpleDateFormat dateFormat;

  public DateHelper(Format dateFormat) {
    this.dateFormat = new SimpleDateFormat(dateFormat.format);
  }

  public Date toDate(String string) throws ParseException {
    return dateFormat.parse(string);
  }
  public String toString(int year, int month, int day){
    return toString(year,month,day,0,0);
  }
  public String toString(int year, int month, int day,int hour, int minute){
    return dateFormat.format(new GregorianCalendar(year,month,day,hour,minute).getTime());
  }
  public String toString(Date date) {
    return dateFormat.format(date);
  }

  public static enum Format{
    COMPLETE("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    COMPACT("yyyy-MM-dd HH:mm");

    String format;

    Format(String format) {
      this.format = format;
    }

    public String getFormat() {
      return format;
    }
  }
}
