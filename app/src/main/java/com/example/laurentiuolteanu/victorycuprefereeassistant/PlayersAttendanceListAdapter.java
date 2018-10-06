package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            final Resources resources = itemView.getContext().getResources();
            playerNameTextView.setText(player.getNume());
            itemView.setBackground(player.isSelected() ? resources.getDrawable(R.color.selectedBackgroundColor) : resources.getDrawable(R.color.notSelectedBackgroundColor));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    player.setSelected(!player.isSelected());
                    itemView.setBackground(player.isSelected() ? resources.getDrawable(R.color.selectedBackgroundColor) : resources.getDrawable(R.color.notSelectedBackgroundColor));
                }
            });
        }
    }
}