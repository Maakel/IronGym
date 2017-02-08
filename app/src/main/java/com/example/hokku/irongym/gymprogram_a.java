package com.example.hokku.irongym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class gymprogram_a extends AppCompatActivity {

    String sKnabojSet = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);


        final EditText etKnabojSet  = (EditText) findViewById(R.id.knabojSet);
        final EditText etKnabojVikt       = (EditText) findViewById(R.id.knabojVikt);
        final EditText etKnabojReps       = (EditText) findViewById(R.id.knabojReps);

        final String sKnabojRepsHint = etKnabojReps.getHint().toString();

        etKnabojSet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    sKnabojSet = etKnabojSet.getText().toString();
                    if (sKnabojSet.length() > 0) {
                        etKnabojReps.setHint(sKnabojSet);
                    } else {
                        etKnabojReps.setHint(sKnabojRepsHint);
                    }

                }
            }

        });

    }


}
