package com.mycrypto.service.impl;

import com.mycrypto.entity.User;
import com.mycrypto.repository.UserRepository;
import com.mycrypto.service.UserAssetService;
import com.mycrypto.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Component
public class UserAssetServiceImpl implements UserAssetService {
    private final Logger log = LoggerFactory.getLogger(UserAssetServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserAssets(String username) {
        if(!userRepository.existsByUsername(username)) {
            log.info("User does not exist, making new user {}", username);
            User user = new User();
            user.setUsername(username);

            userRepository.save(user);
            log.info("user created");
        }

        return userRepository.findUserByUsername(username);
    }
}
