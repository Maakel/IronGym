package com.example.hokku.irongym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProgramName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_name);
    }

    public void next(View view) {
        Intent intent = new Intent(this, gymprogram_a.class);
        SQL.setShowHistory(Boolean.FALSE);
        startActivity(intent);
    }
}
