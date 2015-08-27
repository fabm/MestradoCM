package ejb.injectors;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import pt.ipg.mcm.batis.MappedSql;

public class EJBModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MappedSql.class).in(Singleton.class);
        bindListener(Matchers.any(), new EJBInjectionListener(getProvider(Injector.class)));
    }

}
