package com.example.hokku.irongym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void pass_a(View view){
        Intent intent = new Intent(this, gymprogram_a.class);

                startActivity(intent);
    }
    public void pass_b(View view){
        Intent intent = new Intent(this, gymprogram_b.class);

        startActivity(intent);
    }
}
