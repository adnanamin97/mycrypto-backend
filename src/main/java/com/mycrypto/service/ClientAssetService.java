package com.mycrypto.service;


import com.mycrypto.http.AssetData;
import org.springframework.http.ResponseEntity;

public interface ClientAssetService {

    AssetData getAllAssets() throws Exception;
}
