package ch.iet_gibb.hockeyapp.arena;

public class ArenaMapper {

    public static ArenaResponseDTO toDTO(Arena arena) {
        ArenaResponseDTO arenaResponseDTO = new ArenaResponseDTO();
        arenaResponseDTO.setId(arena.getId());
        arenaResponseDTO.setName(arena.getName());
        arenaResponseDTO.setBaujahr(arena.getBaujahr());
        arenaResponseDTO.setKapazitaet(arena.getKapazitaet());
        arenaResponseDTO.setOrt(arena.getOrt());
        return arenaResponseDTO;
    }

    public static Arena fromDTO(ArenaRequestDTO dto) {
        Arena arena = new Arena();
        arena.setName(dto.getName());
        arena.setKapazitaet(dto.getKapazitaet());
        arena.setOrt(dto.getOrt());
        arena.setBaujahr(dto.getBaujahr());
        return arena;
    }
}
