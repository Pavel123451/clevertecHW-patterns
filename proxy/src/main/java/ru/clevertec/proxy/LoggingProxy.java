package ru.clevertec.proxy;

import ru.clevertec.service.MyService;
import ru.clevertec.annotation.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggingProxy implements MyService {
    private final MyService target;

    public LoggingProxy(MyService target) {
        this.target = target;
    }

    @Override
    public void performTask() {
        invokeMethod("performTask");
    }

    @Override
    public void anotherTask() {
        invokeMethod("anotherTask");
    }

    private void invokeMethod(String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName);
            if (method.isAnnotationPresent(Log.class)) {
                System.out.println("Start: "
                        + method.getDeclaringClass().getName()
                        + "."
                        + method.getName());
                method.invoke(target);
                System.out.println("End: "
                        + method.getDeclaringClass().getName()
                        + "."
                        + method.getName());
            } else {
                method.invoke(target);
            }
        } catch (NoSuchMethodException
                 | IllegalAccessException
                 | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
