package ch.iet_gibb.hockeyapp.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpielService {
    private final SpielRepository spielRepository;

    @Autowired
    public SpielService(SpielRepository spielRepository) {
        this.spielRepository = spielRepository;
    }

    public List<SpielResponseDTO> findAll() {
        return spielRepository.findAll().stream().map(SpielMapper::toDTO).toList();
    }

    public SpielResponseDTO findById(String id) {
        return SpielMapper.toDTO(spielRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spiel not found")));
    }

    public void deleteById(String id) {
        spielRepository.deleteById(id);
    }

    public SpielResponseDTO insert(SpielRequestDTO spielRequestDTO) {
        Spiel spiel = SpielMapper.fromDTO(spielRequestDTO);
        return SpielMapper.toDTO(spielRepository.save(spiel));
    }

    public SpielResponseDTO update(SpielRequestDTO spielRequestDTO, String id) {
        Spiel existing = spielRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Spiel not found"));
        Spiel changing = SpielMapper.fromDTO(spielRequestDTO);
        mergeSpiel(existing, changing);

        return SpielMapper.toDTO(spielRepository.save(existing));
    }

    private void mergeSpiel(Spiel existing, Spiel changing) {
        if (changing.getDatum() != null) {
            existing.setDatum(changing.getDatum());
        }
        if (changing.getAustragungsort() != null) {
            existing.setAustragungsort(changing.getAustragungsort());
        }
        if (changing.getTeam1_id() != null) {
            existing.setTeam1_id(changing.getTeam1_id());
        }
        if (changing.getTeam2_id() != null) {
            existing.setTeam2_id(changing.getTeam2_id());
        }
        if (changing.getToreTeam1() != null) {
            existing.setToreTeam1(changing.getToreTeam1());
        }
        if (changing.getToreTeam2() != null) {
            existing.setToreTeam2(changing.getToreTeam2());
        }
    }
}
