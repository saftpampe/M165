package ch.iet_gibb.hockeyapp.game;

import java.util.Objects;

public class SpielRequestDTO {
    private String datum;
    private String austragungsort;  // Referenz zur Arena
    private String team1_id;  // Referenz zum ersten Team
    private String team2_id;  // Referenz zum zweiten Team
    private Integer toreTeam1;
    private Integer toreTeam2;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getAustragungsort() {
        return austragungsort;
    }

    public void setAustragungsort(String austragungsort) {
        this.austragungsort = austragungsort;
    }

    public String getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(String team1_id) {
        this.team1_id = team1_id;
    }

    public String getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(String team2_id) {
        this.team2_id = team2_id;
    }

    public Integer getToreTeam1() {
        return toreTeam1;
    }

    public void setToreTeam1(Integer toreTeam1) {
        this.toreTeam1 = toreTeam1;
    }

    public Integer getToreTeam2() {
        return toreTeam2;
    }

    public void setToreTeam2(Integer toreTeam2) {
        this.toreTeam2 = toreTeam2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SpielRequestDTO that = (SpielRequestDTO) o;
        return Objects.equals(datum, that.datum) && Objects.equals(austragungsort, that.austragungsort) && Objects.equals(team1_id, that.team1_id) && Objects.equals(team2_id, that.team2_id) && Objects.equals(toreTeam1, that.toreTeam1) && Objects.equals(toreTeam2, that.toreTeam2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datum, austragungsort, team1_id, team2_id, toreTeam1, toreTeam2);
    }
}
