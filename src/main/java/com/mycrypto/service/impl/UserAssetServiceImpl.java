package com.mycrypto.service.impl;

import com.mycrypto.entity.User;
import com.mycrypto.entity.UserAssets;
import com.mycrypto.exceptions.ResourceNotFoundException;
import com.mycrypto.model.request.SaveAsset;
import com.mycrypto.repository.AssetsRepository;
import com.mycrypto.repository.UserRepository;
import com.mycrypto.service.UserAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserAssetServiceImpl implements UserAssetService {
    private final Logger log = LoggerFactory.getLogger(UserAssetServiceImpl.class);

    private final UserRepository userRepository;
    private final AssetsRepository assetsRepository;


    public UserAssetServiceImpl(final UserRepository userRepository, AssetsRepository assetsRepository) {
        this.userRepository = userRepository;
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Set<UserAssets> getUserAssets(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id.toString(), ""));

        return assetsRepository.findAllByUser(user);
    }

    @Override
    public void findOrCreateUser (final List<String> usernameAndTimestamp) {
        final String username = usernameAndTimestamp.get(0);
        final String timestamp = usernameAndTimestamp.get(1);
        try {
            String modifiedTimestamp = timestamp.replace('T', ' ');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

            if (!userRepository.existsByUsername(username)) {
                createUser(username, LocalDateTime.parse(modifiedTimestamp, formatter));
                log.info("user created:{} , at {}  ", username, modifiedTimestamp);
            } else {
                log.info("user {} exists in database with id: {} ", username, userRepository.findUserByUsername(username).get().getId());
            }
            }
        catch (RuntimeException e) {
            log.error("We were unable to find or save user {}, ", username);
        }
    }

    @Override
    public void saveUserAsset(SaveAsset assetRequest) throws ResourceNotFoundException {

        // find user
        User user = userRepository.findUserByUsername(assetRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user", assetRequest.getUsername(), ""));

        // Find Asset - orElse Add Asset
        Set<UserAssets> userAssetsByUser = assetsRepository.findAllByUser(user);

        Optional<UserAssets> existingAsset = userAssetsByUser.stream()
                .filter(asset -> assetRequest.getAsset_id().equals(asset.getAssetId()))
                .findFirst();

        if (!userAssetsByUser.isEmpty()) {
            if (existingAsset.isPresent()) {
                log.info("updating asset {} with quantity {}", existingAsset.get().getAssetId(), assetRequest.getQuantity());
                updateAsset(existingAsset.get(), assetRequest);

            } else {
                log.info("assets found, creating new asset {} with quantity {}", assetRequest.getAsset_id(), assetRequest.getQuantity());
                createAsset(assetRequest, user);
            }

        } else {
            log.info("empty assets, creating new asset {} with quantity {}", assetRequest.getAsset_id(), assetRequest.getQuantity());
            createAsset(assetRequest, user);

        }

    }

    @Override
    public void deleteUserAsset(Long userId, String assetId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId.toString(), ""));

        UserAssets assetToDelete = assetsRepository.findAllByUser(user).stream()
                .filter(asset -> asset.getAssetId().equals(assetId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("asset", assetId, ""));

        assetsRepository.delete(assetToDelete);
    }

    private void updateAsset(UserAssets existingAsset, SaveAsset assetRequest) {
        existingAsset.setQuantity(assetRequest.getQuantity());
        existingAsset.setDatePurchased(LocalDateTime.now());
        assetsRepository.save(existingAsset);
    }

    private void createAsset(SaveAsset assetRequest, User user) {
        UserAssets newUserAssets = new UserAssets();
        newUserAssets.setAssetId(assetRequest.getAsset_id());
        newUserAssets.setQuantity(assetRequest.getQuantity());
        newUserAssets.setUser(user);
        newUserAssets.setDatePurchased(LocalDateTime.now());
        assetsRepository.save(newUserAssets);
    }

    private void createUser(String username, LocalDateTime registrationDate) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setTimeStamp(registrationDate);
        userRepository.save(newUser);

    }

}

