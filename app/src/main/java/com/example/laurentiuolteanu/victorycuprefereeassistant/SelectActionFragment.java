package com.example.laurentiuolteanu.victorycuprefereeassistant;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class SelectActionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_action, container, false);
        Bundle b = getArguments();
        long selectedTeam = b.getLong("selectedTeam");

        SelectActionFragmentAdapter adapter = new SelectActionFragmentAdapter(selectedTeam);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerViewSelectAction);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getRootView().getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }



}