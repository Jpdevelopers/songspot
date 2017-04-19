package com.example.ishaandhamija.sunodilse;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    private static final int HOTSPOT_ACTIVITY = 1234;
    RecyclerView rvList;
    public static ArrayList<Song> songList;
    public static MediaPlayer mp = new MediaPlayer();
    //    Button btnPlaylists;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    WifiManager wifi;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        wifi=(WifiManager) getSystemService(Context.WIFI_SERVICE);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFAB();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isHotspotEnable()){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.TetherSettings");
                    intent.setComponent(cn);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent,HOTSPOT_ACTIVITY);
                }
                else{
                    Toast.makeText(PlaylistActivity.this, "Server will be created", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(PlaylistActivity.this, "Fab 1", Toast.LENGTH_SHORT).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlaylistActivity.this, "Fab 2", Toast.LENGTH_SHORT).show();
            }
        });

        songList = new ArrayList<>();
        getSongList();

        rvList = (RecyclerView) findViewById(R.id.rvList);
        FeedAdapter feedAdapter = new FeedAdapter();

        rvList.setLayoutManager(new LinearLayoutManager(this));

        rvList.setAdapter(feedAdapter);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(feedAdapter);

//        btnPlaylists = (Button) findViewById(R.id.btnPlaylists);
//
//        btnPlaylists.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PlaylistActivity.this, DifferentPlaylistsActivity.class));
//            }
//        });
    }

    private boolean isHotspotEnable() {
        Method[] wmMethods = wifi.getClass().getDeclaredMethods();
        for (Method method: wmMethods) {
            if (method.getName().equals("isWifiApEnabled")) {

                try {
                    boolean isWifiAPenabled = (boolean) method.invoke(wifi);
                    return isWifiAPenabled;
                } catch (IllegalArgumentException e) {
                    Log.d("state", "onClick: " + e.getMessage());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.d("state", "onClick: " + e.getMessage());
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.d("state", "onClick: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    public void getSongList(){

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] proj1 = {"distinct " + MediaStore.Audio.Media.ARTIST};
        String[] proj2 = {MediaStore.Audio.Media.DISPLAY_NAME};

        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

//        Cursor musicCursor = musicResolver.query(musicUri, proj1, null, null, null);

        if(musicCursor == null){
            Toast.makeText(this, "No Songs", Toast.LENGTH_SHORT).show();
            Log.d("", "getSongList: No Songs");
        }

        if (musicCursor.moveToFirst()) {
            do {
                Log.d("Hello", "getSongList: " + musicCursor.getString(0));
            }while(musicCursor.moveToNext());
        }
        else {
            Log.d("Hello", "getSongList: No Artists");
        }
//        if (musicCursor!=null && musicCursor.moveToFirst()){
//            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
//            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
//            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//
//            do{
//                long thisId = musicCursor.getLong(idColumn);
//                String thisTitle = musicCursor.getString(titleColumn);
//                String thisArtist = musicCursor.getString(artistColumn);
//                Uri trackUri = ContentUris.withAppendedId(
//                        android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                        thisId);
//
//                Bitmap bitmap = null;
//                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//                mmr.setDataSource(getApplicationContext(),trackUri);
//                byte [] data = mmr.getEmbeddedPicture();
//                if (data != null){
//                    bitmap = BitmapFactory.decodeByteArray(data, 0 ,data.length);
//                }
//                songList.add(new Song(thisId, thisTitle, thisArtist, bitmap));
//            }while (musicCursor.moveToNext());
//        }
    }

    class FeedHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView imageSong;
        View fullView;

        public FeedHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.imageSong = (ImageView) itemView.findViewById(R.id.imageSong);
            this.fullView = itemView;
        }
    }

    class FeedAdapter extends RecyclerView.Adapter<FeedHolder>{


        @Override
        public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView = li.inflate(R.layout.smallview ,parent, false);

            return new FeedHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FeedHolder holder, final int position) {

            final Song feed = songList.get(position);

            long currSong = songList.get(position).getId();

            Uri trackUri = ContentUris.withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    currSong);

            Bitmap bitmap = songList.get(position).getBitmap();
//            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
//            mmr.setDataSource(getApplicationContext(),trackUri);
//            byte [] data = mmr.getEmbeddedPicture();
//            if (data != null){
//                bitmap = BitmapFactory.decodeByteArray(data, 0 ,data.length);
//            }
            holder.name.setText(feed.getTitle());
            if (bitmap == null){
                holder.imageSong.setImageResource(R.drawable.dddd);
            }
            else {
//                int nh = (int) (bitmap.getHeight() * (512.0 / bitmap.getWidth()));
//                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                holder.imageSong.setImageBitmap(bitmap);
            }
            holder.fullView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(PlaylistActivity.this, SongPlayActivity.class);
                    i.putExtra("id",feed.getId());
                    i.putExtra("name",feed.getTitle());
                    i.putExtra("artist",feed.getArtist());
                    i.putExtra("position",position);
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return songList.size();
        }
    }

    public static ArrayList<Song> getSongs(){
        return songList;
    }

    public static MediaPlayer getMPlayer(){
        return mp;
    }


    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==HOTSPOT_ACTIVITY){
            if(!isHotspotEnable()){
                Toast.makeText(this, "Please open your hotspot", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Hotspot Opened successfully", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
