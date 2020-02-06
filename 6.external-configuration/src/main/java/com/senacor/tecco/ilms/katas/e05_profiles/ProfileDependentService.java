package com.senacor.tecco.ilms.katas.e05_profiles;

import com.senacor.tecco.ilms.katas.e02_configurationproperties.User;

public interface ProfileDependentService {

    User getUser();

    String getProfile();

}
