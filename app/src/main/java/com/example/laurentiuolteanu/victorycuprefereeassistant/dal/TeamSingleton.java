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
        teams.add(new Team(1, "Brigada", "logo_brigada"));
        teams.add(new Team(2, "Aripile Bucuresti", "logo_aripile_bucuresti"));
        teams.add(new Team(3, "Boca Old Boys", "logo_boca_old_boys"));
        teams.add(new Team(4, "ARSC", "logo_arsc"));
        teams.add(new Team(5, "Arsenal 2012", "logo_arsenal_2012"));
        teams.add(new Team(6, "All For One", "logo_all_for_one"));
        teams.add(new Team(7, "Inedit Team", "logo_inedit_team"));
        teams.add(new Team(8, "Metropolis - Oldies", "logo_metropolis_oldies"));
        teams.add(new Team(9, "Eagles Club Bucuresti", "logo_eagles_club_bucuresti"));
        teams.add(new Team(10, "RTC", "logo_rtc"));
        teams.add(new Team(11, "Deportivo Bucuresti", "logo_deportivo_bucuresti"));
        teams.add(new Team(12, "Anonymous", "logo_anonymous"));
        teams.add(new Team(13, "Arsenal Romania Supporters Club", "logo_arsenal_romania_supporters_club"));
        teams.add(new Team(14, "Onesti FC", "logo_onesti_fc"));
        teams.add(new Team(15, "Black Aces", "logo_black_aces"));
        teams.add(new Team(16, "FC 1 Decembrie", "logo_fc_1_decembrie"));

        return teams;
    }
}
