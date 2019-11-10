package com.aot.standard.context.message;

import com.aot.standard.common.util.AotDateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class MessageContext {

    @Bean("messageSource")
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        final ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasenames("/WEB-INF/messages/sbcstcs_msg", "/WEB-INF/messages/sbcstcs_text");
        reloadableResourceBundleMessageSource.setCacheSeconds(5);
        reloadableResourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return reloadableResourceBundleMessageSource;
    }

    @Bean("messageSourceAccessor")
    public MessageSourceAccessor getMessageSourceAccessor() {
        return new MessageSourceAccessor(this.getReloadableResourceBundleMessageSource());
    }

    @Bean
    public SessionLocaleResolver getSessionLocaleResolver() {
        final SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(AotDateUtils.LOCALE);
        return sessionLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}
