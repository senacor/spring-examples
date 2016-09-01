package com.senacor.tecco.ilms.katas.feign;

import com.senacor.tecco.ilms.katas.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fsubasi on 26.01.2016.
 */
@FeignClient("users-service")
interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    User getUser();
}