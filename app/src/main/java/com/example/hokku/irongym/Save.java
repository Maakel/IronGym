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

import java.util.Locale;


public class Save extends AppCompatActivity {

    static String[] squatsArrayWeight = new String[5];
    int[] benchpressArrayWeight = new int[5];
    int[] rowArrayWeight = new int[5];
    static String[] calfraiseArrayWeight = new String[4];
    static String[] squatsArrayReps = new String[4];
    //static String sDateTimeSave;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

   /* public static void date(){
        Calendar cal = Calendar.getInstance();
         year = cal.get(Calendar.YEAR);
    }*/


    public static void saveValue(SharedPreferences exercise_a, String sKey, String sValue) {
        SharedPreferences.Editor edit = exercise_a.edit();
        edit.putString(sKey, sValue);
        edit.apply();
    }

    public static String getValue(SharedPreferences exercise_a, String sKey) {
        String value;
        value = exercise_a.getString(sKey,"");
        return value;
    }


    // Save value of exercise.
    public static void saveExercise(SharedPreferences exercise_a){

        SharedPreferences.Editor editor = exercise_a.edit();


        editor.putString("squats1", squatsArrayWeight[0]);
        editor.putString("squats2", squatsArrayWeight[1]);
        editor.putString("squats3", squatsArrayWeight[2]);
        editor.putString("squats4", squatsArrayWeight[3]);
        editor.putString("squats5", squatsArrayWeight[4]);
        editor.putString("datetime", gymprogram_a.sDateTime);

        //CalfRaiseWeight

        editor.putString("CalfRaiseWeight1", calfraiseArrayWeight[0]);

        editor.apply();


    //Början på sparfunktion för en array
        SharedPreferences.Editor editArray = exercise_a.edit();
        editArray.putInt("arraySize", gymprogram_a.sTabellArr.length);
        for (int cell=0; cell < gymprogram_a.sTabellArr.length; cell++) {

        }


    }

    // Print out saved data in  textview

    public static String showOldValue(SharedPreferences exercise_a){

        String sq1 = exercise_a.getString("squats1","");
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
