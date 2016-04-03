package com.bignerdranch.android.quiztime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Level2Activity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private int mCurrentIndex=0;
    private TextView mQuestionTextView;
    private int mResult=0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.l2question1,false),
            new Question(R.string.l2question2,true),
            new Question(R.string.l2question3,true),
            new Question(R.string.l2question4,true),
            new Question(R.string.l2question5,false),
            new Question(R.string.l2question6,true),
            new Question(R.string.l2question7,true),
            new Question(R.string.l2question8,false),
            new Question(R.string.l2question9,true),
            new Question(R.string.l2question10,false),
            new Question(R.string.l2question11,true),
            new Question(R.string.l2question12,true),
            new Question(R.string.l2question13,false),
            new Question(R.string.l2question14,true),
            new Question(R.string.l2question15,true)

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

        Toast.makeText(this, resAnswerId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
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
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                if (mCurrentIndex == 0 || mCurrentIndex - 1 == (mQuestionBank.length - 1)) {
                    Intent i = FinalResultActivity.newIntentL2(Level2Activity.this, mResult);
                    startActivity(i);
                }


            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0)
                    mCurrentIndex = mQuestionBank.length - 1;
                else
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();

            }
        });

        updateQuestion();
    }

}
