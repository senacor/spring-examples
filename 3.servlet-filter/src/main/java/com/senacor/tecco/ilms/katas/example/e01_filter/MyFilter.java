
package com.senacor.tecco.ilms.katas.example.e01_filter;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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
 * Created by amishra on 06/04/16.
 */

@Component
public class MyFilter {
    private static final Log log = LogFactory.getLog(MyFilter.class);

    @Bean
    public FilterRegistrationBean registerMyFilter() {
        FilterRegistrationBean register = new FilterRegistrationBean();

        register.setName(this.getClass().getName());
        register.addUrlPatterns("/filter/response/*");
        register.setFilter(new Filter());
        register.setOrder(1);
        register.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        register.setEnabled(true);

        return register;
    }

    private class Filter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            log.debug("now filtering");
            System.out.println("The request is : " + request.getMethod() + " " + request.getRequestURL());

            filterChain.doFilter(request, response);

            System.out.println("The response status is : "+ response.getStatus());
        }

    }
}




