package com.example.hokku.irongym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void createNew(View view) {
        Intent intent = new Intent(this, ProgramName.class);

        startActivity(intent);
    }

    public void pass_b(View view) {
        Intent intent = new Intent(this, gymprogram_b.class);
        SQL.setShowHistory(Boolean.FALSE);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this, History.class);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        String dbDateString = dbHandler.dbDateToString();
        gymprogram_a.datetimelist = new ArrayList<String>(Arrays.asList(dbDateString.split("\\s*,\\s*")));
        startActivity(intent);
    }

    public void del_db(View view){
        Intent intent = new Intent(this, Del_db.class);
        startActivity(intent);
    }


}