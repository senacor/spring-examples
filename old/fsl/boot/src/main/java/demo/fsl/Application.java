package demo.fsl;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@EnableAutoConfiguration // Spring boot auto configuration
@Configuration // Indicates a class that declares one or more beans
@ComponentScan(   // configures component scanning
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(Configuration.class) // only create beans from classes annotated with @Configuration
)
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); // throw a NoHandlerFoundException if no Handler is found
	}
}
