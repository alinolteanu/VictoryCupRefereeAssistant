package com.example.laurentiuolteanu.victorycuprefereeassistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.laurentiuolteanu.victorycuprefereeassistant.bl.Team;
import com.example.laurentiuolteanu.victorycuprefereeassistant.dal.TeamSingleton;

public class SelectActionActivity extends AppCompatActivity{
    public static String TAG = SelectActionActivity.class.getName();
    long selectedTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_action);
        selectedTeam = getIntent().getLongExtra("selectedTeam",0);
        SelectActionFragment selectActionFragment = new SelectActionFragment();
        setContentFragment(selectActionFragment);
    }

    private void setContentFragment(Fragment fragment){
        Bundle b = new Bundle();
        b.putLong("selectedTeam", selectedTeam);
        fragment.setArguments(b);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
