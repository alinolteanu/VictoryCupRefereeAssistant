package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Action;

import java.util.List;

public class ActionsListAdapter extends RecyclerView.Adapter<ActionsListAdapter.ActionsListAdapterViewHolder> {

    private List<Action> actions;

    public ActionsListAdapter(List<Action> actions){
        this.actions = actions;
    }

    @NonNull
    @Override
    public ActionsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_game_action, parent, false);
        return new ActionsListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionsListAdapterViewHolder holder, int position) {
        holder.bindData(actions.get(position));
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public class ActionsListAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMinute;
        private TextView txtNamePlayerHost;
        private TextView txtNamePlayerGuest;
        private TextView txtScore;
        private ImageView imageViewActionGuest;
        private ImageView imageViewActionHost;

        public ActionsListAdapterViewHolder(final View itemView) {
            super(itemView);
            txtMinute = itemView.findViewById(R.id.txtMinute);
            txtNamePlayerHost = itemView.findViewById(R.id.txtNamePlayerHost);
            txtNamePlayerGuest = itemView.findViewById(R.id.txtNamePlayerGuest);
            txtScore = itemView.findViewById(R.id.txtScore);
            imageViewActionGuest = itemView.findViewById(R.id.imageViewActionGuest);
            imageViewActionHost = itemView.findViewById(R.id.imageViewActionHost);
        }

        public void bindData(final Action action) {
            setMatchTeamsPreferences(action);
        }

        private void setMatchTeamsPreferences(Action action) {
            txtMinute.setText(action.getMinute() + "'");
            txtNamePlayerHost.setText(action.getHostPlayer() != null ? action.getHostPlayer() : "");
            txtNamePlayerGuest.setText(action.getGuestPlayer() != null ? action.getGuestPlayer() : "");
            txtScore.setText(action.getScore() != null ? action.getScore() : "");

            Context context = imageViewActionGuest.getContext();
            int id = -1;
            switch (action.getAction()) {
                case Action.ACTION_GOAL : id = context.getResources().getIdentifier("ic_ball", "drawable", context.getPackageName());
                                            break;
                case Action.ACTION_OWNGOAL : id = context.getResources().getIdentifier("ic_ball_red", "drawable", context.getPackageName());
                                            break;
                case Action.ACTION_PENALTY : id = context.getResources().getIdentifier("ic_penalty", "drawable", context.getPackageName());
                                            break;
                case Action.ACTION_YELLOW_CARD : id = context.getResources().getIdentifier("ic_yellow_card", "drawable", context.getPackageName());
                                            break;
                case Action.ACTION_RED_CARD : id = context.getResources().getIdentifier("ic_red_card", "drawable", context.getPackageName());
                                            break;
                case Action.ACTION_FAULT : id = context.getResources().getIdentifier("ic_adidas", "drawable", context.getPackageName());
                                            break;
            }

            imageViewActionHost.setImageResource(action.getHostPlayer() != null ? id : 0);
            imageViewActionGuest.setImageResource(action.getGuestPlayer() != null ? id : 0);
        }
    }
}