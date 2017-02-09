package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class Save extends AppCompatActivity {



    static String[] squatsArrayWeight = new String[5];
    int[] benchpressArrayWeight = new int[5];
    int[] rowArrayWeight = new int[5];
    int[] calfraiseArray = new int[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

    // Save value of exercise.
    public static void saveExercise(SharedPreferences exercise_a){





        SharedPreferences.Editor editor = exercise_a.edit();


       editor.putString("squats1", squatsArrayWeight[0]);
        editor.putString("squats2", squatsArrayWeight[1]);
        editor.putString("squats3", squatsArrayWeight[2]);
        editor.putString("squats4", squatsArrayWeight[3]);
        editor.putString("squats5", squatsArrayWeight[4]);

        editor.commit();

        //Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }

    // Print out saved data in  textview

    public static void showOldValue(SharedPreferences exercise_a){



        String sq1 = exercise_a.getString("squats1","");
        String sq2 = exercise_a.getString("squats2","");
        String sq3 = exercise_a.getString("squats3","");
        String sq4 = exercise_a.getString("squats4","");
        String sq5 = exercise_a.getString("squats5","");

        gymprogram_a.tv.setText(sq1);






    }
}
