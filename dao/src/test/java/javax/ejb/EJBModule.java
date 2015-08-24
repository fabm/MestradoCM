package javax.ejb;

import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.matcher.Matchers;
import pt.ipg.mcm.batis.MappedSql;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class EJBModule extends AbstractModule{

    @Inject
    private EJBInjectionListener ejbInjectionListener;

    @Override
    protected void configure() {
        final List<Class<?>> classes = Arrays.<Class<?>>asList(MappedSql.class);
        bindListener(Matchers.any(), new EJBInjectionListener(classes));
    }
}
