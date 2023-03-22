package com.shinmen.relationships.onetoone.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinmen.relationships.onetoone.exceptions.NotFoundException;
import com.shinmen.relationships.onetoone.models.Player;
import com.shinmen.relationships.onetoone.models.PlayerProfile;
import com.shinmen.relationships.onetoone.services.PlayerProfileService;
import com.shinmen.relationships.onetoone.services.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerProfileService playerProfileService;

    @GetMapping
	public List<Player> allPlayers() {
		return playerService.allPlayers();	    
	}

	@GetMapping("/{id}")
	public Player getPlayer(@PathVariable int id) throws NotFoundException{
		return playerService.getPlayer(id);
	}

    @PostMapping
	public Player addPlayer(@RequestBody Player player) {
    	return playerService.addPlayer(player);
	}
    
 	@DeleteMapping("/{id}")
	public void deletePlayer(@PathVariable int id) {
		playerService.deletePlayer(id);
	}

    @PutMapping("/{id}/profiles/{profile_id}")
    public Player assignDetail(@PathVariable int id, @PathVariable int profile_id) throws NotFoundException {
    	PlayerProfile profile = playerProfileService.getPlayerProfile(profile_id); 
    	System.out.println(profile);
    	return playerService.assignProfile(id, profile);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    
}
