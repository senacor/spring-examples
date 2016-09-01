
package com.senacor.tecco.ilms.katas.example.e02_errorcontroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;


/**
 * Created by Dr. Michael Menzel, Senacor Technologies AG, 01.09.2016.
 */

@Component
public class ThrowExceptionFilter {
    private static final Log log = LogFactory.getLog(ThrowExceptionFilter.class);

    @Bean
    public FilterRegistrationBean registerExceptionFilter() {
        FilterRegistrationBean register = new FilterRegistrationBean();

        register.setName(this.getClass().getName());
        register.addUrlPatterns("/filter/response/exception");
        register.setFilter(new Filter());
        register.setOrder(1);
        register.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        register.setEnabled(true);

        return register;
    }


    private class Filter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Exception thrown in servlet filter");
        }

    }
}




