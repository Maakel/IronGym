package com.example.hokku.irongym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class gymprogram_a extends AppCompatActivity {

    EditText etSquatsWeight1 = (EditText)findViewById(R.id.squatsWeight1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);



    }
}
