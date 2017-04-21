package com.example.ishaandhamija.sunodilse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ishaandhamija on 21/04/17.
 */

public class SongsDataBase extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "songs";
    Context context;
    public SongsDataBase(Context context) {
        super(context, "songsDataBase.db", null, 1);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME +
        " (ID TEXT,"+
        "TITLE TEXT,"+
        "ARTIST TEXT,"+
        "GENRE TEXT,"+
        "WEIGHT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(long id,String title,String artist,String genre){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",""+id);
        contentValues.put("TITLE",title);
        contentValues.put("ARTIST",artist);
        contentValues.put("GENRE",genre);
        contentValues.put("WEIGHT",0);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        return true;
    }
    public void updateWeightByArtist(String artist){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET WEIGHT = WEIGHT + 1 WHERE ARTIST = "+artist);
        Toast.makeText(context, "WEIGHT UPDATED BY ARTIST", Toast.LENGTH_SHORT).show();
    }
    public void updateWeightByGenre(String genre){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET WEIGHT = WEIGHT + 1 WHERE GENRE = "+genre);
        Toast.makeText(context, "WEIGHT UPDATED BY GENRE", Toast.LENGTH_SHORT).show();
    }
    public Cursor getSortedData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT TITLE FROM "+TABLE_NAME+" WHERE WEIGHT>0 ORDER BY WEIGHT DESC",null);
        return res;
    }
    public Cursor getTitle(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor ans=db.rawQuery("SELECT TITLE FROM "+TABLE_NAME,null);
        return ans;
    }
}
