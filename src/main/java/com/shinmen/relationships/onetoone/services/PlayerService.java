package com.shinmen.relationships.onetoone.services;

import java.util.List;

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
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player not found"));
    }

    public Player addPlayer(Player player) {
        player.setId(0);
        return playerRepository.save(player);
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    public Player assignProfile(int id, PlayerProfile profile) throws NotFoundException {
        Player player = playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player not found"));
        player.setPlayerProfile(profile);
        return playerRepository.save(player);
    }

}
