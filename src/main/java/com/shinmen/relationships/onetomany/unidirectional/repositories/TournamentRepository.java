package com.shinmen.relationships.onetomany.unidirectional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinmen.relationships.onetomany.unidirectional.models.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    
}
