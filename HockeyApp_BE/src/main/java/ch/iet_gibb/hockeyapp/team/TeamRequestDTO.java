package ch.iet_gibb.hockeyapp.team;

import java.util.List;
import java.util.Objects;

public class TeamRequestDTO {
    private String name;
    private Integer gruendungsjahr;
    private List<String> erfolge;
    private Integer punkte;
    private String arenaId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGruendungsjahr() {
        return gruendungsjahr;
    }

    public void setGruendungsjahr(Integer gruendungsjahr) {
        this.gruendungsjahr = gruendungsjahr;
    }

    public List<String> getErfolge() {
        return erfolge;
    }

    public void setErfolge(List<String> erfolge) {
        this.erfolge = erfolge;
    }

    public Integer getPunkte() {
        return punkte;
    }

    public void setPunkte(Integer punkte) {
        this.punkte = punkte;
    }

    public String getArenaId() {
        return arenaId;
    }

    public void setArenaId(String arenaId) {
        this.arenaId = arenaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamRequestDTO that = (TeamRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(gruendungsjahr, that.gruendungsjahr) && Objects.equals(erfolge, that.erfolge) && Objects.equals(punkte, that.punkte) && Objects.equals(arenaId, that.arenaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gruendungsjahr, erfolge, punkte, arenaId);
    }
}
