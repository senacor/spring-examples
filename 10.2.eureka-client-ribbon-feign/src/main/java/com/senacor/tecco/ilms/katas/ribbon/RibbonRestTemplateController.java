package com.senacor.tecco.ilms.katas.ribbon;

import com.senacor.tecco.ilms.katas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * See https://spring.io/guides/gs/client-side-load-balancing/
 */

@RestController
public class RibbonRestTemplateController {

    // We are using registered service name instead of real host name
    private final String USERS_SERVICE_URL = "http://USERS-SERVICE";

    @Bean
    // @Qualifier to mark the RestTemplate bean to use a LoadBalancerClient
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * The RestTemplate has been auto-configured by Spring Cloud to use a custom
     * ClientHttpRequest that uses Netflix Ribbon to do the micro-service lookup
     * Can find services with their service ID in eureka server
     * instead of the real hostname
     * Also has client-side load balancing capabilities, it can pick one instance if there are multiple
     * instances of a service.
     * below is the code excerpt from RibbonClientHttpRequestFactory class
     * <code>
       String serviceId = originalUri.getHost();
       ... check if serviceId is null ...
       ServiceInstance instance = loadBalancer.choose(serviceId);
       ... check if instance null ...
       URI uri = loadBalancer.reconstructURI(instance, originalUri);
       </code>
     */
    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping("/ribbon/getUserFromUsersService/")
    public User getUserFromUsersService(){
        User user = restTemplate.getForObject(USERS_SERVICE_URL + "/user/",
                User.class);
        return user;
    }
}
