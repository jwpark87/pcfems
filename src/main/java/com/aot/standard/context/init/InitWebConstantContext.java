package com.aot.standard.context.init;

import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.exception.CommonExceptionCode;
import com.aot.standard.common.util.AotDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.util.TimeZone;

@Configuration
public class InitWebConstantContext {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String contextPath = null;

    @Autowired(required = false)
    public void setConstant(final ServletContext servletContext) throws IllegalArgumentException, IllegalAccessException {
        this.setContextPath(servletContext);

        final Field[] fields = CommonCode.class.getDeclaredFields();
        for (final Field field : fields) {
            // this.logger.info("servletContext.setAttribute(\"{}\", \"{}\");", field.getName(), field.get(field.getName()));
            servletContext.setAttribute(field.getName(), field.get(field.getName()));
        }
        this.logger.info("CommonCode - Complete");

        final CommonExceptionCode[] values = CommonExceptionCode.values();
        for (final CommonExceptionCode value : values) {
            // this.logger.info("servletContext.setAttribute(\"{}\", \"{}\");", value.name(), value.getCode());
            servletContext.setAttribute(value.name(), value.getCode());
        }
        this.logger.info("CommonResultCode - Complete");

        DateTimeZone.setDefault(AotDateUtils.TIME_ZONE);
        TimeZone.setDefault(AotDateUtils.TIME_ZONE.toTimeZone());
        servletContext.setAttribute("TIME_ZONE_ASIA_SEOUL", AotDateUtils.TIME_ZONE.getID());
        servletContext.setAttribute("LOCALE_KOREAN", AotDateUtils.LOCALE.toString());
        this.logger.info("DateTimeZone/TimeZone.setDefault(\"{}\"); - Complete", AotDateUtils.TIME_ZONE.getID());
    }

    private void setContextPath(final ServletContext servletContext) {
        if (contextPath == null) {
            String tempContextPath = servletContext.getContextPath();
            if (StringUtils.equals(tempContextPath, "/")) {
                tempContextPath = "";
            }
            if (StringUtils.isNotEmpty(tempContextPath) && StringUtils.endsWith(tempContextPath, "/")) {
                tempContextPath = StringUtils.removeEnd(tempContextPath, "/");
            }
            contextPath = tempContextPath;
        }
        servletContext.setAttribute("CONTEXT_PATH", contextPath);
        this.logger.info("servletContext.setAttribute(\"CONTEXT_PATH\", \"{}\");", contextPath);
    }

    public static String getContextPath() {
        return contextPath;
    }
}
