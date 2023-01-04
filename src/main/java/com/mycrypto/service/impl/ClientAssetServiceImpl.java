package com.mycrypto.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycrypto.http.Asset;
import com.mycrypto.http.Client;
import com.mycrypto.service.ClientAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static com.mycrypto.service.Paths.ASSETS;

@Component
public class ClientAssetServiceImpl implements ClientAssetService {

    private final Client client;
    @Value("${coincap.client.url}")
    private String baseUrl;

    @Autowired
    public ClientAssetServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public ResponseEntity<Asset> getAllAssets() throws JsonProcessingException {
        String requestUrl = buildRequestUrl(ASSETS.path());
        return client.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Asset>() {
        });
    }

    @Override
    public ResponseEntity<Asset> getAssetById(String id) throws JsonProcessingException {
       StringBuilder requestString = new StringBuilder();
       requestString.append(ASSETS.path()).append(id);

       String requestUrl = buildRequestUrl(String.valueOf(requestString));
        return client.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Asset>() {
        });
    }

    private String buildRequestUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl.concat(path)).encode().toUriString();
    }
}
