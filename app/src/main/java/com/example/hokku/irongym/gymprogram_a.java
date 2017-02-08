package com.example.hokku.irongym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        EditText etRoddReps  = (EditText) findViewById(R.id.roddReps);


        final String sKnabojRepsHint = etKnabojReps.getHint().toString();

        etKnabojSet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    sKnabojSet = etKnabojSet.getText().toString();
                    if (sKnabojSet.length() > 0) {
                        etKnabojReps.setHint(sKnabojSet);
                        Save.squatsArrayWeight[0]= Integer.parseInt(sKnabojSet);
                    } else {
                        etKnabojReps.setHint(sKnabojRepsHint);
                    }

                }
            }
        });

    }
}