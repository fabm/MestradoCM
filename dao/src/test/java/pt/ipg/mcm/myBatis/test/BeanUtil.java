package pt.ipg.mcm.myBatis.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtil <T>{
    private Class<T> beanClass;
    private int padding;

    public BeanUtil(Class<T> beanClass, int padding) {
        this.beanClass = beanClass;
        this.padding = padding;
    }

    public void printProperties(Iterable<T> iterable) {
        if(beanClass == Map.class){
            printMapProperties((Iterable<Map<String, Object>>) iterable);
            return;
        }
        for (T o : iterable) {
            System.out.println();
            printProperties(o);
        }
    }

    public void printProperties(T o) {
        if(beanClass == Map.class){
            printMapProperties((Iterable<Map<String, Object>>) o);
        }
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            throw new IllegalStateException(e);
        }
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            final String name = propertyDescriptor.getName();
            if (name.equals("class")) {
                continue;
            }
            final Object value;
            try {
                value = propertyDescriptor.getReadMethod().invoke(o);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            String stringed;
            if (value == null) {
                stringed = "\u001B[32mnull\u001B[0m";
            } else {
                stringed = value.toString();
            }
            System.out.printf("%" + padding + "s -> %s\n", name, stringed);
        }
    }

    private void printMapProperties(Iterable<Map<String, Object>> mapIterable) {
        for (Map<String, Object> map : mapIterable) {
            System.out.println();
            printMapProperties(map);
        }
    }

    private void printMapProperties(Map<String, Object> o) {
        for (Map.Entry<String, Object> entry : o.entrySet()) {
            Object value = entry.getValue();
            String stringed;
            if (value == null) {
                stringed = "\u001B[32mnull\u001B[0m";
            } else {
                stringed = value.toString();
            }
            System.out.printf("%" + padding + "s -> %s\n", entry.getKey(), stringed);
        }
    }

}
