package com.example.hokku.irongym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "irongym.db";
    static final String TABLE_PRODUCTS = "exercise";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_EXERCISENAME = "exerciseName";
    static final String COLUMN_WEIGHT = "weight";
    static final String COLUMN_REPS = "reps";
    static final String COLUMN_DATETIME = "date";
    static final String COLUMN_TIME = "time";

    DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Gör ett query androp som skapar en ny tabell första gången den körs.
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXERCISENAME + " VARCHAR(30) " +
                ");";
        db.execSQL(query);

        /*
        COLUMN_REPS + " TEXT, " +
                COLUMN_WEIGHT + " TEXT, " +
                COLUMN_DATETIME + " DATETIME NOT NULL DEFAULT GETDATE()" +
                */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Körs om versionen uppdateras.
        //Tar bort hela tabellen och bygger en ny.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Lägg till ny rad i DB
    void addExercise(SQL sql) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISENAME, sql.get_exercise());
        //values.put(COLUMN_REPS, sql.get_reps());
        //values.put(COLUMN_WEIGHT, sql.get_weight());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    void deleteRecord(String rowToDelete){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_EXERCISENAME + "=\"" + rowToDelete);
    }

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
                dbString += c.getString(c.getColumnIndex("exerciseName"));
                //dbString += " " + c.getString(c.getColumnIndex("weight"));
                //dbString += " " + c.getString(c.getColumnIndex("reps"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbString;
    }

}