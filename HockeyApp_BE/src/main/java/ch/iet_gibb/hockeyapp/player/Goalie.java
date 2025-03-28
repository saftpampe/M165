package ch.iet_gibb.hockeyapp.player;

public class Goalie extends Spieler{
    private Integer saves;
    private Double savePercentage;

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public double getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(Double savePercentage) {
        this.savePercentage = savePercentage;
    }
}
