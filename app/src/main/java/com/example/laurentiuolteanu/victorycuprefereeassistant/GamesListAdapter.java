package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Game;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.TeamSingleton;

import java.util.List;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesListAdapterViewHolder> {

    private List<Game> games;

    public GamesListAdapter(List<Game> g){
        games = g;
    }

    @NonNull
    @Override
    public GamesListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_games_item, parent, false);
        return new GamesListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesListAdapterViewHolder holder, int position) {
        holder.bindData(games.get(position));
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class GamesListAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView fieldTextView;
        private TextView leagueTextView;
        private TextView team1TextView;
        private TextView team2TextView;
        private TextView scoreTimeTextView;
        private ImageView team1ImageView;
        private ImageView team2ImageView;
        private ImageView matchStatusImageView;

        public GamesListAdapterViewHolder(final View itemView) {
            super(itemView);
            fieldTextView = itemView.findViewById(R.id.fieldTextView);
            leagueTextView = itemView.findViewById(R.id.leagueTextView);
            team1TextView = itemView.findViewById(R.id.team1TextView);
            team2TextView = itemView.findViewById(R.id.team2TextView);
            scoreTimeTextView = itemView.findViewById(R.id.timeScoreTextView);
            team1ImageView = itemView.findViewById(R.id.team1ImageView);
            team2ImageView = itemView.findViewById(R.id.team2ImageView);
            matchStatusImageView = itemView.findViewById(R.id.matchStatusImageView);

        }

        public void bindData(final Game game) {
            Team team1 = TeamSingleton.getInstance().getTeamById(game.getHostID());
            Team team2 = TeamSingleton.getInstance().getTeamById(game.getGuestID());

            setMatchTeamsPreferences(team1, team2);
            setGameStatistics(game);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SelectMatch(view, game);
                }
            });
        }

        private void setMatchTeamsPreferences(Team team1, Team team2) {
            team1TextView.setText(team1.getName());
            team2TextView.setText(team2.getName());

            Context context = team1ImageView.getContext();
            int id = context.getResources().getIdentifier(team1.getLogo(), "drawable", context.getPackageName());
            team1ImageView.setImageResource(id);
            id = context.getResources().getIdentifier(team2.getLogo(), "drawable", context.getPackageName());
            team2ImageView.setImageResource(id);
        }

        private void setGameStatistics(Game game) {
            fieldTextView.setText(game.getField());
            leagueTextView.setText(game.getLeague());
            switch (game.getStatus()){
                case Game.GAME_ENDED:
                    scoreTimeTextView.setText(game.getScore());
                    matchStatusImageView.setImageResource(R.drawable.ic_match_ended);
                    break;
                case Game.GAME_NOT_STARTED:
                    scoreTimeTextView.setText(game.getTime());
                    matchStatusImageView.setImageResource(R.drawable.ic_match_ready);
                    break;
                case Game.GAME_IN_PROGRESS:
                    scoreTimeTextView.setText("Se Joaca");
                    matchStatusImageView.setImageResource(R.drawable.ic_match_in_progress);
            }
        }

        private void SelectMatch(View view, Game game){
            Intent i = new Intent(view.getContext(), PlayersAttendanceActivity.class);
            i.putExtra("matchId", game.getId());
            view.getContext().startActivity(i);
        }
    }
}