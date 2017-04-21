package com.example.ishaandhamija.sunodilse;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CustomActivity extends AppCompatActivity {
    SongsDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        dataBase=new SongsDataBase(CustomActivity.this);
        Cursor ans=dataBase.getSortedData();
        ans.moveToFirst();
        while(ans.moveToNext()){
            Log.d("DATA", "onCreate: "+ans.getString(0));
            Log.d("DATA", "onCreate: "+ans.getString(1));
            Log.d("DATA", "onCreate: "+ans.getInt(4));
        }
    }
}
