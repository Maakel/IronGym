package com.example.hokku.irongym;

import android.content.Intent;
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

    static String sDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final ListAdapter historyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gymprogram_a.datetimelist);
        ListView historyListView = (ListView) findViewById(R.id.historyListView);
        historyListView.setAdapter(historyAdapter);

        final Intent DisplayHistory = new Intent(this, gymprogram_a.class);

        historyListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        SQL.setDateTime(historyAdapter.getItem(position).toString());

                        SQL.setShowHistory(Boolean.TRUE);
                        startActivity(DisplayHistory);
                    }
                }
        );

    }


}