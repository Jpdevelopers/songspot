package com.example.ishaandhamija.songplayer;

/**
 * Created by ishaandhamija on 08/03/17.
 */

public class Song {

    private Long id;
    private String title;
    private String artist;

    public Song(Long id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
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


}
