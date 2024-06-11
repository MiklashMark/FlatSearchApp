package by.itacademy.flatservice.config.properites.profling;

import org.hibernate.event.spi.RefreshContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@Component
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String n : beanNames) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(n);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                try {
                    Class<?> originalClass = Class.forName(beanClassName);
                    Method[] methods = originalClass.getMethods();
                    for (Method m : methods) {
                        if (m.isAnnotationPresent(PostProxy.class)) {
                            Object bean = applicationContext.getBean(n);
                            Method method = bean.getClass().getMethod(m.getName(), m.getParameterTypes());
                            method.invoke(bean);
                        }
                    }
                } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

