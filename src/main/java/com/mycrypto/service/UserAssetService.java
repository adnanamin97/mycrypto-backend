package com.mycrypto.service;

import com.mycrypto.entity.User;
import com.mycrypto.entity.UserAssets;
import com.mycrypto.exceptions.ResourceNotFoundException;
import com.mycrypto.model.request.SaveAsset;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserAssetService {

    Set<UserAssets> getUserAssets(final Long userId) throws ResourceNotFoundException;

    void findOrCreateUser(final List<String> usernameAndTimeStamp);

    void saveUserAsset(SaveAsset asset) throws ResourceNotFoundException;

    void deleteUserAsset(Long userId, String assetId) throws ResourceNotFoundException;
}
