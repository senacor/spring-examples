package com.senacor.tecco.ilms.katas.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by fsubasi on 18.02.2016.
 * This class is not part of the examples. By default Spring removes semicolons in the urls.
 * This behavior must be disabled so that @MatrixVariable's can be parsed.
 */
@Component
public class MyPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof RequestMappingHandlerMapping){
            ((RequestMappingHandlerMapping) bean).setRemoveSemicolonContent(false);
        }
        return bean;
    }
}
