package ch.iet_gibb.hockeyapp.team;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findByName(String name); // Suche nach Team basierend auf dem Namen

}
