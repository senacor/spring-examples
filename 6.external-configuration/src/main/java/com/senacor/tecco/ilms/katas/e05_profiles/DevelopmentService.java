package com.senacor.tecco.ilms.katas.e05_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class DevelopmentService implements ProfileDependentService{

    private final User user;

    public DevelopmentService(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public String getProfile() {
        return "development";
    }

}
