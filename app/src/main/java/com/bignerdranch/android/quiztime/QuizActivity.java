package com.bignerdranch.android.quiztime;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private int mCurrentIndex=0;
    private TextView mQuestionTextView;
    private int mResult=0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_1,false),
            new Question(R.string.question_2,true),
            new Question(R.string.question_3,false),
            new Question(R.string.question_4,true),
            new Question(R.string.question_5,true),
            new Question(R.string.question_6,true),
            new Question(R.string.question_7,false),
            new Question(R.string.question_8,true),
            new Question(R.string.question_9,true),
            new Question(R.string.question_10,false),
            new Question(R.string.question_11,true),
            new Question(R.string.question_12,false),
            new Question(R.string.question_13,true),
            new Question(R.string.question_14,true),
            new Question(R.string.question_15,false)
    };

    private int[] resultCheck = new int[mQuestionBank.length];
    private void updateQuestion(){
        int question  = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private int totalQuestions=mQuestionBank.length;
    private void checkAnswer(boolean userPressedTrue){
        boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int resAnswerId=0;
        if(userPressedTrue==correctAnswer) {
            resAnswerId = R.string.correct_toast;
            if(resultCheck[mCurrentIndex]!=1){
                mResult++;
                resultCheck[mCurrentIndex]=1;
            }
        }
        else{
            resAnswerId = R.string.incorrect_toast;
            if(resultCheck[mCurrentIndex]!=1){
                resultCheck[mCurrentIndex]=1;
            }
        }

        Toast.makeText(this,resAnswerId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
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

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);




        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
                if(mCurrentIndex==0||mCurrentIndex-1==(mQuestionBank.length-1))
                {
                   Intent i = ResultActivity.newIntent(QuizActivity.this, mResult);
                    startActivity(i);
                }



            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex==0)
                    mCurrentIndex=mQuestionBank.length-1;
                else
                    mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();

            }
        });

        updateQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
