package com.example.ishaandhamija.sunodilse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class DifferentPlaylistsActivity extends AppCompatActivity {

    ImageView songs, artists, genres, albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_playlists);

        songs = (ImageView) findViewById(R.id.songs);
        artists = (ImageView) findViewById(R.id.artists);
        genres = (ImageView) findViewById(R.id.genres);
        albums = (ImageView) findViewById(R.id.albums);

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

}
