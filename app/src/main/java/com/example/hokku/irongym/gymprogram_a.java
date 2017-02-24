package com.example.hokku.irongym;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class gymprogram_a extends AppCompatActivity {

    DBHandler dbHandler;


    String sSquatWeight = null;

    String sCalfRaiseWeight1 = null;

    String exerciseHeader = "";


/*
    static String year;
    static String month;
    static String day;
    static String hour;
    static String minute;
    */
    static String sDateTime;


    //Sätt till antalet övningar som är definerade i activity_gymprogram_a.xml
    int iNoOfRows = 0;
    //Antalet celler (vikt+reps) som är definerade i activity_gymprogram_a.xml
    int iNoOfCells; //= 6;

    int iNoOfSaves;

    static String[][] sTabellArr = new String[15][15];
    //TODO: Begränsa så att antalet rader inte blir fler än vad arrayen klarar!

//--------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);

        dbHandler = new DBHandler(this, null, null, 1);

    //Skapar nya rader
        addNewRows(5,6);


        /*
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
        */
    }


    @Override
    protected void onResume() {
        super.onResume();

        //printToTextView(iNoOfSaves + "");
    }


//--------------------------------------------------------------------------------------------------
//Körs när man trycker på knappen SPARA
    public void saveExercise(View view) {

        String id0 = "10";

        SQL sqll = new SQL("test");

        EditText edExercise = (EditText) findViewById(Integer.parseInt(id0));
        EditText edWeight = (EditText) findViewById(Integer.parseInt("01"));
        EditText edReps = (EditText) findViewById(Integer.parseInt("02"));
        SQL exercise = new SQL(edExercise.getText().toString());
        /*SQL weight = new SQL(edWeight.getText().toString());
        SQL reps = new SQL(edReps.getText().toString());*/
        dbHandler.addExercise(exercise);
        printDatabase();


/*
        for (int row = 4; row <= iNoOfRows; row++) {
            for (int cell = 0; cell <= 10; cell++) {
                String id = row + "" + cell;


                //Kollar om data skall sparas från "Text" eller "Hint"
                try {
                    EditText edText = (EditText) findViewById(Integer.parseInt(id));
                    if (edText.getText().toString() != null && !edText.getText().toString().equals("")) {
                        sTabellArr[row][cell] = edText.getText().toString();
                    } else if (edText.getText().toString() != null && edText.getText().toString().equals("")) {
                        sTabellArr[row][cell] = edText.getHint().toString();
                    }
                } catch (Exception e) {

                }

                /*
                if (sTabellArr[row][cell] != null) {
                    String id = row + "" + cell;
                    EditText edText = (EditText) findViewById(Integer.parseInt(id));
                    if (!edText.getText().toString().equals("")) {
                        printToTextView(edText.getText().toString());
                    }
                    //printToTextView(id +"");
                    //sTabellArr[row][cell] = row + "." + cell;
                    //printToTextView(sTabellArr[row][cell]);
                    //*
                }
            }
        }


        Calendar cal = Calendar.getInstance();
        /*year = Integer.toString(cal.get(Calendar.YEAR));
        month = (Integer.toString(cal.get(Calendar.MONTH) + 1));
        if (cal.get(Calendar.MONTH) < 10){ month = 0 + month; }
        day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        if (cal.get(Calendar.DAY_OF_MONTH) < 10){ day = 0 + day; }
        hour = Integer.toString(cal.get(Calendar.HOUR));
        minute = Integer.toString(cal.get(Calendar.MINUTE));

        SimpleDateFormat sdf = new  SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        sDateTime = sdf.format(cal.getTime());

        SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        iNoOfSaves++;
        Save.saveValue(exercise_a, "iNoOfSaves", Integer.toString(iNoOfSaves));
        Save.saveExercise(exercise_a);

        //sDateTime = year + month + day;
        //sDateTime = year + month + day + "\n" + hour +":"+ minute;

        Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show();
        */
    }

//--------------------------------------------------------------------------------------------------
//Körs när man trycker på knappen VISA
    public void showOldValue(View view) {
        dbHandler.dropTable();
        printDatabase();
        //SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        //printToTextView(Save.showOldValue(exercise_a) + "");
    }

//--------------------------------------------------------------------------------------------------
//Körs när man trycker på +. Lägger till en ny rad med X antal celler i tabellen.
    public void addExercise (View view) {
        EditText etSett = (EditText) findViewById(R.id.noOfSett);
        //Hämtar Sett värdet för antal celler som skall byggas.
        if (etSett.getText().toString().equals("")) {
            iNoOfCells = 4;
        } else if (Integer.parseInt(etSett.getText().toString()) > 4) {
            //Nödlösning tills sCellId är fixad (Se problem nedan).
            //Kollar orienteringen på telefonen och sätter olika max antal celler beroende på riktning.
            if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                iNoOfCells = 8;
            } else {
                iNoOfCells = 6;
            }
        } else {
            iNoOfCells = Integer.parseInt(etSett.getText().toString()) * 2;
        }
//TODO: Problem när iNoOfCells är större än 10 för då mixas rad och cellvärden i sCellId = svårt att läsa ut! Ändra till double?

        newRow(iNoOfCells, "");
    }

//--------------------------------------------------------------------------------------------------
//Skapar flera nya rader
    void addNewRows(int iNewRows,int iCells) {
        Resources resource = getResources();
        String[] exerciseSuggestion = resource.getStringArray(R.array.exerciseSuggestions);
        int iExerciseSuggLen = exerciseSuggestion.length;

        for (int row = 0; row<iNewRows; row++) {
            if (iExerciseSuggLen>row) {
                newRow(iCells, exerciseSuggestion[row]);
            } else {
                newRow(iCells, "");
            }
        }
    }

//--------------------------------------------------------------------------------------------------
//Skapar en ny rad
    void newRow(int iNoOfCells, String exerciseSuggestion) {
        if (iNoOfCells > 0) {
//          int inputLength = 1;
            iNoOfRows++;
            String sOut = "Antal rader: " + iNoOfRows;

            //Sätter tabelldata
            TableLayout table = (TableLayout) findViewById(R.id.table1);
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            int cell = 0;
            String sCellId = iNoOfRows + "" + cell;

            //Första cellen - Träningsmomentets namn
            EditText etHeaderExercise = new EditText(this);
            if (!exerciseSuggestion.equals("")) {
                etHeaderExercise.setHint(exerciseSuggestion);
                etHeaderExercise.setHintTextColor(Color.BLACK);
            } else {
                etHeaderExercise.setHint(R.string.exerciseHeader);
            }
            etHeaderExercise.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            etHeaderExercise.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            etHeaderExercise.setId(Integer.parseInt(sCellId));
            etHeaderExercise.setNextFocusDownId((Integer.parseInt(sCellId) + 1));
            row.addView(etHeaderExercise);

            //Skapar så många Vikt och Reps fält som är definerat i iNoOfCells.
            for (cell = 1; cell <= iNoOfCells; cell++) {
                sCellId = iNoOfRows + "" + cell;
                EditText newWeight = new EditText(this);
                newWeight.setHint("20");
                //newWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newWeight.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newWeight.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            //Sätter ID för cellen/EditText.
                newWeight.setId(Integer.parseInt(sCellId));
                newWeight.setNextFocusDownId((Integer.parseInt(sCellId) + 1));
                row.addView(newWeight);

                sCellId = iNoOfRows + "" + ++cell;
                EditText newReps = new EditText(this);
                newReps.setHint("10");
                //newReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newReps.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newReps.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            //Sätter ID för cellen/EditText.
                newReps.setId(Integer.parseInt(sCellId));
                if (cell < iNoOfCells) {
                    newReps.setNextFocusDownId((Integer.parseInt(sCellId) + 1));
                } else {
                    //NextFocus vid radbrytning.
                    newReps.setNextFocusDownId(((Integer.parseInt(sCellId) + 9)/10)*10);
                }
                row.addView(newReps);
            }



        /* //Test för att begränsa input
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(inputLength);
        input1.setFilters(FilterArray);
        //input1.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(inputLength)});
        */

            //Infogar ny rad
            table.addView(row);
            printToTextView(sOut);
        }
    }

//--------------------------------------------------------------------------------------------------

    public void printDatabase(){
        String dbString = dbHandler.dbToString();
        printToTextView(dbString);
    }

    //Visar en String i TextView mellan knapparna "Spara" och "Visa"
    void printToTextView(String output) {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(output);
    }

    @Override
    protected void onPause() {
        //SharedPreferences exercise_a = getSharedPreferences("exerciseA", Context.MODE_PRIVATE);
        //Save.saveValue(exercise_a, "iNoOfSaves", Integer.toString(iNoOfSaves));
        super.onPause();
    }
}

//TODO: Spara när man vrider skärmen.