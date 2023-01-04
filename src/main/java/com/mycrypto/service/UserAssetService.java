package com.mycrypto.service;

import com.mycrypto.entity.User;

import java.util.List;
import java.util.Map;

public interface UserAssetService {

    User getUserAssets(final String username);

    void findOrCreateUser(final List<String> usernameAndTimeStamp);

}
