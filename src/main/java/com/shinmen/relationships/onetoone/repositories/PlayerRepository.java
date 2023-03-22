package com.shinmen.relationships.onetoone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinmen.relationships.onetoone.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    
}
