
package ch.iet_gibb.hockeyapp.player;

import org.bson.types.ObjectId;

public class SpielerMapper {

    public static SpielerResponseDTO toDTO(Spieler spieler) {
        SpielerResponseDTO dto = new SpielerResponseDTO();
        dto.setId(spieler.getId());
        dto.setVorname(spieler.getVorname());
        dto.setNachname(spieler.getNachname());
        dto.setPosition(spieler.getPosition());
        dto.setGeburtsdatum(spieler.getGeburtsdatum());
        dto.setNationalitaet(spieler.getNationalitaet());
        dto.setUnterVertragBis(spieler.getUnterVertragBis());
        dto.setSaves(spieler.getSaves());
        dto.setSavePercent(spieler.getSavePercent());
        dto.setNummer(spieler.getNummer());
        dto.setMarktwert(spieler.getMarktwert());
        dto.setTeam(spieler.getTeam());
        return dto;
    }

    public static Spieler fromDTO(SpielerRequestDTO dto) {
        Spieler spieler = new Spieler();
        spieler.setVorname(dto.getVorname());
        spieler.setNachname(dto.getNachname());
        spieler.setPosition(dto.getPosition());
        spieler.setGeburtsdatum(dto.getGeburtsdatum());
        spieler.setNationalitaet(dto.getNationalitaet());
        spieler.setUnterVertragBis(dto.getUnterVertragBis());
        spieler.setSaves(dto.getSaves());
        spieler.setSavePercent(dto.getSavePercent());
        spieler.setNummer(dto.getNummer());
        spieler.setMarktwert(dto.getMarktwert());
        spieler.setTeam(String.valueOf(new ObjectId(dto.getTeam()))); // Convert String to ObjectId

        return spieler;
    }
}


