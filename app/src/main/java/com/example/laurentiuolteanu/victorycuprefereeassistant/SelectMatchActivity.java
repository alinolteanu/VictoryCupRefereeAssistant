package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.GameSingleton;

public class SelectMatchActivity extends AppCompatActivity {

    private RecyclerView matchesRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_match);

        matchesRecyclerView = findViewById(R.id.listGames);
        mLayoutManager = new LinearLayoutManager(this);
        matchesRecyclerView.setLayoutManager(mLayoutManager);

        GamesListAdapter gamesListAdapter = new GamesListAdapter(GameSingleton.getInstance().getAllGames());
        matchesRecyclerView.setAdapter(gamesListAdapter);
    }
}
