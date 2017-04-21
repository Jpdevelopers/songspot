package com.example.ishaandhamija.sunodilse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.security.Permission;

public class DifferentPlaylistsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 3065;
    ImageView songs, artists, genres, albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_playlists);

        songs = (ImageView) findViewById(R.id.songs);
        artists = (ImageView) findViewById(R.id.artists);
        genres = (ImageView) findViewById(R.id.genres);
        albums = (ImageView) findViewById(R.id.albums);

        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE))== PackageManager.PERMISSION_DENIED && (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(DifferentPlaylistsActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
        }
        else{
            activityStart();
        }
    }

    private void activityStart() {
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifferentPlaylistsActivity.this, PlaylistActivity.class);
                startActivity(i);
            }
        });

        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifferentPlaylistsActivity.this, CategoryActivity.class);
                i.putExtra("pos",1);
                startActivity(i);
            }
        });

        genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifferentPlaylistsActivity.this, CategoryActivity.class);
                i.putExtra("pos",2);
                startActivity(i);
            }
        });

        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifferentPlaylistsActivity.this, CategoryActivity.class);
                i.putExtra("pos",3);
                startActivity(i);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE){
            if(grantResults[1]==PackageManager.PERMISSION_GRANTED){
                activityStart();
            }else{
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
