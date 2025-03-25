package ch.iet_gibb.hockeyapp.arena;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arena")
public class Arena {
    @Id
    private String id;
    private String name;
    private Integer kapazitaet;
    private String ort;
    private Integer baujahr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
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
}
