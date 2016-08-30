package demo.intern.mvc.e03_errorhandling;

import demo.intern.mvc.e03_errorhandling.e04_errorattributes.MyErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by fsubasi on 12.01.2016.
 */
@Configuration
@ComponentScan
public class ErrorHandlingConfig {

    @Bean
    public ErrorAttributes myErrorAttributes(){
        return new MyErrorAttributes();
    }

    // Adding a customized error page for '400 - Bad Request'
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        // Lambda
        // return container -> container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
            }
        };
    }
}
