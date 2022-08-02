package com.mycrypto.controller;


import com.mycrypto.http.AssetData;


import com.mycrypto.repository.UserRepository;
import com.mycrypto.service.ClientAssetService;
import com.mycrypto.service.UserAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class CryptoController {

    private final Logger log = LoggerFactory.getLogger(CryptoController.class);

    private final UserRepository userRepository;
    private final ClientAssetService client;
    private final UserAssetService userAssetService;

    @Autowired
    public CryptoController(final ClientAssetService client,
                            final UserRepository userRepository,
                            final UserAssetService userAssetService) {
        this.client = client;
        this.userRepository = userRepository;
        this.userAssetService = userAssetService;
    }


    @GetMapping("/assets")
    public ResponseEntity<AssetData> allAssets(@AuthenticationPrincipal final Authentication principal) throws Exception {
        return ResponseEntity.ok(client.getAllAssets());
    }





}
