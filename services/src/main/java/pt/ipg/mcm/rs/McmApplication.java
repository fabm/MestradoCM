package pt.ipg.mcm.rs;

import pt.ipg.mcm.rs.services.CategoriaRService;
import pt.ipg.mcm.rs.services.EncomendaRService;

import javax.annotation.ManagedBean;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ManagedBean
public class McmApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(CategoriaRService.class);
    classes.add(EncomendaRService.class);
    return classes;
  }
}
