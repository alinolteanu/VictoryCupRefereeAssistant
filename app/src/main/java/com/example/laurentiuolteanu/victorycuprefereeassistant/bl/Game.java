package com.example.laurentiuolteanu.victorycuprefereeassistant.bl;

public class Game {

    public static final int GAME_NOT_STARTED = 0;
    public static final int GAME_IN_PROGRESS = 1;
    public static final int GAME_ENDED = 2;

    public static String LEAGUE_LIGA_2B = "Liga 2B";

    public static String PLAYING_FIELD_1 = "Terenul 1";
    public static String PLAYING_FIELD_2 = "Terenul 2";

    private long id;
    private long hostID;
    private long guestID;
    private String league;
    private String field;
    private int status;
    private String time;
    private String score;

    public Game(long id, long hostID, long guestID, String league, String field, int status, String time, String score) {
        this.id = id;
        this.hostID = hostID;
        this.guestID = guestID;
        this.league = league;
        this.field = field;
        this.status = status;
        this.time = time;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHostID() {
        return hostID;
    }

    public void setHostID(long hostID) {
        this.hostID = hostID;
    }

    public long getGuestID() {
        return guestID;
    }

    public void setGuestID(long guestID) {
        this.guestID = guestID;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
