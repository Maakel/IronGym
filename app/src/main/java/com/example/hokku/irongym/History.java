package com.example.hokku.irongym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;


public class History extends AppCompatActivity {

   DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        dbHandler = new DBHandler(this, null, null, 1);



        ListAdapter historyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gymprogram_a.datetimelist);
        ListView historyListVeiw = (ListView) findViewById(R.id.historyListView);
        historyListVeiw.setAdapter(historyAdapter);


        historyListVeiw.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String test1 = String.valueOf(parent.getItemAtPosition(position));


                            String test3 = dbHandler.dbHistory(test1);


                        Toast.makeText(History.this, test3,Toast.LENGTH_LONG).show();
                    }
                }
        );


    }
}