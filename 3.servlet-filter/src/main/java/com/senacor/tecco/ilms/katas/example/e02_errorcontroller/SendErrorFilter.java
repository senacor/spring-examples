
package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;


/**
 * Created by Dr. Michael Menzel, Senacor Technologies AG, 01.09.2016.
 * <p>
 * Filter that responds with a servlet error
 */

@Component
public class SendErrorFilter {
    private static final Log log = LogFactory.getLog(SendErrorFilter.class);

    @Bean
    public FilterRegistrationBean<Filter> registerSendErrorFilter() {
        FilterRegistrationBean<Filter> register = new FilterRegistrationBean<>();

        register.setName(this.getClass().getName());
        register.addUrlPatterns("/filter/response/error");
        register.setFilter(new Filter());
        register.setOrder(3);
        register.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        register.setEnabled(true);

        return register;
    }


    private static class Filter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "http servlet error");
        }

    }
}




