package demo.fsl.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import demo.fsl.User;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fsubasi on 05.02.2016.
 * /**
 * Here we see an example of @ConditionalOnMissingBean.
 * This is a pattern used in fsl, where a default bean is defined in the common-fsl-service and
 * any service that wants to override the default behavior creates a bean with the name
 * NAME_OF_THE_DEFAULT_BEAN_IN_COMMON_FSL_SERVICE + 'Override'
 */
@Configuration
public class MyConfig {


    @Bean(name = "userBean")
    @ConditionalOnMissingBean(name = "userBeanOverride")
    public User user(){
        return User.createUser();
    }

    /**
     *
     * If the following bean is commented out,
     * the default bean defined above will be created
     * else this will be the only User bean
     *//*
	@Bean(name = "userBeanOverride")
	public User userOverride(){
		User user = new User();
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmailAddress("janedoe@example.com");
		return user;
	}*/
}
