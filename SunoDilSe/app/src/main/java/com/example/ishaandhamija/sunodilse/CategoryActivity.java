package com.example.ishaandhamija.sunodilse;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView rvList2;
    ArrayList<String> feedList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        feedList2 = new ArrayList<>();

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] proj1 = {"distinct " + MediaStore.Audio.Media.ARTIST};

       /* Cursor musicCursor = musicResolver.query(musicUri, proj1, null, new String[]{""=""}, null);

        if(musicCursor == null){
            Toast.makeText(this, "No Songs", Toast.LENGTH_SHORT).show();
            Log.d("", "getSongList: No Songs");
        }

        if (musicCursor.moveToFirst()) {
            do {
                Log.d("Hello", "getSongList: " + musicCursor.getString(0));
                feedList2.add(musicCursor.getString(0));
            }while(musicCursor.moveToNext());
        }
        else {
            Log.d("Hello", "getSongList: No Artists");
        }
*/
        rvList2 = (RecyclerView) findViewById(R.id.rvList2);
        FeedAdapter feedAdapter = new FeedAdapter();

        rvList2.setLayoutManager(new LinearLayoutManager(this));

        rvList2.setAdapter(feedAdapter);

    }

    class FeedHolder extends RecyclerView.ViewHolder{

        TextView catName;

        public FeedHolder(View itemView) {
            super(itemView);

            this.catName = (TextView) itemView.findViewById(R.id.catName);
        }
    }

    class FeedAdapter extends RecyclerView.Adapter<FeedHolder>{

        @Override
        public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView = li.inflate(R.layout.smallview3, parent, false);

            return new FeedHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FeedHolder holder, int position) {

            final String feed = feedList2.get(position);

            holder.catName.setText(feed.toString());

        }

        @Override
        public int getItemCount() {
            return feedList2.size();
        }
    }
}
