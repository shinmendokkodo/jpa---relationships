package com.shinmen.relationships.onetomany.unidirectional.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinmen.relationships.onetomany.unidirectional.models.Registration;
import com.shinmen.relationships.onetomany.unidirectional.repositories.RegistrationRepository;
import com.shinmen.relationships.onetoone.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    
    private final RegistrationRepository registrationRepository;

    public List<Registration> allRegistrations() {
		return registrationRepository.findAll();	    
	}

	public Registration getRegistration(int id){
		return registrationRepository.findById(id).orElseThrow(() -> new NotFoundException("Registration not found."));
	}
	
    public Registration addRegistration(Registration registration) {
    	registration.setId(0);
		return registrationRepository.save(registration);
	}
    	
 	public void deleteRegistration(int id) {
		registrationRepository.deleteById(id);
	}

}
