package com.mycrypto.repository;

import com.mycrypto.entity.UserAssets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetsRepository extends JpaRepository<UserAssets, Long> {
}
