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
    private Integer saves;
    private double savePercent;
    private Integer nummer;
    private Integer marktwert;
    private String team_id;  // Referenz zum Team

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

    public String getNationalitaet() {
        return nationalitaet;
    }

    public void setNationalitaet(String nationalitaet) {
        this.nationalitaet = nationalitaet;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getUnterVertragBis() {
        return unterVertragBis;
    }

    public void setUnterVertragBis(String unterVertragBis) {
        this.unterVertragBis = unterVertragBis;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public double getSavePercent() {
        return savePercent;
    }

    public void setSavePercent(double savePercent) {
        this.savePercent = savePercent;
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

    public String getTeam() {
        return team_id;
    }

    public void setTeam(String team) {
        this.team_id = team;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SpielerRequestDTO that = (SpielerRequestDTO) o;
        return Double.compare(savePercent, that.savePercent) == 0 && Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname) && Objects.equals(position, that.position) && Objects.equals(geburtsdatum, that.geburtsdatum) && Objects.equals(nationalitaet, that.nationalitaet) && Objects.equals(unterVertragBis, that.unterVertragBis) && Objects.equals(saves, that.saves) && Objects.equals(nummer, that.nummer) && Objects.equals(marktwert, that.marktwert) && Objects.equals(team_id, that.team_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, position, geburtsdatum, nationalitaet, unterVertragBis, saves, savePercent, nummer, marktwert, team_id);
    }
}
