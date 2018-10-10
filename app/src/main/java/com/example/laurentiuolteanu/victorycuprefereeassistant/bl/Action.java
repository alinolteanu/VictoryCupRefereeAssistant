package com.example.laurentiuolteanu.victorycuprefereeassistant.bl;

public class Action {
    public static final int ACTION_GOAL = 1;
    public static final int ACTION_OWNGOAL = 2;
    public static final int ACTION_PENALTY = 3;
    public static final int ACTION_YELLOW_CARD = 4;
    public static final int ACTION_RED_CARD = 5;
    public static final int ACTION_FAULT = 6;

    public int minute;
    public String hostPlayer;
    public String guestPlayer;
    public int action;
    public String score;

    public Action(int minute, String hostPlayer, String guestPlayer, int action, String score) {
        this.minute = minute;
        this.hostPlayer = hostPlayer;
        this.guestPlayer = guestPlayer;
        this.action = action;
        this.score = score;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getHostPlayer() {
        return hostPlayer;
    }

    public void setHostPlayer(String hostPlayer) {
        this.hostPlayer = hostPlayer;
    }

    public String getGuestPlayer() {
        return guestPlayer;
    }

    public void setGuestPlayer(String guestPlayer) {
        this.guestPlayer = guestPlayer;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
