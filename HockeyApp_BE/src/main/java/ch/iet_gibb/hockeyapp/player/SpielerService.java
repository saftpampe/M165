
package ch.iet_gibb.hockeyapp.player;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpielerService {
    private final SpielerRepository spielerRepository;

    @Autowired
    public SpielerService(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
    }

    public List<SpielerResponseDTO> findAll() {
        return spielerRepository.findAll().stream().map(SpielerMapper::toDTO).toList();
    }

    public SpielerResponseDTO findById(String id) {
        return SpielerMapper.toDTO(spielerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spieler not found")));
    }

    public void deleteById(String id) {
        spielerRepository.deleteById(id);
    }

    public SpielerResponseDTO insert(SpielerRequestDTO spielerRequestDTO) {
        Spieler spieler = SpielerMapper.fromDTO(spielerRequestDTO);
        return SpielerMapper.toDTO(spielerRepository.save(spieler));
    }

    public SpielerResponseDTO update(SpielerRequestDTO spielerRequestDTO, String id) {
        return spielerRepository.findById(id).map(existing -> {
            // Allgemeine Felder
            if (spielerRequestDTO.getVorname() != null) existing.setVorname(spielerRequestDTO.getVorname());
            if (spielerRequestDTO.getNachname() != null) existing.setNachname(spielerRequestDTO.getNachname());
            if (spielerRequestDTO.getPosition() != null) existing.setPosition(spielerRequestDTO.getPosition());
            if (spielerRequestDTO.getGeburtsdatum() != null) existing.setGeburtsdatum(spielerRequestDTO.getGeburtsdatum());
            if (spielerRequestDTO.getNationalitaet() != null) existing.setNationalitaet(spielerRequestDTO.getNationalitaet());
            if (spielerRequestDTO.getUnterVertragBis() != null) existing.setUnterVertragBis(spielerRequestDTO.getUnterVertragBis());
            if (spielerRequestDTO.getMarktwert() != null) existing.setMarktwert(spielerRequestDTO.getMarktwert());
            if (spielerRequestDTO.getNummer() != null) existing.setNummer(spielerRequestDTO.getNummer());
            if (spielerRequestDTO.getTeamId() != null) existing.setTeamId(spielerRequestDTO.getTeamId());

            // Wenn der Spieler ein Goalie ist und der Request ebenfalls (type=GOALIE)
            if (existing instanceof Goalie goalie && spielerRequestDTO.getType() == PlayerType.GOALIE) {
                if (spielerRequestDTO.getSaves() != null) goalie.setSaves(spielerRequestDTO.getSaves());
                if (spielerRequestDTO.getSavePercentage() != null) goalie.setSavePercentage(spielerRequestDTO.getSavePercentage());
            }
            // Wenn der Spieler ein Field-Spieler ist und der Request ebenfalls (type=FIELD)
            else if (existing instanceof Feldspieler feldspieler && spielerRequestDTO.getType() == PlayerType.FIELD) {
                if (spielerRequestDTO.getPunkte() != null) feldspieler.setPunkte(spielerRequestDTO.getPunkte());
            }

            existing = spielerRepository.save(existing);
            return SpielerMapper.toDTO(existing);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spieler not found"));
    }


    public List<SpielerResponseDTO> findByTeamId(String team_Id) {
        return spielerRepository.findByTeamId(team_Id).stream().map(SpielerMapper::toDTO).toList();
    }
}

