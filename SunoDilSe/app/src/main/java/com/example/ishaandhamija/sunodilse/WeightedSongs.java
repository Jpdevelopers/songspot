package com.example.ishaandhamija.sunodilse;

/**
 * Created by ishaandhamija on 21/04/17.
 */

public class WeightedSongs {

    public long id;
    public String songName;
    public String artist;
    public String genre;
    public Integer weight;

    public WeightedSongs(long id,String songName, String artist, String genre) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.weight = 0;
    }

    public long getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = this.weight + weight;
    }
}
