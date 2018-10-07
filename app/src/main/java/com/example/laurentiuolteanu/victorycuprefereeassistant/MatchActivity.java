package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Game;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.GameSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.TeamSingleton;

public class MatchActivity extends AppCompatActivity {

    private ImageView team1ImageView;
    private ImageView team2ImageView;
    private TextView team1NameTextView;
    private TextView team2NameTextView;
    private TextView matchStatusTextView;
    private TextView scoreTextView;
    private TextView breakScoreTextView;
    private Button addActionTeam1;
    private Button addActionTeam2;

    private long matchId;

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
        } else if(game.getStatus() == Game.GAME_IN_PROGRESS){
            populateGameInProgress(game);
        } else if(game.getStatus() == Game.GAME_NOT_STARTED){
            populateGameNotStarted(game);
        }
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
        breakScoreTextView.setText(String.format("Scor la pauza: %s", g.getScore()));
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
        Team team1 = TeamSingleton.getInstance().getTeamById(g.getHostID());
        Team team2 = TeamSingleton.getInstance().getTeamById(g.getGuestID());
        team1ImageView.setImageResource(getResources().getIdentifier(team1.getLogo() , "drawable", getPackageName()));
        team2ImageView.setImageResource(getResources().getIdentifier(team2.getLogo() , "drawable", getPackageName()));
        team1NameTextView.setText(team1.getName());
        team2NameTextView.setText(team2.getName());
    }

    private void initViews(){
        team1ImageView = findViewById(R.id.team1ImageView);
        team2ImageView = findViewById(R.id.team2ImageView);
        team1NameTextView = findViewById(R.id.team1NameTextView);
        team2NameTextView = findViewById(R.id.team2NameTextView);
        matchStatusTextView = findViewById(R.id.matchStatusTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        breakScoreTextView = findViewById(R.id.breakScoreTextView);
        addActionTeam1 = findViewById(R.id.buttonAddActionTeam1);
        addActionTeam2 = findViewById(R.id.buttonAddActionTeam2);

        addActionTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "addActionTeam1", Toast.LENGTH_SHORT).show();
            }
        });
        addActionTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "addActionTeam2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
