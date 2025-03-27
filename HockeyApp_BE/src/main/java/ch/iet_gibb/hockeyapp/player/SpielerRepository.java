package ch.iet_gibb.hockeyapp.player;

import ch.iet_gibb.hockeyapp.team.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface SpielerRepository extends MongoRepository<Spieler, String> {
    List<Spieler> findByTeamId(String teamId);

}
