package com.senacor.tecco.ilms.katas.e05_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!development")
@Service
public class ProductionService implements ProfileDependentService {

    private final User user;

    public ProductionService(User user) {
        this.user = user;
    }

    @Override
    public String getProfile() {
        return "Production";
    }

    @Override
    public User getUser() {
        return null;
    }

}
