package com.mycrypto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycrypto.model.response.Asset;
import com.mycrypto.service.ClientAssetService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientCryptoController {

    private final Logger log = LoggerFactory.getLogger(ClientCryptoController.class);
    private final ClientAssetService client;

    @Autowired
    public ClientCryptoController(final ClientAssetService client) {
        this.client = client;
    }


    @GetMapping("/assets")
    public ResponseEntity<Asset> getAllAssets(@AuthenticationPrincipal Jwt principal) throws Exception {
        log.info("GET /assets");
        return client.getAllAssets();
    }

    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") final String assetId) throws JsonProcessingException {
        log.info("GET /assets/{}", assetId);
        return client.getAssetById(assetId);
    }

}
