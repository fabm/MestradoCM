package javax.ejb;

import com.google.inject.MembersInjector;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EJBInjector<I> implements MembersInjector<I> {

    public static <I> void runPostConstruct(I instance) {
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
    public void injectMembers(I instance) {
        try {
            field.set(instance, ejbInstance);
            runPostConstruct(ejbInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
