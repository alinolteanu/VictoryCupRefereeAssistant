package com.example.laurentiuolteanu.victorycuprefereeassistant.bl;

public class Player {
    private long id;
    private String nume;
    private long teamID;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;

    public Player(long id, String nume, long teamID) {
        this.id = id;
        this.nume = nume;
        this.teamID = teamID;
        isSelected = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public long getTeamID() {
        return teamID;
    }

    public void setTeamID(long teamID) {
        this.teamID = teamID;
    }
}
