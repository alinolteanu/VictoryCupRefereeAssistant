package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Player;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.PlayerSingleton;

import java.util.List;

public class SelectPlayerActionFragmentAdapter extends RecyclerView.Adapter<SelectPlayerActionFragmentAdapter.SelectPlayerActionFragmentListAdapterViewHolder> {

    private List<Player> players;
    private SelectPlayerActionFragment fragment;

    public SelectPlayerActionFragmentAdapter(long teamId, SelectPlayerActionFragment frag){
        players = PlayerSingleton.getInstance().getPlayersInTeam(teamId);
        fragment = frag;
    }

    @NonNull
    @Override
    public SelectPlayerActionFragmentListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_select_player_item, parent, false);
        return new SelectPlayerActionFragmentListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPlayerActionFragmentListAdapterViewHolder holder, int position) {
        holder.bindData(players.get(position));
    }



    @Override
    public int getItemCount() {
        return players.size();
    }

    public class SelectPlayerActionFragmentListAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPlayerName;

        public SelectPlayerActionFragmentListAdapterViewHolder(final View itemView) {
            super(itemView);
            textViewPlayerName = itemView.findViewById(R.id.textViewSelectPlayerName);
        }

        public void bindData(final Player player) {
            textViewPlayerName.setText(player.getNume());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerNameClickListener(view, player);
                }
            });
        }

        private void playerNameClickListener(View view, Player player) {
            fragment.ActionSelected(player.getId());
        }
    }
}
