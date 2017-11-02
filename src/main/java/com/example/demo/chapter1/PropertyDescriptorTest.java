package com.example.demo.chapter1;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2017/9/29 15:33
 * DESC:
 */
public class PropertyDescriptorTest {

    public static void main(String[] args) throws Exception {

        Person person = new Person();

//        PropertyDescriptor descriptor = new PropertyDescriptor("username", Person.class);
//
//        System.out.println(descriptor);

        /**
         * 内省 是通过反射来操作javaBean
         */

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            Method writeMethod = descriptor.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }
            System.out.println(writeMethod.getName());
            writeMethod.invoke(person, "kkkk");
            Method readMethod = descriptor.getReadMethod();
            System.out.println(readMethod.invoke(person, null));
        }
    }
}
