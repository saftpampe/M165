
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
        dto.setNummer(spieler.getNummer());
        dto.setTeamId(spieler.getTeamID());
        dto.setMarktwert(spieler.getMarktwert());

        if (spieler instanceof Feldspieler fieldPlayer) {
            dto.setPunkte(fieldPlayer.getPunkte());
        } else if (spieler instanceof Goalie goalie) {
            dto.setSavePercentage(goalie.getSavePercentage());
            dto.setSaves(goalie.getSaves());
        } else {
            throw new IllegalArgumentException("Unsupported spieler type: " + spieler.getClass().getName());
        }
        return dto;

    }

    public static Spieler fromDTO(SpielerRequestDTO dto) {

        switch (dto.getType()) {
            case FIELD -> {
                Feldspieler fieldplayer = new Feldspieler();
                fieldplayer.setVorname(dto.getVorname());
                fieldplayer.setNachname(dto.getNachname());
                fieldplayer.setPosition(dto.getPosition());
                fieldplayer.setGeburtsdatum(dto.getGeburtsdatum());
                fieldplayer.setNationalitaet(dto.getNationalitaet());
                fieldplayer.setUnterVertragBis(dto.getUnterVertragBis());
                fieldplayer.setMarktwert(dto.getMarktwert());
                fieldplayer.setNummer(dto.getNummer());
                fieldplayer.setTeamId(dto.getTeamId());

                //Spieler-spezifisch
                fieldplayer.setPunkte(dto.getPunkte());

                return fieldplayer;
            }
            case GOALIE -> {
                Goalie goalie = new Goalie();
                goalie.setVorname(dto.getVorname());
                goalie.setNachname(dto.getNachname());
                goalie.setPosition(dto.getPosition());
                goalie.setGeburtsdatum(dto.getGeburtsdatum());
                goalie.setNationalitaet(dto.getNationalitaet());
                goalie.setUnterVertragBis(dto.getUnterVertragBis());
                goalie.setMarktwert(dto.getMarktwert());
                goalie.setNummer(dto.getNummer());
                goalie.setTeamId(dto.getTeamId());

                //Goalie-spezifisch
                goalie.setSavePercentage(dto.getSavePercentage());
                goalie.setSaves(dto.getSaves());

                return goalie;
            }
            default -> throw new IllegalArgumentException("Unsupported type");
        }
    }
}


