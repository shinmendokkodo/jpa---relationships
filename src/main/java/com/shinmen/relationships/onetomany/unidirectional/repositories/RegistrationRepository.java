package com.shinmen.relationships.onetomany.unidirectional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shinmen.relationships.onetomany.unidirectional.models.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    
}
