package ch.iet_gibb.hockeyapp.team;

public class TeamMapper {

    // Convert Team entity to TeamResponseDTO
    public static TeamResponseDTO toDTO(Team team) {
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();
        teamResponseDTO.setId(team.getId());
        teamResponseDTO.setName(team.getName());
        teamResponseDTO.setGruendungsjahr(team.getGruendungsjahr());
        teamResponseDTO.setErfolge(team.getErfolge());
        teamResponseDTO.setPunkte(team.getPunkte());
        teamResponseDTO.setArenaId(team.getArenaId());
        return teamResponseDTO;
    }

    // Convert TeamRequestDTO to Team entity
    public static Team fromDTO(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setGruendungsjahr(dto.getGruendungsjahr());
        team.setErfolge(dto.getErfolge());
        team.setPunkte(dto.getPunkte());
        team.setArenaId(dto.getArenaId());
        return team;
    }
}
