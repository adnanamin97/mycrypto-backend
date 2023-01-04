package com.mycrypto.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycrypto.http.Asset;
import org.springframework.http.ResponseEntity;


public interface ClientAssetService {

   ResponseEntity<Asset> getAllAssets() throws Exception;
    ResponseEntity<Asset> getAssetById(String id) throws JsonProcessingException;
}
