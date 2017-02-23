package com.example.hokku.irongym;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.hokku.irongym.R.string.set;

public class gymprogram_b extends AppCompatActivity {


    String myDateandtime;
    Set<String> oldSet;
    Set<String> newSet;
    List<String> aL_dateandtime;
    Set<String> fetch;

    String dlw1;
    String dlr1;
    List<String> test;

    TextView myTextView;
    EditText deadliftWeight1;
    EditText deadliftReps1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_b);
        newSet = new HashSet<String>();




        myTextView = (TextView) findViewById(R.id.textViewTest);
        deadliftWeight1 = (EditText) findViewById(R.id.deadliftWeight1);
        deadliftReps1 = (EditText) findViewById(R.id.deadliftReps1);


    }

    public void showHistory (View view){

        SharedPreferences sharedpref = getSharedPreferences("dateandtime", Context. MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();


        fetch = sharedpref.getStringSet("DateandTime", null); // getStringSet("dateList", null);

        aL_dateandtime = new ArrayList<String>(fetch);




        test = new ArrayList<String>();
        test.add("natis");
        test.add("mik");

        for(int i = 0 ; i < test.size() ; i++){
            Log.d("test",test.get(i));
            //myTextView.setText(test.get(i));

        }

        //Intent intent = new Intent(this, History.class);
        //startActivity(intent);
    }

    public void saveData (View view){

        SharedPreferences sharedpref = getSharedPreferences("dateandtime", Context. MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();

        //oldSet = new HashSet<String>();

        //Get the old values
        //oldSet = sharedpref.getStringSet("DateandTime", null);

        //Calendar

        /*Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new  SimpleDateFormat ("yyyy/MM/dd    HH:mm:ss");
        myDateandtime = sdf.format(cal.getTime());*/
        //Make copy of oldSet and update

        dlw1 = deadliftWeight1.getText().toString();
        dlr1 = deadliftReps1.getText().toString();

        newSet.add(dlw1 + dlr1);
        //newSet.add(dlr1);
        //newSet.addAll(oldSet);





        editor.putStringSet("DateandTime", newSet);
        editor.commit();

    }
}
