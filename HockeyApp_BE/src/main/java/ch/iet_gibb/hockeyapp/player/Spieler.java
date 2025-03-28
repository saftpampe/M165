package ch.iet_gibb.hockeyapp.player;

import ch.iet_gibb.hockeyapp.team.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spieler")
public class Spieler {
    @Id
    private String id;
    private String vorname;
    private String nachname;
    private String position;
    private String geburtsdatum;
    private String nationalitaet;
    private String unterVertragBis;

    private Integer nummer;
    private Integer marktwert;
    private String teamId;  // Referenz zum Team

    // Getters und Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getNationalitaet() {
        return nationalitaet;
    }

    public void setNationalitaet(String nationalitaet) {
        this.nationalitaet = nationalitaet;
    }

    public String getUnterVertragBis() {
        return unterVertragBis;
    }

    public void setUnterVertragBis(String unterVertragBis) {
        this.unterVertragBis = unterVertragBis;
    }

    public Integer getNummer() {
        return nummer;
    }

    public void setNummer(Integer nummer) {
        this.nummer = nummer;
    }

    public Integer getMarktwert() {
        return marktwert;
    }

    public void setMarktwert(Integer marktwert) {
        this.marktwert = marktwert;
    }

    public String getTeamID() {
        return teamId;
    }

    public void setTeamId(String team) {
        this.teamId = team;
    }
}
