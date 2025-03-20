package com.example.hockeyapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spiel")
public class Spiel {
    @Id
    private String id;
    private String datum;
    private String austragungsort;  // Referenz zur Arena
    private String team1_id;  // Referenz zum ersten Team
    private String team2_id;  // Referenz zum zweiten Team
    private int toreTeam1;
    private int toreTeam2;

    // Getters und Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getToreTeam1() {
        return toreTeam1;
    }

    public void setToreTeam1(int toreTeam1) {
        this.toreTeam1 = toreTeam1;
    }

    public int getToreTeam2() {
        return toreTeam2;
    }

    public void setToreTeam2(int toreTeam2) {
        this.toreTeam2 = toreTeam2;
    }
}
