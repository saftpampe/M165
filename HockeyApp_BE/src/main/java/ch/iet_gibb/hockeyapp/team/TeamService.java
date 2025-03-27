package ch.iet_gibb.hockeyapp.team;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Get all teams
    public List<TeamResponseDTO> findAll() {
        return teamRepository.findAll().stream().map(TeamMapper::toDTO).toList();
    }

    // Get a team by its ID
    public TeamResponseDTO findById(String id) {
        return TeamMapper.toDTO(teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found")));
    }

    // Get teams by name
    public List<TeamResponseDTO> findByName(String name) {
        return teamRepository.findByName(name).stream().map(TeamMapper::toDTO).toList();
    }

    // Delete a team by its ID
    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }

    // Create a new team
    public TeamResponseDTO insert(TeamRequestDTO teamRequestDTO) {
        Team team = TeamMapper.fromDTO(teamRequestDTO);
        return TeamMapper.toDTO(teamRepository.save(team));
    }

    // Update an existing team
    public TeamResponseDTO update(TeamRequestDTO teamRequestDTO, String id) {
        Team existing = teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        Team changing = TeamMapper.fromDTO(teamRequestDTO);
        mergeTeams(existing, changing);

        return TeamMapper.toDTO(teamRepository.save(existing));
    }

    // Merge updated team information
    private void mergeTeams(Team existing, Team changing) {
        if (StringUtils.isNotBlank(changing.getName())) {
            existing.setName(changing.getName());
        }
        if (changing.getGruendungsjahr() != null) {
            existing.setGruendungsjahr(changing.getGruendungsjahr());
        }
        if (changing.getErfolge() != null && !changing.getErfolge().isEmpty()) {
            existing.setErfolge(changing.getErfolge());
        }
        if (changing.getPunkte() != null) {
            existing.setPunkte(changing.getPunkte());
        }
        if (StringUtils.isNotBlank(changing.getArenaId())) {
            existing.setArenaId(changing.getArenaId());
        }
    }
}
