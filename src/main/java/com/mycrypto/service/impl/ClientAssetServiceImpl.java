package com.mycrypto.service.impl;

import com.mycrypto.http.AssetData;
import com.mycrypto.http.Client;
import com.mycrypto.service.ClientAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientAssetServiceImpl implements ClientAssetService {

    private final Client client;

    @Autowired
    public ClientAssetServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public AssetData getAllAssets() throws Exception {
        return client.getCryptoData("", AssetData.class);
    }
}
