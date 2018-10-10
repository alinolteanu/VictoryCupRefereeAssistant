package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Action;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.ActionSingleton;

import java.util.ArrayList;
import java.util.List;

public class SelectActionFragmentAdapter extends RecyclerView.Adapter<SelectActionFragmentAdapter.SelectActionFragmentAdapterViewHolder> {

    List<Integer> actions;
    long selectedTeam;

    public SelectActionFragmentAdapter(long st){
        actions = new ArrayList<>();
        actions.add(Action.ACTION_GOAL);
        actions.add(Action.ACTION_OWNGOAL);
        actions.add(Action.ACTION_PENALTY);
        actions.add(Action.ACTION_YELLOW_CARD);
        actions.add(Action.ACTION_RED_CARD);
        actions.add(Action.ACTION_FAULT);

        selectedTeam = st;
    }

    @NonNull
    @Override
    public SelectActionFragmentAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_select_action_item, parent, false);
        return new SelectActionFragmentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectActionFragmentAdapterViewHolder holder, int position) {
        holder.bindData(actions.get(position));
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public class SelectActionFragmentAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewActionDescription;
        private ImageView imageViewAction;

        public SelectActionFragmentAdapterViewHolder(final View itemView) {
            super(itemView);
            textViewActionDescription = itemView.findViewById(R.id.textViewActionDescription);
            imageViewAction = itemView.findViewById(R.id.imageViewAction);
        }

        public void bindData(final Integer action) {
            textViewActionDescription.setText(ActionSingleton.getInstance().getActionNameById(action));

            Context context = imageViewAction.getContext();
            int id = context.getResources().getIdentifier(ActionSingleton.getInstance().getActionIconById(action), "drawable", context.getPackageName());
            imageViewAction.setImageResource(id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newsCardClickListener(view, action);
                }
            });
        }

        private void newsCardClickListener(View view, Integer action) {
            FragmentManager fragmentManager = ((SelectActionActivity) view.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            SelectPlayerActionFragment fragment = new SelectPlayerActionFragment();
            Bundle b = new Bundle();
            b.putInt("action", action);
            b.putLong("selectedTeam", selectedTeam);
            fragment.setArguments(b);
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();
        }
    }
}
