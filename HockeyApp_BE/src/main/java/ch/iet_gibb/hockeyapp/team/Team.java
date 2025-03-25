package ch.iet_gibb.hockeyapp.team;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "team")
public class Team {
    @Id
    private String id;
    private String name;
    private Integer gruendungsjahr;
    private List<String> erfolge;
    private Integer punkte;
    private String arena_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getArena_id() {
        return arena_id;
    }

    public void setArena_id(String arena_id) {
        this.arena_id = arena_id;
    }
}
