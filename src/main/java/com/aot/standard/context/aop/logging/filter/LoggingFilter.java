package com.aot.standard.context.aop.logging.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LoggingFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(final ILoggingEvent event) {
        return FilterReply.ACCEPT;
    }
}
