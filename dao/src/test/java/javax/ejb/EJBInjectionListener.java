package javax.ejb;

import com.google.inject.Injector;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class EJBInjectionListener implements TypeListener {

    private Provider<Injector> injectorProvider;

    public EJBInjectionListener(Provider<Injector> injectorProvider) {
        this.injectorProvider = injectorProvider;
    }

    public static <I>void runPostConstruct(I instance){
        for(Method method:instance.getClass().getDeclaredMethods()){
            if(method.getAnnotation(PostConstruct.class)!=null){
                method.setAccessible(true);
                try {
                    method.invoke(instance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        Class<?> clazz = type.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(EJB.class)) {
                    Object ejbInstance = injectorProvider.get().getInstance(field.getType());
                    EJBInjector<I> ejbInjector = new EJBInjector<I>(field, ejbInstance);
                    encounter.register(ejbInjector);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}
