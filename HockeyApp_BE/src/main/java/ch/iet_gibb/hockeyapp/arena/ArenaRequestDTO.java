package ch.iet_gibb.hockeyapp.arena;

import java.util.Objects;

public class ArenaRequestDTO {
    private String name;
    private Integer kapazitaet;

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Integer getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public Integer getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(Integer kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String ort;
    private Integer baujahr;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArenaRequestDTO that = (ArenaRequestDTO) o;
        return kapazitaet == that.kapazitaet && baujahr == that.baujahr && Objects.equals(name, that.name) && Objects.equals(ort, that.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, kapazitaet, ort, baujahr);
    }
}
