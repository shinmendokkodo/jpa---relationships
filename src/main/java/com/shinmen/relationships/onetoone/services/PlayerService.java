package com.shinmen.relationships.onetoone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shinmen.relationships.onetoone.exceptions.NotFoundException;
import com.shinmen.relationships.onetoone.models.Player;
import com.shinmen.relationships.onetoone.models.PlayerProfile;
import com.shinmen.relationships.onetoone.repositories.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> allPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayer(int id) throws NotFoundException {
        Optional<Player> optionalPlayer = playerRepository.findById(id);

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            throw new NotFoundException("Player not found");
        }
    }

    public Player addPlayer(Player player) {
        player.setId(0);

        // Check if player contains nested profile
        if (player.getPlayerProfile() != null) {
            // Ensure the profile is not already associated with another player
            if (player.getPlayerProfile().getPlayer() != null) {
                throw new IllegalStateException("The profile is already associated with a player");
            }

            // Set the bidirectional relationship
            player.getPlayerProfile().setPlayer(player);
        }

        return playerRepository.save(player);
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    public Player assignProfile(int id, PlayerProfile profile) throws NotFoundException {
        Player player = getPlayer(id); // Reuse the getPlayer method to fetch the player

        if (profile.getPlayer() != null) {
            throw new IllegalStateException("The profile is already associated with a player");
        }

        player.setPlayerProfile(profile);
        // Bi-directional relationship
        profile.setPlayer(player);

        return playerRepository.save(player);
    }

}
