
package com.exercise;
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
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean register = new FilterRegistrationBean();
        Collection<String> urlPatterns = new ArrayList<>();
        register.setName(this.getClass().getName());
        urlPatterns.add("*");
        register.setUrlPatterns(urlPatterns);
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
            System.out.println("The request is : "+ request.getRequestURL()+ request.getMethod());

            filterChain.doFilter(request, response);
            System.out.println("The response is : "+ response.getStatus());
            System.out.println("-----------------------------------------------------");
            response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");

        }

    }
}




