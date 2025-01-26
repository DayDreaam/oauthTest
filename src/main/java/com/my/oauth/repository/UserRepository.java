package com.my.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.oauth.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserId(String userId);
}
