package com.example.ishaandhamija.songplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ishaandhamija on 09/03/17.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private String songTitle="";
    private static final int NOTIFY_ID=1;

    private boolean shuffle=false;
    private Random rand;

    private final IBinder musicBind = new MusicBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent){
        mPlayer.stop();
        mPlayer.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mPlayer.getCurrentPosition() > 0){
            mp.reset();
            playNext();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();

        Intent notIntent = new Intent(this, MainActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendInt)
                .setSmallIcon(R.mipmap.ic_launcher_play)
                .setTicker(songTitle)
                .setOngoing(true)
                .setContentTitle("Playing")
  .setContentText(songTitle);
        Notification not = builder.build();

        startForeground(NOTIFY_ID, not);
    }

    public MediaPlayer mPlayer;
    private ArrayList<Song> songs;
    private int songPosition;

    @Override
    public void onCreate() {
        super.onCreate();
        songPosition = 0;
        mPlayer = new MediaPlayer();

        initMusicPlayer();

        rand = new Random();

    }

    public void initMusicPlayer(){
        mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);

    }

    public void setList(ArrayList<Song> theSongs){
        songs = theSongs;
    }

    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

//    private static Uri pichlaUri = null;

    @Override
    public void onDestroy() {
        stopForeground(true);
    }

    public void playSong(){
        mPlayer.reset();

        Song playSong = songs.get(songPosition);
        songTitle=playSong.getTitle();
        long currSong = playSong.getId();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);

//        if (pichlaUri == trackUri){
//            mPlayer.release();
//            Log.d("pichlawala", "playSong: ");
//        }
//        else {
//            Log.d("Naya", "playSong: ");
//            mPlayer.reset();
//        }
//
//        pichlaUri = trackUri;

        try{
            mPlayer.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }

        mPlayer.prepareAsync();
    }

    public void setSong(int songIndex){
        songPosition=songIndex;
    }

    public int getPosn(){
        return mPlayer.getCurrentPosition();
    }

    public int getDur(){
        return mPlayer.getDuration();
    }

    public boolean isPng(){
        return mPlayer.isPlaying();
    }

    public void pausePlayer(){
        mPlayer.pause();
    }

    public void seek(int posn){
        mPlayer.seekTo(posn);
    }

    public void go(){
        mPlayer.start();
    }

    public void playPrev(){
        songPosition--;
        if(songPosition == 0)
            songPosition=songs.size()-1;
        playSong();
    }

    public void playNext(){
        if(shuffle){
            int newSong = songPosition;
            while(newSong==songPosition){
                newSong=rand.nextInt(songs.size());
            }
            songPosition=newSong;
        }
        else{
            songPosition++;
            if (songPosition == songs.size())
                songPosition = 0;
        }
        playSong();
    }

    public void setShuffle(){
        if(shuffle) shuffle=false;
        else shuffle=true;
    }

}
