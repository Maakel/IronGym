package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class gymprogram_a extends AppCompatActivity {


    String sSquatWeight = null;

    static TextView tv;

    static int iTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);


        final EditText etSquatsSet = (EditText) findViewById(R.id.squatsSet);
        final EditText etSquatsWeight1 = (EditText) findViewById(R.id.squatsWeight1);
        final EditText etSquatsReps = (EditText) findViewById(R.id.squatsReps);

        tv = (TextView) findViewById(R.id.textView);


        etSquatsWeight1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    sSquatWeight = etSquatsWeight1.getText().toString();

                    if (sSquatWeight.length() > 0) {

                        Save.squatsArrayWeight[0] = sSquatWeight;

                    }

                }
            }
        });

    }

    public void saveExercise(View view) {
        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        Save.saveExercise(exercise_a);

    }


    public void showOldValue(View view) {
        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        Save.showOldValue(exercise_a);

    }
}