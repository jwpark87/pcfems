package com.aot.standard.context.viewresolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class ResourceBundleViewContext {

    @Bean(name = "resourceBundleViewResolver")
    public ResourceBundleViewResolver getResourceBundleViewResolver() {
        final ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setOrder(0);
        resourceBundleViewResolver.setBasename("properties/views");

        return resourceBundleViewResolver;
    }
}
