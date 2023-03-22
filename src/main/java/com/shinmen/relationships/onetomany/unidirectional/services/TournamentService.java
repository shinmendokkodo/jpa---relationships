package com.shinmen.relationships.onetomany.unidirectional.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinmen.relationships.onetomany.unidirectional.models.Registration;
import com.shinmen.relationships.onetomany.unidirectional.models.Tournament;
import com.shinmen.relationships.onetomany.unidirectional.repositories.TournamentRepository;
import com.shinmen.relationships.onetoone.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public List<Tournament> allTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(int id) throws NotFoundException {
        return tournamentRepository.findById(id).orElseThrow(() -> new NotFoundException("Tournament not found."));
    }

    public Tournament addTournament(Tournament tournament) {
        tournament.setId(0);
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(int id) {
        tournamentRepository.deleteById(id);
    }

    public Tournament addRegistration(int id, Registration registration) {
        Tournament tournament = getTournament(id);
        tournament.addRegistration(registration);
        return tournamentRepository.save(tournament);
    }

    public Tournament removeRegistration(int id, Registration registration) {
        Tournament tournament = getTournament(id);
        tournament.removeRegistration(registration);
        return tournamentRepository.save(tournament);
    }
}
