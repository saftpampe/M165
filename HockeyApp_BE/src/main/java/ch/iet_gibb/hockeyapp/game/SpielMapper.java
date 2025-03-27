package ch.iet_gibb.hockeyapp.game;

import org.bson.types.ObjectId;

public class SpielMapper {

    public static SpielResponseDTO toDTO(Spiel spiel) {
        SpielResponseDTO dto = new SpielResponseDTO();
        dto.setId(spiel.getId());
        dto.setDatum(spiel.getDatum());
        dto.setAustragungsort(spiel.getAustragungsort().toString());  // Convert ObjectId to String
        dto.setTeam1_id(spiel.getTeam1_id().toString());  // Convert ObjectId to String
        dto.setTeam2_id(spiel.getTeam2_id().toString());  // Convert ObjectId to String
        dto.setToreTeam1(spiel.getToreTeam1());
        dto.setToreTeam2(spiel.getToreTeam2());
        return dto;
    }

    public static Spiel fromDTO(SpielRequestDTO dto) {
        Spiel spiel = new Spiel();
        spiel.setDatum(dto.getDatum());
        spiel.setAustragungsort(String.valueOf(new ObjectId(dto.getAustragungsort()))); // Convert String to ObjectId
        spiel.setTeam1_id(String.valueOf(new ObjectId(dto.getTeam1_id()))); // Convert String to ObjectId
        spiel.setTeam2_id(String.valueOf(new ObjectId(dto.getTeam2_id()))); // Convert String to ObjectId
        spiel.setToreTeam1(dto.getToreTeam1());
        spiel.setToreTeam2(dto.getToreTeam2());
        return spiel;
    }
}
