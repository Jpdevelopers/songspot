package com.example.ishaandhamija.sunodilse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CheckListActivity extends AppCompatActivity {

    RecyclerView rvList3;
    ArrayList<Check> feedList3;

    RecyclerView rvList4;
    ArrayList<Check> feedList4;

    ArrayList<Check> genreList;
    ArrayList<Check> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        feedList3 = new ArrayList<>();
        feedList3.add(new Check("Rock", false));
        feedList3.add(new Check("Pop", false));
        feedList3.add(new Check("Club", false));

        rvList3 = (RecyclerView) findViewById(R.id.rvList3);
        FeedAdapter feedAdapter = new FeedAdapter();

        rvList3.setLayoutManager(new LinearLayoutManager(this));

        rvList3.setAdapter(feedAdapter);

        feedList4 = new ArrayList<>();
        feedList4.add(new Check("Arijit Singh", false));
        feedList4.add(new Check("", false));
        feedList4.add(new Check("Arijit Singh", false));

        genreList = new ArrayList<>();
        artistList = new ArrayList<>();

    }

    class FeedHolder extends RecyclerView.ViewHolder{

        TextView genreName;
        CheckBox ticked;

        public FeedHolder(View itemView) {
            super(itemView);

            this.genreName = (TextView) itemView.findViewById(R.id.genreName);
            this.ticked = (CheckBox) itemView.findViewById(R.id.ticked);

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
        public void onBindViewHolder(final FeedHolder holder, int position) {

            final Check feed = feedList3.get(position);

            holder.genreName.setText(feed.getName());
            holder.ticked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    feed.setTicked(isChecked);
                }
            });

            if (holder.ticked.isChecked() == true){
                artistList.add(feedList3.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return feedList3.size();
        }
    }
}
