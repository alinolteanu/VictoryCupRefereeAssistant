package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectPlayerActionFragment extends Fragment {

    private int action;
    private static int RESULT_OK = 100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_player, container, false);
        Bundle b = getArguments();
        long selectedTeam = b.getLong("selectedTeam");
        action = b.getInt("action");

        SelectPlayerActionFragmentAdapter adapter = new SelectPlayerActionFragmentAdapter(selectedTeam, this);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerViewSelectPlayer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getRootView().getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void ActionSelected(long playerId){
        Intent data = new Intent();
        data.putExtra("action", action);
        data.putExtra("playerId", playerId);
        getActivity().setResult(getActivity().RESULT_OK, data);
        getActivity().finish();
    }
}