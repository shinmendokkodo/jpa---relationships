package com.shinmen.relationships.onetoone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinmen.relationships.onetoone.models.PlayerProfile;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Integer> {

}
