package com.example.hokku.irongym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hokku on 2017-03-05.
 */

public class Del_db extends AppCompatActivity {
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_db);
        dbHandler = new DBHandler(this, null, null, 1);
    }
    public void deleteDB(View view) {
        dbHandler.dropTable();
        Toast.makeText(this, R.string.dbEmpty, Toast.LENGTH_SHORT).show();
    }
}