package ch.iet_gibb.hockeyapp.arena;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ArenaService {
    private final ArenaRepository arenaRepository;

    @Autowired

    public ArenaService(ArenaRepository arenaRepository) {
        this.arenaRepository = arenaRepository;
    }

    public List<ArenaResponseDTO> findAll() {
        return arenaRepository.findAll().stream().map(ArenaMapper::toDTO).toList();
    }

    public ArenaResponseDTO findById(String id) {
        return ArenaMapper.toDTO(arenaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arena not found")));
    }

    public void deleteById(String id) {
        arenaRepository.deleteById(id);
    }

    public ArenaResponseDTO insert(ArenaRequestDTO arenaRequestDTO) {
        Arena arena = ArenaMapper.fromDTO(arenaRequestDTO);
        return ArenaMapper.toDTO(arenaRepository.save(arena));
    }

    public ArenaResponseDTO update(ArenaRequestDTO arenaRequestDTO, String id) {
        Arena existing = arenaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arena not found"));
        Arena changing = ArenaMapper.fromDTO(arenaRequestDTO);
        mergePlayers(existing, changing);

        return ArenaMapper.toDTO(arenaRepository.save(changing));
    }

    private void mergePlayers(Arena existing, Arena changing) {
        if (changing.getBaujahr() != null) {
            existing.setBaujahr(changing.getBaujahr());
        }
        if (StringUtils.isNotBlank(changing.getOrt())) {
            existing.setOrt(changing.getOrt());
        }
        if (changing.getKapazitaet() != null) {
            if (existing.getKapazitaet() > 0) {
                existing.setKapazitaet(changing.getKapazitaet());
            }
        }
        if (StringUtils.isNotBlank(changing.getName())) {
            existing.setName(changing.getName());
        }

    }
}
