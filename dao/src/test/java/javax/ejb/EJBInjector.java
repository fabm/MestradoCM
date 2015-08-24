package javax.ejb;

import com.google.inject.MembersInjector;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EJBInjector<I> implements MembersInjector<I> {

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

    private Field field;
    private MembersInjector<Object> membersInjector;
    private Class<?> aClass;

    public EJBInjector(Field field, MembersInjector<Object> membersInjector, Class<?> aClass) {
        this.membersInjector = membersInjector;
        this.aClass = aClass;
        field.setAccessible(true);
        this.field = field;
    }



    @Override
    public void injectMembers(I instance) {
        try {
            final Object ejbInstance = aClass.newInstance();
            field.set(instance, ejbInstance);
            membersInjector.injectMembers(ejbInstance);

            runPostConstruct(ejbInstance);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
