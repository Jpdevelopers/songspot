package com.example.ishaandhamija.sunodilse;

import android.graphics.Bitmap;

/**
 * Created by ishaandhamija on 13/04/17.
 */

class Song {

    private Long id;
    private String title;
    private String artist;
    private Bitmap bitmap;
    private String genre;

    public Song(Long id, String title, String artist, Bitmap bitmap, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.bitmap = bitmap;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getGenre() {
        return genre;
    }
}
