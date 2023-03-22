package com.shinmen.relationships.onetoone.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinmen.relationships.onetoone.exceptions.NotFoundException;
import com.shinmen.relationships.onetoone.models.PlayerProfile;
import com.shinmen.relationships.onetoone.repositories.PlayerProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerProfileService {

    private final PlayerProfileRepository playerProfileRepository;
    
    public List<PlayerProfile> allPlayerProfiles() {
		return playerProfileRepository.findAll();	    
	}
	
	public PlayerProfile getPlayerProfile(int id) throws NotFoundException{
		return playerProfileRepository.findById(id).orElseThrow(() -> new NotFoundException("Player profile not found"));
	}	
    
	public PlayerProfile addPlayerProfile(PlayerProfile profile) {
		profile.setId(0);
		return playerProfileRepository.save(profile);
	}    
    
	public void deletePlayerProfile(int id) {
		playerProfileRepository.deleteById(id);
	}

}
