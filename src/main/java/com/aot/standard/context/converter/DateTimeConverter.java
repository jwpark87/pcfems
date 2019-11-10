package com.aot.standard.context.converter;

import com.aot.standard.common.util.AotDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeConverter implements Converter<String, DateTime> {
    @Override
    public DateTime convert(final String arg0) {
        if (StringUtils.isNotEmpty(arg0)) {
            try {
                // 1. long값(timestamp)
                return AotDateUtils.getDateTime(Long.parseLong(arg0));
            } catch (final Exception e) {
                try {
                    // 2. yyyy-MM-dd
                    return AotDateUtils.getDateTime(arg0, AotDateUtils.YYYY_MM_DD);
                } catch (final Exception e2) {
                    try {
                        // 3. yyyyMMdd
                        return AotDateUtils.getDateTime(arg0, AotDateUtils.YYYYMMDD);
                    } catch (final Exception e3) {
                        try {
                            // 4. yyyyMMddHHmmss
                            return AotDateUtils.getDateTime(arg0, AotDateUtils.YYYYMMDDHHMMSS);
                        } catch (final Exception e4) {
                            return null;
                        }
                    }
                }
            }
        } else {
            return null;
        }
    }
}
