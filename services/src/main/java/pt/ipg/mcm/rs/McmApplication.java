package pt.ipg.mcm.rs;

import org.glassfish.jersey.jackson.JacksonFeature;
import pt.ipg.mcm.rs.services.CategoriaRService;
import pt.ipg.mcm.rs.services.ClienteRService;
import pt.ipg.mcm.rs.services.DeleteSyncRService;
import pt.ipg.mcm.rs.services.EncomendaRService;
import pt.ipg.mcm.rs.services.AuthenticationRService;
import pt.ipg.mcm.rs.services.IterfacesTest;
import pt.ipg.mcm.rs.services.LocalidadeRService;
import pt.ipg.mcm.rs.services.ProdutoRService;
import pt.ipg.mcm.rs.services.UtilizadorRService;

import javax.annotation.ManagedBean;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
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
    classes.add(UtilizadorRService.class);
    classes.add(IterfacesTest.class);

    classes.add(JacksonFeature.class);
    classes.add(MCMJacksonJsonProvider.class);
    return classes;
  }
}
