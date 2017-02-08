package com.example.hokku.irongym;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class gymprogram_a extends AppCompatActivity {


    String sKnabojSet = "1";


    static int iTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);


        final EditText etKnabojSet  = (EditText) findViewById(R.id.knabojSet);
        final EditText etSquatsWeight1 = (EditText)findViewById(R.id.squatsWeight1);
        final EditText etKnabojReps       = (EditText) findViewById(R.id.knabojReps);

        final TextView tv = (TextView) findViewById(R.id.textView);

        final SharedPreferences sharedPref = getSharedPreferences("test", 0);
        final SharedPreferences.Editor editPref = sharedPref.edit();

        //Hämtar värdet från
        String test = sharedPref.getString("tag", "");

        if (test.length() > 0) {
            tv.setText("Sparat värde: " + test);
        }


        EditText etRoddReps  = (EditText) findViewById(R.id.roddReps);



        final String sKnabojRepsHint = etKnabojReps.getHint().toString();


        etKnabojSet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    sKnabojSet = etKnabojSet.getText().toString();
                    if (sKnabojSet.length() > 0) {
                        etKnabojReps.setHint(sKnabojSet);
                      
                        editPref.putString("tag", sKnabojSet).apply();

                        tv.setText(sharedPref.getString("tag", ""));
                    } else {
                        etKnabojReps.setHint(sKnabojRepsHint);
                    }

                }
            }
        });

    }
}