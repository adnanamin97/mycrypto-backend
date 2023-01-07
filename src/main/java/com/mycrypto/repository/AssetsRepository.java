package com.mycrypto.repository;

import com.mycrypto.entity.User;
import com.mycrypto.entity.UserAssets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AssetsRepository extends JpaRepository<UserAssets, Long> {


    Set<UserAssets> findAllByUser(User user);
}
