package com.mycrypto.util;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.mycrypto.entity.User;
import com.mycrypto.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ControllerUtils {
    private final Logger log = LoggerFactory.getLogger(ControllerUtils.class);
    private final Map<String, String> map = new HashMap<>();


    private final AuthAPI authAPI;
    private final UserRepository userRepository;

    @Autowired
    public ControllerUtils(final AuthAPI authAPI, final UserRepository userRepository) {
        this.authAPI = authAPI;
        this.userRepository = userRepository;
    }

    public List<String> getUsernameAndTimeStampFor(final String accessToken) throws Auth0Exception {
        Map<String,String> userDetails = validateTokenAndReturnUserDetails(accessToken);
        String username = userDetails.get("name");
        String timestamp = userDetails.get("updated_at");
       return Arrays.asList(username,timestamp);
    }

    public Map<String,String> validateTokenAndReturnUserDetails(final String accessToken) throws Auth0Exception {
        try {
            final Request<UserInfo> info = authAPI.userInfo(accessToken);
            final Map<String, Object> values = info.execute().getValues();
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                map.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            log.info("map {}", map);
            return map;
        } catch(Exception e) {
            log.error("There was an error with the token provided");
            throw new Auth0Exception(e.getMessage());
        }
    }


}
