package ch.iet_gibb.hockeyapp.game;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpielRepository extends MongoRepository<Spiel, String> {
}
