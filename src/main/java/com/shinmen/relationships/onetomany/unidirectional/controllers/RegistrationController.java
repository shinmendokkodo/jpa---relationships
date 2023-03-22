package com.shinmen.relationships.onetomany.unidirectional.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinmen.relationships.onetomany.unidirectional.models.Registration;
import com.shinmen.relationships.onetomany.unidirectional.services.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {
    
    private final RegistrationService registrationService;

    @GetMapping
	public List<Registration> allRegistrations() {
		return registrationService.allRegistrations();	    
	}

	@GetMapping("/{id}")
	public Registration getRegistration(@PathVariable int id){
		return registrationService.getRegistration(id);
	}
	
    @PostMapping
	public Registration addRegistration(@RequestBody Registration registration) {
    	return registrationService.addRegistration(registration);
	}
    
 	@DeleteMapping("/{id}")
	public void deleteRegistration(@PathVariable int id) {
		registrationService.deleteRegistration(id);
	}

}
