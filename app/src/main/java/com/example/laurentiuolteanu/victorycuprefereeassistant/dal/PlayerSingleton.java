package com.example.laurentiuolteanu.victorycuprefereeassistant.dal;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerSingleton {
    private static PlayerSingleton mySingleton = null;

    private PlayerSingleton() {

    }

    public static PlayerSingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new PlayerSingleton();
        }
        return mySingleton;
    }

    public List<Player> getAllTeams() {
        List<Player> players = new ArrayList<>();

        players.add(new Player(1, "Stoica Adrian", 1));
        players.add(new Player(2, "TARLOIU Radu", 1));
        players.add(new Player(3, "Olteanu Laurentiu Alin", 1));
        players.add(new Player(4, "Popa Marian-Catalin", 1));
        players.add(new Player(5, "Florea Catalin George", 1));
        players.add(new Player(6, "Grimpels Aurel", 1));
        players.add(new Player(7, "Iliescu Bogdan George", 1));
        players.add(new Player(8, "Constantin Cristian", 1));
        players.add(new Player(9, "Papalici Constantin", 1));
        players.add(new Player(10, "Iliescu Constantin", 1));
        players.add(new Player(11, "Savulescu Marius", 1));
        players.add(new Player(12, "Ciupitu Radu", 1));
        players.add(new Player(13, "Vutei Alexandru", 1));
        players.add(new Player(14, "Vasile Nicolae", 1));
        players.add(new Player(15, "Costin Dragos Adrian", 1));
        players.add(new Player(16, "Radu Cristian", 1));
        players.add(new Player(17, "Barbulescu Emil-Andrei", 1));
        players.add(new Player(18, "Smaranda Victor-Bogdan", 1));

        players.add(new Player(19, "Gheorghe Zamfir", 2));
        players.add(new Player(20, "Militaru Andrei Cristian", 2));
        players.add(new Player(21, "Sava Aurelian Costel", 2));
        players.add(new Player(22, "Mic Dan-Catalin", 2));
        players.add(new Player(23, "Paraschiv Romeo-Aurel", 2));
        players.add(new Player(24, "Grigore Bogdan", 2));
        players.add(new Player(25, "Chira Nicolae", 2));
        players.add(new Player(26, "Deacu Eugen", 2));
        players.add(new Player(27, "Banica Marian", 2));
        players.add(new Player(28, "Murgeanu Mihai-Alexandru", 2));
        players.add(new Player(29, "Berdila Andrei", 2));
        players.add(new Player(30, "Vacaru Cristian-Gabriel-Paxino", 2));
        players.add(new Player(31, "Draghici Ciprian-Mihai", 2));
        players.add(new Player(32, "Morosanu Robert-Gabriel", 2));
        players.add(new Player(33, "Neagu Florin", 2));
        players.add(new Player(34, "Vasiloae Dragos", 2));
        players.add(new Player(35, "Plesoianu Iulian", 2));
        players.add(new Player(36, "Sandu Catalin", 2));


        return players;
    }

    public List<Player> getPlayersInTeam(long teamId){
        List<Player> list = new ArrayList<>();
        List<Player> players = getAllTeams();
        for(Player player: players)
            if(player.getTeamID() == teamId)
                list.add(player);
        return list;
    }
}
