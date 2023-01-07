package com.mycrypto.controller;

import com.auth0.exception.Auth0Exception;
import com.mycrypto.entity.UserAssets;
import com.mycrypto.exceptions.ResourceNotFoundException;
import com.mycrypto.model.request.SaveAsset;
import com.mycrypto.service.UserAssetService;
import com.mycrypto.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin("http://localhost:3000")
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
    public ResponseEntity<?> saveUser(@AuthenticationPrincipal Jwt principal) throws Auth0Exception {

        log.info("POST /");
       try {
           final String token = principal.getTokenValue();
           final List<String> usernameAndTimestamp = controllerUtils.getUsernameAndTimeStampFor(token);
           userAssetService.findOrCreateUser(usernameAndTimestamp);
           return new ResponseEntity<>(HttpStatus.OK);
       } catch (Exception e) {
           log.debug("Error encountered {}", e.getMessage());
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/save")
    public void saveAsset(@RequestBody SaveAsset asset) throws ResourceNotFoundException {
        log.info("POST /save");
        log.info("attempting to save asset for {}", asset.getUsername());
        userAssetService.saveUserAsset(asset);
        log.info("saved asset");

    }

    @GetMapping("/wallet/{id}")
    public Set<UserAssets> getAssets(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return userAssetService.getUserAssets(userId);
    }

    @DeleteMapping("/wallet/{user-id}/{asset-id}")
    public void deleteAsset(@PathVariable(value = "user-id") Long userId,
                            @PathVariable(value = "asset-id") String assetId) throws ResourceNotFoundException {
        userAssetService.deleteUserAsset(userId, assetId);
    }



}
