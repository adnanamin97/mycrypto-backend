package com.mycrypto.controller;

import com.auth0.exception.Auth0Exception;
import com.mycrypto.service.UserAssetService;
import com.mycrypto.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserCryptoController {

    private final Logger log = LoggerFactory.getLogger(UserCryptoController.class);
    private final UserAssetService userAssetService;
    private final ControllerUtils controllerUtils;

    @Autowired
    public UserCryptoController(final UserAssetService userAssetService, final ControllerUtils controllerUtils) {
        this.userAssetService = userAssetService;
        this.controllerUtils = controllerUtils;
    }

    @PostMapping("/")
    public void saveUser(@AuthenticationPrincipal final Jwt principal) throws Auth0Exception {

        log.info("POST /");
       final String token = principal.getTokenValue();
       final List<String> usernameAndTimestamp = controllerUtils.getUsernameAndTimeStampFor(token);
       userAssetService.findOrCreateUser(usernameAndTimestamp);
    }


}
