package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class Save extends AppCompatActivity {

    static int[] squatsArrayWeight = new int[5];
    int[] benchpressArrayWeight = new int[5];
    int[] rowArrayWeight = new int[5];
    int[] calfraiseArray = new int[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

    // Save value of exercise.
    public void saveExercise(View view){


        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = exercise_a.edit();
        editor.putString("sguats1", String.valueOf(squatsArrayWeight[0]));
        editor.putString("sguats2", String.valueOf(squatsArrayWeight[1]));
        editor.putString("sguats3", String.valueOf(squatsArrayWeight[2]));
        editor.putString("sguats4", String.valueOf(squatsArrayWeight[3]));
        editor.putString("sguats5", String.valueOf(squatsArrayWeight[4]));

        editor.apply();

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }

    // Print out saved data in edit text box

    public void showOldValue(View view){
        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        String sq1 = exercise_a.getString("squat1","");
        String sq2 = exercise_a.getString("squat2","");
        String sq3 = exercise_a.getString("squat3","");
        String sq4 = exercise_a.getString("squat4","");
        String sq5 = exercise_a.getString("squat5","");

        gymprogram_a.iTest= Integer.parseInt(sq1);
         /*gymprogram_a.      setText(sq2);
        gymprogram_a.      setText(sq3);
        gymprogram_a.      setText(sq4);
        gymprogram_a.      setText(sq5);
        */




    }
}
