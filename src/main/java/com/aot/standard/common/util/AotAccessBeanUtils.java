package com.aot.standard.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AotAccessBeanUtils {
    private static AotAccessBeanUtils instance;

    @Autowired
    private ApplicationContext applicationContext;

    private AotAccessBeanUtils() {
        if (instance == null) {
            instance = this;
        }
    }

    public static <T> T getBean(final Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }
}
