package com.example.laurentiuolteanu.victorycuprefereeassistant.dal;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Action;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Game;

import java.util.ArrayList;
import java.util.List;

public class ActionSingleton {

    private static ActionSingleton mySingleton = null;

    private ActionSingleton() {

    }

    public static ActionSingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new ActionSingleton();
        }
        return mySingleton;
    }

    public List<Action> getAllActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action(18, null, "Eduard-Adrian Manea", Action.ACTION_GOAL, "0 - 1"));
        actions.add(new Action(18, null, "Timofte Edmond", Action.ACTION_FAULT, null));
        actions.add(new Action(25, null, "Manea Eduard-Adrian", Action.ACTION_GOAL, "0 - 2"));
        actions.add(new Action(25, null, "Marascu Mihai", Action.ACTION_FAULT, null));
        actions.add(new Action(27, null, "Marascu Mihai", Action.ACTION_GOAL, "0 - 3"));
        actions.add(new Action(28, "Manta Cristian", null, Action.ACTION_GOAL, "1 - 3"));
        actions.add(new Action(34, null, "Manea Eduard-Adrian", Action.ACTION_GOAL, "1 - 4"));
        actions.add(new Action(35, null, "Timofte Edmond", Action.ACTION_GOAL, "1 - 5"));
        actions.add(new Action(36, "Constantin Alexandru", null, Action.ACTION_GOAL, "2 - 5"));
        actions.add(new Action(36, "Calinescu George", null, Action.ACTION_FAULT, null));
        actions.add(new Action(38, null, "Marascu Mihai", Action.ACTION_PENALTY, "2 - 6"));
        return actions;
    }

    public String getActionNameById(int action){
        switch (action){
            case Action.ACTION_GOAL:
                return "Gol";
            case Action.ACTION_OWNGOAL:
                return "AutoGol";
            case Action.ACTION_PENALTY:
                return "Penalty";
            case Action.ACTION_YELLOW_CARD:
                return "Cartonas Galben";
            case Action.ACTION_RED_CARD:
                return "Cartonas Rosu";
            case Action.ACTION_FAULT:
                return "Fault";
        }
        return "";
    }

    public String getActionIconById(int action){
        switch (action){
            case Action.ACTION_GOAL:
                return Action.ACTION_GOAL_ICON;
            case Action.ACTION_OWNGOAL:
                return Action.ACTION_OWNGOAL_ICON;
            case Action.ACTION_PENALTY:
                return Action.ACTION_PENALTY_ICON;
            case Action.ACTION_YELLOW_CARD:
                return Action.ACTION_YELLOW_CARD_ICON;
            case Action.ACTION_RED_CARD:
                return Action.ACTION_RED_CARD_ICON;
            case Action.ACTION_FAULT:
                return Action.ACTION_FAULT_ICON;
        }
        return "";
    }
}
