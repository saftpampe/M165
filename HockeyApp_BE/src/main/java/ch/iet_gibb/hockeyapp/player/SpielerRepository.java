package ch.iet_gibb.hockeyapp.player;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpielerRepository extends MongoRepository<Spieler, String> {
}
