package pt.ipg.mcm.rs;

import pt.ipg.mcm.rs.services.CategoriaRService;
import pt.ipg.mcm.rs.services.ClienteRService;
import pt.ipg.mcm.rs.services.DeleteSyncRService;
import pt.ipg.mcm.rs.services.EncomendaRService;
import pt.ipg.mcm.rs.services.AuthenticationRService;
import pt.ipg.mcm.rs.services.LocalidadeRService;
import pt.ipg.mcm.rs.services.ProdutoRService;
import pt.ipg.mcm.services.ClienteService;

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
    classes.add(ProdutoRService.class);
    classes.add(AuthenticationRService.class);
    classes.add(DeleteSyncRService.class);
    classes.add(ClienteRService.class);
    classes.add(LocalidadeRService.class);
    return classes;
  }
}
