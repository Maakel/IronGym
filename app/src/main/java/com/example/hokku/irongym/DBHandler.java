package com.example.hokku.irongym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "irongym.db";
    static final String TABLE_NAME = "exercise";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_EXERCISENAME = "exerciseName";
    static final String COLUMN_WEIGHT = "weight";
    static final String COLUMN_REPS = "reps";
    static final String COLUMN_DATETIME = "date";

    DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Bygger och gör ett query anrop som skapar en ny tabell första gången den körs.
        String query = "CREATE TABLE " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXERCISENAME + " TEXT, " +
                COLUMN_WEIGHT + " TEXT, " +
                COLUMN_REPS + " TEXT, " +
                COLUMN_DATETIME +  " TEXT "/*" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP "*/ +
                ");";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Körs om DATABASE_VERSION uppdateras till ett högre nummer.
        //Tar bort hela tabellen och bygger en ny.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Körs om DATABASE_VERSION ändras till ett lägre nummer.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Tar bort hela tabellen
    void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Skapar en ny rad i DB
    void saveExerciseRow() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISENAME, SQL.getExercise());
        values.put(COLUMN_REPS, SQL.getReps());
        values.put(COLUMN_WEIGHT, SQL.getWeight());
        values.put(COLUMN_DATETIME, SQL.getdateTime());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Tar bort data i tabellen
    void deleteRecord(String rowToDelete){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_EXERCISENAME + "=\"" + rowToDelete);
    }

    //Hämtar data från tabellen
    String dbToString(){
        String dbString ="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        //Databaspekar. Går igenom rad för rad.
        Cursor c = db.rawQuery(query, null);
        //Flyttar pekaren till första raden.
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("exerciseName")) != null) {
                dbString += c.getString(c.getColumnIndex("_id")) + " ";
                dbString += c.getString(c.getColumnIndex("exerciseName"));
                dbString += " " + c.getString(c.getColumnIndex("weight"));
                dbString += " " + c.getString(c.getColumnIndex("reps"));
                //dbString += " " + c.getString(c.getColumnIndex("date"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

    String getDbRow(int iPos,String sColumn){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        c.moveToPosition(iPos);

        if (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("exerciseName")) != null) {
                //dbString += c.getString(c.getColumnIndex("_id")) + " ";
                dbString = c.getString(c.getColumnIndex(sColumn));
                //dbString += " " + c.getString(c.getColumnIndex("weight"));
                //dbString += " " + c.getString(c.getColumnIndex("reps"));
                //dbString += " " + c.getString(c.getColumnIndex("date"));
            }
        }
        c.close();
        db.close();
        return dbString;
    }

    //Hämtar sparade datum från databasen
    String dbDateToString(){
        String dbDateString ="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        //Databaspekar. Går igenom rad för rad.
        Cursor c = db.rawQuery(query, null);
        //Flyttar pekaren till första raden.
        c.moveToFirst();
        String buffer = "";
        String buffer2 = "";

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("exerciseName")) != null) {

                buffer = c.getString(c.getColumnIndex("date")) + " ";
                //Kollar att buffer inte är lika med buffer 2. Detta för att bara läsa ut ett unikt datumvärde.
                if(!buffer2.equals(buffer)){
                    dbDateString += c.getString(c.getColumnIndex("date")) + " ";
                    buffer2 = c.getString(c.getColumnIndex("date")) + " ";
                }
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbDateString;
    }

    public int countRows() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
        int iCounter = c.getCount();
        c.close();
        db.close();
        return iCounter;
    }

}
