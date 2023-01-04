package com.mycrypto.service.impl;

import com.mycrypto.entity.User;
import com.mycrypto.repository.UserRepository;
import com.mycrypto.service.UserAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class UserAssetServiceImpl implements UserAssetService {
    private final Logger log = LoggerFactory.getLogger(UserAssetServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserAssetServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserAssets(String username) {
      // TODO: method to get user assets and check for null values
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void findOrCreateUser (final List<String> usernameAndTimestamp) {
        final String username = usernameAndTimestamp.get(0);
        final String timestamp = usernameAndTimestamp.get(1);
        try {
            String modifiedTimestamp = timestamp.replace('T', ' ');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
            if (!userRepository.existsByUsername(username)) {
                log.info("User does not exist, making new user {}", username);

                final User user = new User();
                user.setUsername(username);
                user.setTimeStamp(LocalDateTime.parse(modifiedTimestamp, formatter));
                userRepository.save(user);
                log.info("user created:{} , at {}  ", username, modifiedTimestamp);
            }

            log.info("user {} exists in database with id: {} ", username, userRepository.findUserByUsername(username).getId());
        } catch (RuntimeException e){
            log.error("We were unable to find or save user {}, ", username);
        }
    }


}
