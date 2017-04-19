package com.example.ishaandhamija.sunodilse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class DifferentPlaylistsActivity extends AppCompatActivity {

    TextView songs, artists, genres, albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_playlists);

        songs = (TextView) findViewById(R.id.songs);
        artists = (TextView) findViewById(R.id.artists);
        genres = (TextView) findViewById(R.id.genres);
        albums = (TextView) findViewById(R.id.albums);

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
