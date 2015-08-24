package javax.ejb;

import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Field;
import java.util.List;

public class EJBInjectionListener implements TypeListener {

    private List<Class<?>> ejbClasses;

    public EJBInjectionListener(List<Class<?>> ejbClasses) {
        this.ejbClasses = ejbClasses;
    }

    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        Class<?> clazz = type.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(EJB.class)) {
                    MembersInjector<Object> mi = encounter.getMembersInjector(Object.class);
                    encounter.register(new EJBInjector<I>(field, mi, getEjbClass(field.getType())));
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private Class<?> getEjbClass(Class<?> current){
        for(Class<?> iterated:ejbClasses){
            if(iterated.isAssignableFrom(current)){
                return iterated;
            }
        }
        throw new IllegalStateException("Class not present for EJB "+current);
    }
}
