package com.example.dell.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //TextView ques = (TextView)findViewById(R.id.ques);
    Random rand = new Random();
    int v1, v2 ,rightAnswer=0 , userAnswer=0, tQuestions=0;
    boolean star=true;


    public void nextQuestion(){
    final TextView ques = (TextView)findViewById(R.id.ques);
        int a = rand.nextInt(50);
        int b=rand.nextInt(50);
        v1=a;   v2=b;
        rightAnswer = v1 + v2;
        ques.setText(Integer.toString(v1)+" + "+Integer.toString(v2)+" = ?");
        userAnswer=0;

    }



    public void buttonPressed(View view){
        //Button but =(Button) view;
        //int t = (int) but.getTag();
        TextView ques = (TextView)findViewById(R.id.ques);
        int t=0;
        int id=view.getId();
        if(id == R.id.b1) t=1;
        if(id == R.id.b2) t=2;
        if(id == R.id.b3) t=3;
        if(id == R.id.b4) t=4;
        if(id == R.id.b5) t=5;
        if(id == R.id.b6) t=6;
        if(id == R.id.b7) t=7;
        if(id == R.id.b8) t=8;
        if(id == R.id.b9) t=9;
        if(id == R.id.b0) t=0;
        userAnswer=userAnswer*10 + t;
        ques.setText(Integer.toString(v1)+" + "+Integer.toString(v2)+" = "+ Integer.toString(userAnswer));
        if(userAnswer==rightAnswer){
            nextQuestion();
            tQuestions++;
            TextView score = (TextView)findViewById(R.id.score);
            score.setText(Integer.toString(tQuestions));
        }

    }

    public void backspace(View view){
        TextView ques = (TextView)findViewById(R.id.ques);
        userAnswer/=10;
        if(userAnswer==0)
            ques.setText(Integer.toString(v1)+" + "+Integer.toString(v2)+" = ?");
    }



    public void replay(View view){
        userAnswer=0;
        tQuestions=0;

        final TextView timer = (TextView)findViewById(R.id.timer);
        LinearLayout replayScreen = (LinearLayout)findViewById(R.id.replayScreen);
        replayScreen.setVisibility(View.INVISIBLE);

        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
            timer.setText(Integer.toString((int)millisUntilFinished/1000));
            if(star==true) {

                nextQuestion();
                star=false;
            }
        }

            @Override
            public void onFinish() {
                TextView fScore = (TextView)findViewById(R.id.fScore);
                fScore.setText(Integer.toString(tQuestions));
                LinearLayout replayScreen = (LinearLayout)findViewById(R.id.replayScreen);
                replayScreen.setVisibility(View.VISIBLE);
                TextView score = (TextView)findViewById(R.id.score);
                score.setText(Integer.toString(0));

            }
        }.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView score = (TextView)findViewById(R.id.score);
        score.setText(Integer.toString(0));

        replay(score);

    }

}
