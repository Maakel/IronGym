package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class gymprogram_a extends AppCompatActivity {


    String sSquatWeight = null;

    String sCalfRaiseWeight1 = null;

    public static TextView tvTest;


    //Sätt till antalet övningar som är definerade i activity_gymprogram_a.xml
    int iNoOfRows = 4;
    //Antalet celler (vikt+reps) som är definerade i activity_gymprogram_a.xml
    int iNoOfCells; //= 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);

        tvTest = (TextView)findViewById(R.id.textView);




        final EditText etSquatsWeight1 = (EditText) findViewById(R.id.squatsWeight1);
        //final EditText etSquatsReps = (EditText) findViewById(R.id.squatsReps);


        etSquatsWeight1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    sSquatWeight = etSquatsWeight1.getText().toString();

                    if (sSquatWeight.length() > 0) {

                        Save.squatsArrayWeight[0] = sSquatWeight;

                    }

                }
            }
        });

        final EditText etcalfRaiseWeight1 = (EditText) findViewById(R.id.calfraiseWeight1) ;


        etcalfRaiseWeight1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    sCalfRaiseWeight1 = etcalfRaiseWeight1.getText().toString();

                    if (sCalfRaiseWeight1.length() > 0) {

                        Save.calfraiseArrayWeight [0] = sCalfRaiseWeight1;

                    }

                }
            }
        });

    }

    public void saveExercise(View view) {
        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        Save.saveExercise(exercise_a);

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }


    public void showOldValue(View view) {
        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        Save.showOldValue(exercise_a);



    }

//--------------------------------------------------------------------------------------------------

//Körs när man trycker på +. Lägger till en ny rad i tabellen.
    public void addExercise (View view) {
        EditText etSett = (EditText) findViewById(R.id.noOfSett);
        if (etSett.getText().toString().equals("")) {
            iNoOfCells = 4;
        } else {
            iNoOfCells = Integer.parseInt(etSett.getText().toString()) * 2;
        }
//TODO: Problem när iNoOfCells är större än 10 för då mixas rad och cellvärden i sCellId = svårt att läsa ut!


        if (iNoOfCells > 0) {
            //        int inputLength = 1;

            String sOut = "Antal rader: " + ++iNoOfRows;

            //Sätter tabelldata
            TableLayout table = (TableLayout) findViewById(R.id.table1);
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            int cell = 0;
            String sCellId = iNoOfRows + "" + cell;

            //Första cellen - Träningsmomentets namn
            EditText etHeaderExercise = new EditText(this);
            etHeaderExercise.setHint(R.string.exerciseHeader);
            etHeaderExercise.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            etHeaderExercise.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            etHeaderExercise.setId(Integer.parseInt(sCellId));
            row.addView(etHeaderExercise);

            //Skapar så många Vikt och Reps fält som är definerat i iNoOfCells.
            for (cell = 1; cell <= iNoOfCells; cell++) {
                sCellId = iNoOfRows + "" + cell;
                EditText newWeight = new EditText(this);
                newWeight.setHint("1");
                //newWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newWeight.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newWeight.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
                newWeight.setId(Integer.parseInt(sCellId));
                row.addView(newWeight);

                sCellId = iNoOfRows + "" + ++cell;
                EditText newReps = new EditText(this);
                newReps.setHint("10");
                //newReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newReps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newReps.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                newReps.setId(Integer.parseInt(sCellId));
                row.addView(newReps);
                sOut = sCellId + "";
            }



/*
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(inputLength);
        input1.setFilters(FilterArray);
        //input1.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(inputLength)});
        */


            //row.addView(input3);
            table.addView(row);

            //table.removeView(row);


            printToTextView(sOut);
        }
    }

    //Visar en String i TextView mellan knapparna "Spara" och "Visa"
    void printToTextView(String output) {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(output);

    }
}