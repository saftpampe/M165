
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
        Spieler existing = spielerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spieler not found"));
        Spieler changing = SpielerMapper.fromDTO(spielerRequestDTO);
        mergeSpieler(existing, changing);

        return SpielerMapper.toDTO(spielerRepository.save(existing));
    }

    public List<SpielerResponseDTO> findByTeamId(String team_Id) {
        return spielerRepository.findByTeamId(team_Id).stream().map(SpielerMapper::toDTO).toList();
    }

    private void mergeSpieler(Spieler existing, Spieler changing) {
        if (StringUtils.isNotBlank(changing.getVorname())) {
            existing.setVorname(changing.getVorname());
        }
        if (StringUtils.isNotBlank(changing.getNachname())) {
            existing.setNachname(changing.getNachname());
        }
        if (StringUtils.isNotBlank(changing.getPosition())) {
            existing.setPosition(changing.getPosition());
        }
        if (StringUtils.isNotBlank(changing.getGeburtsdatum())) {
            existing.setGeburtsdatum(changing.getGeburtsdatum());
        }
        if (StringUtils.isNotBlank(changing.getNationalitaet())) {
            existing.setNationalitaet(changing.getNationalitaet());
        }
        if (StringUtils.isNotBlank(changing.getUnterVertragBis())) {
            existing.setUnterVertragBis(changing.getUnterVertragBis());
        }
        if (changing.getSaves() != null) {
            existing.setSaves(changing.getSaves());
        }
        if (changing.getSavePercent() > 0) {
            existing.setSavePercent(changing.getSavePercent());
        }
        if (changing.getNummer() != null) {
            existing.setNummer(changing.getNummer());
        }
        if (changing.getMarktwert() != null) {
            existing.setMarktwert(changing.getMarktwert());
        }
        if (changing.getTeam() != null) {
            existing.setTeam(changing.getTeam());
        }
    }
}

