package com.mycrypto.util;

import com.mycrypto.controller.CryptoController;
import com.mycrypto.entity.User;
import com.mycrypto.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControllerUtils {
    private final Logger log = LoggerFactory.getLogger(ControllerUtils.class);

    @Autowired
    private UserRepository userRepository;

    public void updateAssetsByUsername(String username) {
        if(!userRepository.existsByUsername(username)) {
            log.info("User does not exist, making new user {}", username);
            User user = new User();
            user.setUsername(username);

            userRepository.save(user);
            log.info("user created");
        };
    }
}
