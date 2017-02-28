package com.example.hokku.irongym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "irongym.db";
    static final String TABLE_PRODUCTS = "exercise";
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
        //Gör ett query androp som skapar en ny tabell första gången den körs.
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXERCISENAME + " VARCHAR(30), " +
                COLUMN_WEIGHT + " TEXT, " +
                COLUMN_REPS + " TEXT, " +
                COLUMN_DATETIME +  " TEXT "/*" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP "*/ +
                ");";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Körs om versionen uppdateras.
        //Tar bort hela tabellen och bygger en ny.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Tar bort hela tabellen
    void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
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
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Tar bort data i tabellen
    void deleteRecord(String rowToDelete){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_EXERCISENAME + "=\"" + rowToDelete);
    }

    //Hämtar data från tabellen
    String dbToString(){
        String dbString ="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

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
        db.close();
        c.close();
        return dbString;
    }
    String dbDateToString(){
        String dbDateString ="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Databaspekar. Går igenom rad för rad.
        Cursor c = db.rawQuery(query, null);
        //Flyttar pekaren till första raden.
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("exerciseName")) != null) {

                dbDateString += c.getString(c.getColumnIndex("date")) + " ";
                dbDateString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbDateString;
    }
}
