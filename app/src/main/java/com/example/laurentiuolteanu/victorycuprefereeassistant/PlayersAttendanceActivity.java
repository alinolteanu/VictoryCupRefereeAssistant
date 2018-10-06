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

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Player;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.GameSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.PlayerSingleton;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.TeamSingleton;

import java.util.List;

public class PlayersAttendanceActivity extends AppCompatActivity {

    private ImageView teamCrestImageView;
    private TextView teamNameTextView;
    private Button confirmButton;
    private boolean attendanceTeam1;
    private RecyclerView matchesRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_attendance);

        teamCrestImageView = findViewById(R.id.teamCrestImageView);
        teamNameTextView = findViewById(R.id.teamNameTextView);
        confirmButton = findViewById(R.id.confirmButton);
        matchesRecyclerView = findViewById(R.id.playersList);
        mLayoutManager = new LinearLayoutManager(this);
        matchesRecyclerView.setLayoutManager(mLayoutManager);
        attendanceTeam1 = true;

        Intent intent = getIntent();
        final long matchId = intent.getLongExtra("matchId",0);

        final Team team1 = TeamSingleton.getInstance().getTeamById(GameSingleton.getInstance().getGameById(matchId).getHostID());
        final Team team2 = TeamSingleton.getInstance().getTeamById(GameSingleton.getInstance().getGameById(matchId).getGuestID());

        populateViews(team1);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(attendanceTeam1){
                    saveTeamAttendance(team1);
                    attendanceTeam1 = false;
                    populateViews(team2);
                } else{
                    saveTeamAttendance(team2);
                    Toast.makeText(getApplicationContext(), "Start Meci", Toast.LENGTH_SHORT).show();
                    finish();
                    //TODO: Start match using final matchId

                }
            }
        });

    }

    private void saveTeamAttendance(Team team) {
        List<Player> players = PlayerSingleton.getInstance().getPlayersInTeam(team.getId());
        StringBuilder sb = new StringBuilder();
        for(Player p:players)
            sb.append(p.isSelected() ? p.getId() + ", " : "");
        sb.delete(sb.lastIndexOf(",")== -1 ? 0 : sb.lastIndexOf(","), sb.length());
        Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void populateViews(Team team){
        int id = this.getResources().getIdentifier(team.getLogo(), "drawable", this.getPackageName());
        teamCrestImageView.setImageResource(id);

        teamNameTextView.setText(team.getName());

        PlayersAttendanceListAdapter playersAttendanceListAdapter = new PlayersAttendanceListAdapter(team.getId());
        matchesRecyclerView.setAdapter(playersAttendanceListAdapter);
    }

}
