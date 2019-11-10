package com.aot.standard.context.aop;

import com.aot.standard.context.aop.logging.TraceLoggingInAOP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPContext {

    @Bean(name = "traceLoggingInAOP")
    public TraceLoggingInAOP getLoggingInAOP() {
        return new TraceLoggingInAOP();
    }
}
