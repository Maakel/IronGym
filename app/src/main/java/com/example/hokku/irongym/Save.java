package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


public class Save extends AppCompatActivity {

    static String[] squatsArrayWeight = new String[5];
    int[] benchpressArrayWeight = new int[5];
    int[] rowArrayWeight = new int[5];
    static String[] calfraiseArrayWeight = new String[4];
    static String[] squatsArrayReps = new String[4];
    //static String sDateTimeSave;
    static int sTest =0 ;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);




    }



    // Save value of exercise.
    public static void saveExercise(SharedPreferences exercise_a){

        sTest ++;





        SharedPreferences.Editor editor = exercise_a.edit();



       editor.putString(sTest+"squats1", squatsArrayWeight[0]);
        editor.putString("squats2", squatsArrayWeight[1]);
        editor.putString("squats3", squatsArrayWeight[2]);
        editor.putString("squats4", squatsArrayWeight[3]);
        editor.putString("squats5", squatsArrayWeight[4]);
        editor.putString("datetime", gymprogram_a.sDateTime);

        //CalfRaiseWeight

        editor.putString("CalfRaiseWeight1", calfraiseArrayWeight[0]);

        editor.apply();


    }

    // Print out saved data in  textview

    public static String showOldValue(SharedPreferences exercise_a){




        String sq1 = exercise_a.getString(sTest+"squats1","");
        String sq2 = exercise_a.getString("squats2","");
        String sq3 = exercise_a.getString("squats3","");
        String sq4 = exercise_a.getString("squats4","");
        String sq5 = exercise_a.getString("squats5","");

        String crw1 = exercise_a.getString("CalfRaiseWeight1", "");

        String dt = exercise_a.getString("datetime","");


        return (crw1 + dt);

        //gymprogram_a.tv.setText(sq1);






    }
}
