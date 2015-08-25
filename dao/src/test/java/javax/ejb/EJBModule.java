package javax.ejb;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;

public class EJBModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new EJBInjectionListener(getProvider(Injector.class)));
    }

}
