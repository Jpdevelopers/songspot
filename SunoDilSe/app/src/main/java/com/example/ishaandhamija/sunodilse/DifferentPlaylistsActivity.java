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

    RecyclerView rvList;
    ArrayList<Playlist> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_playlists);

        feedList = new ArrayList<>();
        feedList.add(new Playlist("All Songs"));
        feedList.add(new Playlist("Artists"));
        feedList.add(new Playlist("Genres"));

        rvList = (RecyclerView) findViewById(R.id.playlistList);
        FeedAdapter feedAdapter = new FeedAdapter();

        rvList.setLayoutManager(new GridLayoutManager(this,2));

        rvList.setAdapter(feedAdapter);

    }

    class FeedHolder extends RecyclerView.ViewHolder{

        TextView name;
        View view;

        public FeedHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.view = itemView;
        }
    }

    class FeedAdapter extends RecyclerView.Adapter<FeedHolder>{

        @Override
        public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView = li.inflate(R.layout.smallview2, parent, false);

            return new FeedHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FeedHolder holder, final int position) {

            final Playlist feed = feedList.get(position);

            holder.name.setText(feed.getName());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = null;
                    if (position == 0) {
                        i = new Intent(DifferentPlaylistsActivity.this, PlaylistActivity.class);
                    }
                    else {
                        i = new Intent(DifferentPlaylistsActivity.this, CategoryActivity.class);
                    }
                    i.putExtra("pos",position);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return feedList.size();
        }
    }

}
