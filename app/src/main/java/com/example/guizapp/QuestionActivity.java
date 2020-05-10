package com.example.guizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView  questions,noindicate;
    private LinearLayout option;
    private Button sharebtn,nextbtn;
    private int count=0;
    private   List<QuestionMosel> list;
    private int position=0;
    private int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        questions = findViewById(R.id.question);
        noindicate = findViewById(R.id.no_indicate);
        option = findViewById(R.id.options);
        sharebtn = findViewById(R.id.share_btn);
       nextbtn = findViewById(R.id.next_btn);
       list = new ArrayList<>();
        list.add(new QuestionMosel("How to kill an activity in Android?"," a: finish()"," b: finishActivity(int requestCode)","a & b","kill()","a & b"));
        list.add(new QuestionMosel("How many ports are allocated for new emulator?","a: 2","b: 6","c: 12","d: None of the above","a: 2"));
        list.add(new QuestionMosel("Can a class be immutable in android?","a: NO","b: YES","c: None of the above","d: make the class as final class","b: YES"));
        list.add(new QuestionMosel("Time limit of broadcast receiver in android?","a: 10 sec","b: 30 sec","c: 56 sec","d: 1 hour","a: 10 sec"));
        list.add(new QuestionMosel("Android Is Based On Which Kernal","a: Linux","b: windows","a & b","None of the above","a: Linux"));

        for(int i=0 ; i <4 ;i++)
        {
            option.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkAnswer((Button)v);

                }
            });
        }


        playAnim(questions,0,list.get(position).getQuestion());
       nextbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               nextbtn.setEnabled(false);
               nextbtn.setAlpha(0.7f);
               enableOption(true);
               position++;
               if(position == list.size())
               {
                   Intent scoreintent = new Intent(QuestionActivity.this,scoreActivity.class);
                   scoreintent.putExtra("score",score);
                   scoreintent.putExtra("total",list.size());
                   startActivity(scoreintent);
                   finish();
                   return;

               }
               count=0;
               playAnim(questions,0,list.get(position).getQuestion());
           }
       });




    }

    private void playAnim(final View view, final int value, final String data)
    {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 1 && count <4) {
                    String options="";
                    if(count ==0)
                    {
                        options=list.get(position).getOptionA();

                    }
                    else if (count == 1)
                    {
                        options=list.get(position).getOptionB();
                    }
                    else if (count == 2)
                    {
                        options=list.get(position).getOptionC();
                    }
                    else if (count == 3)
                    {
                        options=list.get(position).getOptionD();
                    }

                        playAnim(option.getChildAt(count), 0, options);
                        count++;

                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                if(value == 0)
                {
                    ((TextView)view).setText(data);
                    noindicate.setText(position+1+"/"+list.size());
                    try{

                    }catch (ClassCastException ex)
                    {
                        ((Button)view).setText(data);

                    }
                    view.setTag(data);

                    playAnim(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @SuppressLint("NewApi")
    private void checkAnswer(Button selectOption)
    {
        enableOption(false);
        nextbtn.setEnabled(true);
        nextbtn.setAlpha(1);
        if(selectOption.getText().toString().equals(list.get(position).getCorrectans()))
        {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46eb72")));
            score++;

        }else
        {
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fc0015")));
            Button correctOption = (Button) option.findViewWithTag(list.get(position).getCorrectans());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46eb72")));

        }

    }
@SuppressLint("NewApi")
private void enableOption(boolean enable)
{
    for(int i=0 ; i <4 ;i++)
    {
        option.getChildAt(i).setEnabled(enable);
        if(enable)
        {
            option.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#46eb72")));
        }
    }
}

}
