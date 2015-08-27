package ejb.injectors;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import javax.ejb.EJB;
import java.lang.reflect.Field;

public class EJBInjectionListener implements TypeListener {

    private Provider<Injector> injectorProvider;

    public EJBInjectionListener(Provider<Injector> injectorProvider) {
        this.injectorProvider = injectorProvider;
    }

    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        Class<?> clazz = type.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(EJB.class)) {
                    Object ejbInstance = injectorProvider.get().getInstance(field.getType());
                    EJBInjector ejbInjector = new EJBInjector(field, ejbInstance);
                    encounter.register(ejbInjector);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
