package com.example.laurentiuolteanu.victorycuprefereeassistant.dal;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamSingleton {

    private static TeamSingleton mySingleton = null;

    private TeamSingleton() {

    }

    public static TeamSingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new TeamSingleton();
        }
        return mySingleton;
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Brigada"));
        teams.add(new Team(2, "Aripile Bucuresti"));
        teams.add(new Team(3, "Boca Old Boys"));
        teams.add(new Team(4, "ARSC"));
        teams.add(new Team(5, "Arsenal 2012"));
        teams.add(new Team(6, "All For One"));

        return teams;
    }
}
