package ejb.injectors;

import com.google.inject.MembersInjector;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EJBInjector implements MembersInjector {

    public static void runPostConstruct(Object instance) {
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (method.getAnnotation(PostConstruct.class) != null) {
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


    private Object ejbInstance;
    private Field field;

    public EJBInjector(Field field, Object ejbInstance) {
        this.ejbInstance = ejbInstance;
        field.setAccessible(true);
        this.field = field;
    }


    @Override
    public void injectMembers(Object instance) {
        try {
            field.set(instance, ejbInstance);
            runPostConstruct(ejbInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
