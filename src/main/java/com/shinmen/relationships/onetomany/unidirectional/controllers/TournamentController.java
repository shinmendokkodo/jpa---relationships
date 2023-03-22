package com.shinmen.relationships.onetomany.unidirectional.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinmen.relationships.onetomany.unidirectional.models.Registration;
import com.shinmen.relationships.onetomany.unidirectional.models.Tournament;
import com.shinmen.relationships.onetomany.unidirectional.services.RegistrationService;
import com.shinmen.relationships.onetomany.unidirectional.services.TournamentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments")
public class TournamentController {

	private final TournamentService service;
	
	private final RegistrationService registrationService;
	
	@GetMapping
	public List<Tournament> allTournaments() {
		return service.allTournaments();	    
	}

	@GetMapping("/{id}")
	public Tournament getTournament(@PathVariable int id){
		return service.getTournament(id);
	}
	
    @PostMapping
	public Tournament addTournament(@RequestBody Tournament tournament) {
    	return service.addTournament(tournament);
	}
    
    @PutMapping("/{id}/registrations/{registration_id}")
    public Tournament addRegistration(@PathVariable int id, @PathVariable int registration_id) {
    	Registration registration = registrationService.getRegistration(registration_id); 
    	return service.addRegistration(id, registration);
    }
		
    @PutMapping("/{id}/remove_registrations/{registration_id}")
    public Tournament removeRegistration(@PathVariable int id, @PathVariable int registration_id) {
    	Registration registration = registrationService.getRegistration(registration_id);
    	return service.removeRegistration(id, registration);
    }

    @DeleteMapping("/{id}")
	public void deleteTournament(@PathVariable int id) {
		service.deleteTournament(id);
	}   
}
