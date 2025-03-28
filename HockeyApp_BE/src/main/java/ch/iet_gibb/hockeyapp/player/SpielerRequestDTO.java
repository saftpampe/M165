package ch.iet_gibb.hockeyapp.player;

import ch.iet_gibb.hockeyapp.team.Team;

import java.util.Objects;

public class SpielerRequestDTO {
    private String vorname;
    private String nachname;
    private String position;
    private String geburtsdatum;
    private String nationalitaet;
    private String unterVertragBis;
    private Integer marktwert;
    private Integer nummer;
    private String teamId;  // Referenz zum Team

    private PlayerType type;

    // Nur falls type = GOALIE
    private Integer saves;
    private Double savePercentage;

    // nur falls type = FIELD
    private Integer punkte;

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

    public Integer getMarktwert() {
        return marktwert;
    }

    public void setMarktwert(Integer marktwert) {
        this.marktwert = marktwert;
    }

    public Integer getNummer() {
        return nummer;
    }

    public void setNummer(Integer nummer) {
        this.nummer = nummer;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String team_id) {
        this.teamId = team_id;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public Double getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(Double savePercentage) {
        this.savePercentage = savePercentage;
    }

    public Integer getPunkte() {
        return punkte;
    }

    public void setPunkte(Integer punkte) {
        this.punkte = punkte;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SpielerRequestDTO that = (SpielerRequestDTO) o;
        return Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname) && Objects.equals(position, that.position) && Objects.equals(geburtsdatum, that.geburtsdatum) && Objects.equals(nationalitaet, that.nationalitaet) && Objects.equals(unterVertragBis, that.unterVertragBis) && Objects.equals(marktwert, that.marktwert) && Objects.equals(nummer, that.nummer) && Objects.equals(teamId, that.teamId) && type == that.type && Objects.equals(saves, that.saves) && Objects.equals(savePercentage, that.savePercentage) && Objects.equals(punkte, that.punkte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, position, geburtsdatum, nationalitaet, unterVertragBis, marktwert, nummer, teamId, type, saves, savePercentage, punkte);
    }
}
