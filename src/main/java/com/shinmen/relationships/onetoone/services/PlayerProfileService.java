package com.shinmen.relationships.onetoone.services;

import java.util.List;
import java.util.Optional;

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
		// Retrieve all PlayerProfile objects from the repository
		return playerProfileRepository.findAll();
	}

	public PlayerProfile getPlayerProfile(int id) throws NotFoundException {
		Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);
	
		if (optionalPlayerProfile.isPresent()) {
			return optionalPlayerProfile.get();
		} else {
			throw new NotFoundException("Player profile not found");
		}
	}

	public PlayerProfile addPlayerProfile(PlayerProfile playerProfile) {
		// Reset the ID to ensure a new object is created
		playerProfile.setId(0);
	
		// Check if profile contains nested player
		if (playerProfile.getPlayer() != null) {
			// Ensure the player is not already associated with another profile
			if (playerProfile.getPlayer().getPlayerProfile() != null) {
				throw new IllegalStateException("The player is already associated with a player profile");
			}
	
			// Set the bidirectional relationship
			playerProfile.getPlayer().setPlayerProfile(playerProfile);
		}
	
		return playerProfileRepository.save(playerProfile);
	}	

	public void deletePlayerProfile(int id) throws NotFoundException {
        Optional<PlayerProfile> optionalPlayerProfile = playerProfileRepository.findById(id);

        if (optionalPlayerProfile.isPresent()) {
            PlayerProfile playerProfile = optionalPlayerProfile.get();
            if (playerProfile.getPlayer() != null) {
                playerProfile.getPlayer().setPlayerProfile(null);
            }
            playerProfile.setPlayer(null);
            playerProfileRepository.delete(playerProfile);
        } else {
            throw new NotFoundException("Player profile not found");
        }
    }

}
