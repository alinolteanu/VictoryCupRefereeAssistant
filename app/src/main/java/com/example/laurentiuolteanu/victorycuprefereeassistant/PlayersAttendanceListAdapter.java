package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Player;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.PlayerSingleton;

import java.util.List;

public class PlayersAttendanceListAdapter extends RecyclerView.Adapter<PlayersAttendanceListAdapter.PlayersAttendanceListAdapterViewHolder> {

    private List<Player> players;

    public PlayersAttendanceListAdapter(long teamId){
        players = PlayerSingleton.getInstance().getPlayersInTeam(teamId);
    }

    @NonNull
    @Override
    public PlayersAttendanceListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_players_attendance_item, parent, false);
        return new PlayersAttendanceListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersAttendanceListAdapterViewHolder holder, int position) {
        holder.bindData(players.get(position));
    }



    @Override
    public int getItemCount() {
        return players.size();
    }

    public class PlayersAttendanceListAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView playerNameTextView;
        private boolean isSelected = false;

        public PlayersAttendanceListAdapterViewHolder(final View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.playerNameTextView);
        }

        public void bindData(final Player player) {

            playerNameTextView.setText(player.getNume());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Resources resources = view.getContext().getResources();
                    if(isSelected){
                        view.setBackground(resources.getDrawable(R.color.defaultBackgroundColor));
                        isSelected = false;
                    } else{
                        view.setBackground(resources.getDrawable(R.color.greenBackgroundColor));
                        isSelected = true;
                    }
                }
            });
        }
    }
}