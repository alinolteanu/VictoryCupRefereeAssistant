package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Action;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Game;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Player;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.ActionSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.GameSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.PlayerSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.TeamSingleton;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity {

    private ImageView team1ImageView;
    private ImageView team2ImageView;
    private TextView team1NameTextView;
    private TextView team2NameTextView;
    private TextView matchStatusTextView;
    private TextView scoreTextView;
    private TextView breakScoreTextView;
    private RecyclerView actionsRecyclerView;
    private Button addActionTeam1;
    private Button addActionTeam2;
    private RecyclerView.LayoutManager mLayoutManager;

    public static int REQUEST_CODE = 100;
    private long matchId;

    List<Action> actions;

    @Override
    public void onBackPressed() {
        Game game = GameSingleton.getInstance().getGameById(matchId);
        if(game.getStatus() == Game.GAME_IN_PROGRESS) {
            Toast.makeText(getApplicationContext(), "Meci in desfasurare. Nu recomand.", Toast.LENGTH_SHORT).show();
            //TODO: Dialog ceva "Esti sigur?" sau...
            super.onBackPressed();
        }
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        initViews();

        Intent i = getIntent();
        matchId = i.getLongExtra("matchId", 0);
        initData();
    }

    private void initData() {
        Game game = GameSingleton.getInstance().getGameById(matchId);
        if(game.getStatus() == Game.GAME_ENDED){
            populateGameEnded(game);
            actions = ActionSingleton.getInstance().getAllActions();
        } else if(game.getStatus() == Game.GAME_IN_PROGRESS){
            populateGameInProgress(game);
            actions = ActionSingleton.getInstance().getAllActions();
        } else if(game.getStatus() == Game.GAME_NOT_STARTED){
            populateGameNotStarted(game);
            actions = new ArrayList<>();
        }
        ActionsListAdapter gamesListAdapter = new ActionsListAdapter(actions);
        actionsRecyclerView.setAdapter(gamesListAdapter);
    }

    private void populateGameInProgress(Game g) {
        populateGeneralViews(g);
        matchStatusTextView.setText("31:46");
        scoreTextView.setText(g.getScore());
        breakScoreTextView.setText(String.format("Scor la pauza: %s", g.getScore()));
    }

    private void populateGameEnded(Game g) {
        populateGeneralViews(g);
        matchStatusTextView.setText(R.string.endGameText);
        scoreTextView.setText(g.getScore());
        breakScoreTextView.setText(String.format("Scor la pauza: %s", "1 - 3"));
        addActionTeam1.setVisibility(View.GONE);
        addActionTeam2.setVisibility(View.GONE);
    }

    private void populateGameNotStarted(Game g){
        populateGeneralViews(g);
        matchStatusTextView.setText("00:00");
        scoreTextView.setText("0 - 0");
        breakScoreTextView.setVisibility(View.GONE);
    }

    private void populateGeneralViews(Game g) {
        final Team team1 = TeamSingleton.getInstance().getTeamById(g.getHostID());
        final Team team2 = TeamSingleton.getInstance().getTeamById(g.getGuestID());
        team1ImageView.setImageResource(getResources().getIdentifier(team1.getLogo() , "drawable", getPackageName()));
        team2ImageView.setImageResource(getResources().getIdentifier(team2.getLogo() , "drawable", getPackageName()));
        team1NameTextView.setText(team1.getName());
        team2NameTextView.setText(team2.getName());

        team1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGameAction(team1, team2);
            }
        });

        team2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGameAction(team2, team1);
            }
        });

        addActionTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGameAction(team1, team2);
            }
        });
        addActionTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddGameAction(team2, team1);
            }
        });
    }

    private void AddGameAction(Team team1, Team team2) {
        Intent i = new Intent(getApplicationContext(), SelectActionActivity.class);
        i.putExtra("selectedTeam", team1.id);
        i.putExtra("opponentTeam", team2.id);
        startActivityForResult(i, REQUEST_CODE);
    }

    private void initViews(){
        team1ImageView = findViewById(R.id.imageViewActionHost);
        team2ImageView = findViewById(R.id.imageViewActionGuest);
        team1NameTextView = findViewById(R.id.team1NameTextView);
        team2NameTextView = findViewById(R.id.team2NameTextView);
        matchStatusTextView = findViewById(R.id.matchStatusTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        breakScoreTextView = findViewById(R.id.breakScoreTextView);
        addActionTeam1 = findViewById(R.id.buttonAddActionTeam1);
        addActionTeam2 = findViewById(R.id.buttonAddActionTeam2);

        actionsRecyclerView = findViewById(R.id.matchHighlightsList);
        mLayoutManager = new LinearLayoutManager(this);
        actionsRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int action = data.getIntExtra("action", 0);
                long playerId = data.getLongExtra("playerId", 0);
                actions.add(createAction(action, playerId));
                actionsRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public Action createAction(int action, long playerId) {
        Action a = null;
        Game g = GameSingleton.getInstance().getGameById(matchId);
        List<Player> hostTeamPlayers = PlayerSingleton.getInstance().getPlayersInTeam(g.getHostID());
        List<Player> guestTeamPlayers = PlayerSingleton.getInstance().getPlayersInTeam(g.getGuestID());

        switch (action) {
            case Action.ACTION_GOAL:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, String.valueOf(GameSingleton.getInstance().getScoreAsInt(g.getId()).get(0) + 1).concat(" - 0"));
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action, "0 - ".concat(String.valueOf(GameSingleton.getInstance().getScoreAsInt(g.getId()).get(1) + 1)));
                }
                break;
            case Action.ACTION_OWNGOAL:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, null);
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action,null);
                }
                break;
            case Action.ACTION_PENALTY:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, null);
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action,null);
                }
                break;
            case Action.ACTION_RED_CARD:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, null);
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action,null);
                }
                break;
            case Action.ACTION_YELLOW_CARD:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, null);
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action,null);
                }
                break;
            case Action.ACTION_FAULT:
                if(TeamContainsPlayer(playerId, hostTeamPlayers)){
                    a = new Action(actions.size() + 1, PlayerSingleton.getInstance().getPlayerById(playerId).getNume(),
                            null, action, null);
                } else{
                    a = new Action(actions.size() + 1, null,
                            PlayerSingleton.getInstance().getPlayerById(playerId).getNume(), action,null);
                }
                break;
        }
        return a;
    }

    public boolean TeamContainsPlayer(long playerId, List<Player> team){
        for(Player p: team)
            if(p.getId() == playerId)
                return true;
        return false;
    }
}
