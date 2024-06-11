package by.itacademy.flatservice.config.properites.profling;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


@Log4j2
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);

        if (beanClass != null && !Proxy.isProxyClass(bean.getClass())) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                log.info("PROFILING...");
                long startTime = System.nanoTime();
                Object retValue = method.invoke(bean, args);
                long endTime = System.nanoTime();
                log.info("EXECUTION TIME : " + (endTime - startTime));
                log.info("PROFILING COMPLETED");
                return retValue;
            });
        }
        return bean;
    }
}
