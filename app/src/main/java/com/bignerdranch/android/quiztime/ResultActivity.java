package com.bignerdranch.android.quiztime;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String RESULT_SCORE="com.bignerdranch.android.quiztime.resultScore";
    private TextView mAnswerDisplay;
    private Button mLevel2;

    public static Intent newIntent(Context cont,int resultScore){
        Intent i = new Intent(cont,ResultActivity.class);
        i.putExtra(RESULT_SCORE,resultScore);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        int final_score=getIntent().getIntExtra(RESULT_SCORE,0);
        String resultDisplay = ""+final_score+" / 15";
        mAnswerDisplay= (TextView) findViewById(R.id.score);
        mAnswerDisplay.setText(resultDisplay);

        if(final_score<3){
            View l2btn = findViewById(R.id.level2button);
            l2btn.setVisibility(View.GONE);
            View l2txt = findViewById(R.id.level2text);
            l2txt.setVisibility(View.GONE);
        }

        mLevel2 = (Button)findViewById(R.id.level2button);
        mLevel2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent il2 = new Intent(ResultActivity.this,Level2Activity.class);
                startActivity(il2);

            }
        });

    }

}
