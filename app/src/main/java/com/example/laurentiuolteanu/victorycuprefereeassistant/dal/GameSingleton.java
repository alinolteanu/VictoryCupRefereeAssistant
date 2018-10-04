package com.example.laurentiuolteanu.victorycuprefereeassistant.dal;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Game;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;

import java.util.ArrayList;
import java.util.List;

public class GameSingleton {

    private static GameSingleton mySingleton = null;

    private GameSingleton() {

    }

    public static GameSingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new GameSingleton();
        }
        return mySingleton;
    }

    public List<Game> getAllGames() {
        List<Game> teams = new ArrayList<>();
        teams.add(new Game(1, 5, 6, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_1, Game.GAME_ENDED, "19:00", "3 - 1"));
        teams.add(new Game(2, 1, 2, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_1, Game.GAME_ENDED, "20:00", "2 - 2"));
        teams.add(new Game(3, 3, 4, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_1, Game.GAME_IN_PROGRESS, "20:00", "2 - 0"));
        teams.add(new Game(4, 7, 8, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_2, Game.GAME_IN_PROGRESS, "20:30", "0 - 0"));
        teams.add(new Game(5, 9, 10, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_1, Game.GAME_NOT_STARTED, "22:00", null));
        teams.add(new Game(6, 11, 12, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_2, Game.GAME_NOT_STARTED, "22:00", null));
        teams.add(new Game(7, 13, 14, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_1, Game.GAME_NOT_STARTED, "23:00", null));
        teams.add(new Game(8, 15, 16, Game.LEAGUE_LIGA_2B, Game.PLAYING_FIELD_2, Game.GAME_NOT_STARTED, "23:00", null));
        return teams;
    }
}
