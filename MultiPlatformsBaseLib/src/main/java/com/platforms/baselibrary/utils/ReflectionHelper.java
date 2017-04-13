package com.platforms.baselibrary.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的帮助类
 */
public class ReflectionHelper {

    public static Class getClass(String className) {
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * 用指定类的构造方法创建一个实例
     * @param clazz Class
     * @param params 构造参数
     * @return 实例
     */
    public static Object newInstance(Class clazz, Object... params) {
        if (clazz != null) {
            Constructor[] ctors = clazz.getDeclaredConstructors();
            Constructor constructor = null;
            int paramsCount = params == null ? 0 : params.length;
            for (Constructor ctor : ctors) {
                constructor = ctor;
                if (constructor.getGenericParameterTypes().length == paramsCount)
                    break;
            }
            if (constructor != null) {
                constructor.setAccessible(true);
                try {
                    return constructor.newInstance(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 调用一个类的方法
     * @param receiver 方法的接受者，通常是clazz的实例。如果调用静态方法则传null。
     * @param clazz Class
     * @param methodName 方法名
     * @param args 参数，可为null
     * @return
     */
    public static Object invokeMethod(Object receiver, Class clazz, String methodName, Object... args) {
        if (clazz == null || methodName == null) {
            return null;
        }

        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (methodName.equals(method.getName())) {
                method.setAccessible(true);
                try {
                    return method.invoke(receiver, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }

    public static boolean setFiledIntValue(Object object, String filedName, int value) {
        if (object == null) return false;
        Class clazz = object.getClass();
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        if (field == null) {
            return false;
        }
        try {
            field.setAccessible(true);
            field.setInt(object, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
