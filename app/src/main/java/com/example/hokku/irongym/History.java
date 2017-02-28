package com.example.hokku.irongym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.List;


public class History extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);







        ListAdapter historyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gymprogram_a.datetimelist);
        ListView historyListVeiw = (ListView) findViewById(R.id.historyListView);
        historyListVeiw.setAdapter(historyAdapter);


        historyListVeiw.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        );


    }
}