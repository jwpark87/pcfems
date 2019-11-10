package com.aot.standard.common.util;

import com.aot.standard.context.message.MessageContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class AotMessageUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AotMessageUtils.class);

    protected AotMessageUtils() {
        throw new UnsupportedOperationException();
    }

    @Autowired
    private static ReloadableResourceBundleMessageSource messageSourceAccessor = AotAccessBeanUtils.getBean(MessageContext.class).getReloadableResourceBundleMessageSource();

    public static String getMessage(final String code) {
        return messageSourceAccessor.getMessage(code, null, AotDateUtils.LOCALE);
    }

    public static String getMessage(final String code, final String defaultMessage) {
        return messageSourceAccessor.getMessage(code, null, defaultMessage, AotDateUtils.LOCALE);
    }

    public static String getMessage(final String code, final String[] args) {
        return messageSourceAccessor.getMessage(code, args, AotDateUtils.LOCALE);
    }

    public static String getMessage(final String code, final String[] args, final Locale locale) {
        return messageSourceAccessor.getMessage(code, args, locale);
    }

    public static String getProperty(final String key) {
        try (InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream("/properties/environment.properties")) {
            final Properties prop = new Properties();
            prop.load(inputStream);
            return prop.getProperty(key);
        } catch (final IOException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            return "";
        }
    }

}
