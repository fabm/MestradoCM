package pt.ipg.mcm.rs;

import pt.ipg.mcm.rs.services.CategoriasRsService;

import javax.annotation.ManagedBean;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ManagedBean
public class McmApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(CategoriasRsService.class);
    return classes;
  }
}
