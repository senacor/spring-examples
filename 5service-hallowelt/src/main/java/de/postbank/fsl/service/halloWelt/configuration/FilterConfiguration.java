package de.postbank.fsl.service.halloWelt.configuration;

import de.postbank.fsl.common.components.filter.FslFilterUrlPattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dgey on 04.09.15.
 */
@Configuration
public class FilterConfiguration {

    @Bean
    FslFilterUrlPattern filterUrlPattern() {
        return new FslFilterUrlPattern("/hallo*/");
    }

}
