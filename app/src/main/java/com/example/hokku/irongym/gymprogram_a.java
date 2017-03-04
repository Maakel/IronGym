package com.example.hokku.irongym;

import android.content.Intent;
import android.graphics.Color;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;

public class gymprogram_a extends AppCompatActivity {

    DBHandler dbHandler;

    //Sätt till antalet övningar som är definerade i activity_gymprogram_a.xml
    int iNoOfRows = 0;
    //Antalet celler (vikt+reps) som är definerade i activity_gymprogram_a.xml
    int iNoOfCells; //= 6;
    int iMaxNoOfCells = 10;
    String sDateTime;
    public static ArrayList datetimelist;

//--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram_a);
        //Döljer keyboard vid start.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dbHandler = new DBHandler(this, null, null, 1);


        if (SQL.getShowHistory()==Boolean.FALSE) {
            //Skapar nya rader, celler.
            addNewRows(5,6);
            //TODO: Sätt dessa via inställningar i appen.
        } else if (SQL.getShowHistory()==Boolean.TRUE){
            //Visar historik.
            showHistory();
        }

    }

//--------------------------------------------------------------------------------------------------
    @Override
    protected void onResume() {
        super.onResume();

    }

//--------------------------------------------------------------------------------------------------
//Körs när man trycker på knappen SPARA
    public void saveExercise(View view)  {

        //Skapar en tidsstämpel när man trycker på spara knappen.
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String sDateTime = sdf.format(cal.getTime());
        SQL.setDateTime(sDateTime + ",");

        //Skannar igenom hela tabellen
        for (int row = 1; row <= iNoOfRows; row++) {
            //Körs för varje ny rad
            String sExerciseMemory = "";
            String sWeightMemory = "";
            String sRepsMemory = "";

            for (int cell = 0; cell <= iMaxNoOfCells; cell++) {
                //Körs för varje ny cell
                String id = row + "" + cell;

                //Kollar om data skall sparas från "Text" eller "Hint"
                try {
                    EditText edText = (EditText) findViewById(Integer.parseInt(id));

                    if (!edText.getText().toString().equals("") || !edText.getHint().toString().equals("")) {
                        if (edText.getText().toString() != null && !edText.getText().toString().equals("")) {
                            //Om edittext är ifylld av användaren körs getText.

                            if (cell == 0) {
                                sExerciseMemory = edText.getText().toString();
                            } else if (cell % 2 != 0) { //Läser celler med ojämna nummer.
                                sWeightMemory += edText.getText().toString() + ",";
                            }else if (cell % 2 == 0 && cell % 10 != 0) { //Läser celler med jämna nummer men som inte har sista siffran 0.
                                sRepsMemory += edText.getText().toString() + ",";
                            }

                        } else if (edText.getText().toString() != null && edText.getText().toString().equals("")) {
                            //Om edittext inte är ifylld körs getHint.
                            if (cell == 0) {
                                SQL.setExercise(edText.getHint().toString());
                            } else if (cell % 2 != 0) { //Läser celler med ojämna nummer.
                                sWeightMemory += edText.getHint().toString() + ",";
                            }else if (cell % 2 == 0 && cell % 10 != 0) { //Läser celler med jämna nummer men som inte har sista siffran 0.
                                sRepsMemory += edText.getHint().toString() + ",";
                            }
                        }
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

                }
                */
            }

            if (!sWeightMemory.equals("") || !sRepsMemory.equals("") || !sExerciseMemory.equals("")) {
                SQL.setExercise(sExerciseMemory);
                SQL.setWeight(sWeightMemory);
                SQL.setReps(sRepsMemory);
                dbHandler.saveExerciseRow();
            }
        }

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        //printDatabase();
    }

//--------------------------------------------------------------------------------------------------
//Körs när man trycker på knappen VISA
    public void showOldValue(View view) {
        Intent intent = new Intent(this, History.class);


        String dbDateString = dbHandler.dbDateToString();
        datetimelist = new ArrayList<String>(Arrays.asList(dbDateString.split("\\s*,\\s*")));
        startActivity(intent);
        //printDateDatabase();


        //showHistory();
    }

//--------------------------------------------------------------------------------------------------
//Körs när man trycker på knappen "Del DB"
    public void deleteDB(View view) {
        dbHandler.dropTable();
        printToTextView("Databasen är tömd!");
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
        int iNoOfExerciseSuggestion = exerciseSuggestion.length;

        for (int row = 0; row<iNewRows; row++) {
            if (iNoOfExerciseSuggestion>row) {
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
                if (iNoOfRows<=2) {
                    newWeight.setHint("20");
                } else {
                    newWeight.setHint("");}
                //newWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                newWeight.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                newWeight.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            //Sätter ID för cellen/EditText.
                newWeight.setId(Integer.parseInt(sCellId));
                newWeight.setNextFocusDownId((Integer.parseInt(sCellId) + 1));
                row.addView(newWeight);

                sCellId = iNoOfRows + "" + ++cell;
                EditText newReps = new EditText(this);
                if (iNoOfRows<=2) {
                    newReps.setHint("10");
                } else {
                    newReps.setHint("");}
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
            scrollDown();
        }
    }

//--------------------------------------------------------------------------------------------------
//Hämtar värden från databasen och sätter dom i tabellen.
    void showHistory() {

        String sDbExerciseColName = DBHandler.COLUMN_EXERCISENAME;
        String sDbWeightColName = DBHandler.COLUMN_WEIGHT;
        String sDbRepsColName = DBHandler.COLUMN_REPS;
        int iDbRows = dbHandler.countRows();
        if (iNoOfRows < iDbRows) {addNewRows((iDbRows - iNoOfRows), 6);}
        //TODO: Ta reda på antalet celler som krävs.

        for (int iRow = 1; iRow <= iNoOfRows; iRow++) {
            int iCount = 0;

            for (int iCell = 0; iCell <= iMaxNoOfCells; iCell++) {
                String sDbAnswer = "";
                String sId = iRow + "" + iCell;
                EditText edText = (EditText) findViewById(Integer.parseInt(sId));
                ArrayList alDbBuff;

                if (iCell == 0) {
                    sDbAnswer = dbHandler.getDbRow(iRow-1, sDbExerciseColName);
                    edText.setText(sDbAnswer);

                } else if (iCell % 2 != 0) { //Läser celler med ojämna nummer.
                    sDbAnswer = dbHandler.getDbRow(iRow-1, sDbWeightColName);
                    alDbBuff = new ArrayList<String>(Arrays.asList(sDbAnswer.split("\\s*,\\s*")));
                    try {
                        edText.setText(alDbBuff.get((iCount)).toString());
                    } catch (Exception e) {     }

                }else if (iCell % 2 == 0 && iCell % 10 != 0) { //Läser celler med jämna nummer men som inte har sista siffran 0.
                    sDbAnswer = dbHandler.getDbRow(iRow-1, sDbRepsColName);
                    alDbBuff = new ArrayList<String>(Arrays.asList(sDbAnswer.split("\\s*,\\s*")));
                    try {
                        edText.setText(alDbBuff.get((iCount)).toString());
                        iCount++;
                    } catch (Exception e) {     }
                }
            }
        }
    }

//--------------------------------------------------------------------------------------------------
//Scrollar ned till botten av sidan.
    void scrollDown() {
        final ScrollView scroll = (ScrollView) findViewById(R.id.activity_gymprogram_a);
        scroll.postDelayed(new Runnable() {
            public void run() {scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 50L);
    }

//--------------------------------------------------------------------------------------------------
//Skriver ut databasen till TextView
    public void printDatabase(){
        String dbString = dbHandler.dbToString();
        printToTextView(dbString);
    }

//--------------------------------------------------------------------------------------------------
//Hämtar spardatumet från databasen
    public void printDateDatabase() {

    }

//--------------------------------------------------------------------------------------------------
    //Visar en String i TextView placerad under knappraden.
    void printToTextView(String output) {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(output);
    }

//--------------------------------------------------------------------------------------------------
    @Override
    protected void onPause() {

        super.onPause();
    }
}

//TODO: Spara när man vrider skärmen.