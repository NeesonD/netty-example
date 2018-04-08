package com.neeson.netty.scanner;

import com.neeson.netty.core.annotion.SocketCommand;
import com.neeson.netty.core.annotion.SocketModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: neeson
 * Date: 2018/4/8
 * Time: 21:43
 * Description:
 */
@Component
public class HandlerScanner implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null && interfaces.length >0){
            for (Class<?> anInterface : interfaces) {
                SocketModule socketModule = anInterface.getAnnotation(SocketModule.class);
                if (socketModule == null){
                    continue;
                }

                Method[] methods = anInterface.getMethods();
                if (methods != null && methods.length > 0){
                    for (Method method : methods) {
                        SocketCommand socketCommand = method.getAnnotation(SocketCommand.class);
                        if (socketCommand == null){
                            continue;
                        }

                        final short module = socketModule.module();
                        final short cmd = socketCommand.cmd();

                        if (InvokerHodler.getInvoker(module,cmd) ==null){
                            InvokerHodler.addInvoker(module,cmd,Invoker.valueOf(method,bean));
                        }

                    }
                }
            }
        }
        return bean;
    }
}
