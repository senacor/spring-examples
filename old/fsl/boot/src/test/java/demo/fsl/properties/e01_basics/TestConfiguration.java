package demo.fsl.properties.e01_basics;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by fsubasi on 14.01.2016.
 */
@Configuration
@PropertySource("classpath:other.properties")
@PropertySource("classpath:${config.directory}/app.properties")
public class TestConfiguration {
}
