package com.shinmen.relationships.onetoone.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinmen.relationships.onetoone.exceptions.NotFoundException;
import com.shinmen.relationships.onetoone.models.PlayerProfile;
import com.shinmen.relationships.onetoone.services.PlayerProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class PlayerProfileController {

	private final PlayerProfileService playerProfileService;

	@GetMapping
	public List<PlayerProfile> allPlayerProfiles() {
		return playerProfileService.allPlayerProfiles();
	}

	@GetMapping("/{id}")
	public PlayerProfile getPlayerProfile(@PathVariable int id) throws NotFoundException {
		return playerProfileService.getPlayerProfile(id);
	}

	@PostMapping
	public PlayerProfile addPlayerProfile(@RequestBody PlayerProfile playerProfile) {
		return playerProfileService.addPlayerProfile(playerProfile);
	}

	@DeleteMapping("/{id}")
    public void deletePlayerProfile(@PathVariable int id) throws NotFoundException {
        playerProfileService.deletePlayerProfile(id);
    }

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

}
