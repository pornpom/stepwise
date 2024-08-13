package com.stepwise.stepwise.repository;

import com.stepwise.stepwise.entity.ConfUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfUserRepository extends JpaRepository<ConfUserEntity, Integer> {

    ConfUserEntity findFirstByUserUsernameAndStatus(String username , String status);

    Optional<ConfUserEntity> findByUserUsernameAndStatus(String username, String status);

}