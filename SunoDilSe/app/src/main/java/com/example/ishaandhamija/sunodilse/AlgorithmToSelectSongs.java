package com.example.ishaandhamija.sunodilse;

import java.util.ArrayList;

/**
 * Created by ishaandhamija on 21/04/17.
 */

public class AlgorithmToSelectSongs {

    public static ArrayList<String> genreList;
    public static ArrayList<String> artistList;

    public AlgorithmToSelectSongs(ArrayList<String> genreList, ArrayList<String> artistList) {
        this.genreList = genreList;
        this.artistList = artistList;
    }

    public static ArrayList<Song> songs = PlaylistActivity.getWeightedSongList();

    public static ArrayList<WeightedSongs> weightedSongs = new ArrayList<>();

    public static void weightSum(){
        for(int j=0;j<songs.size();j++){
            weightedSongs.add(new WeightedSongs(songs.get(j).getId(),songs.get(j).getTitle(),songs.get(j).getArtist(),songs.get(j).getGenre()));
        }
    }

    public static void genreTraversal(){
        weightSum();
        for (int i=0;i<genreList.size();i++){
            for (int j=0;j<weightedSongs.size();j++){
                if (genreList.get(i).toString() == weightedSongs.get(j).getGenre()){
                    weightedSongs.get(j).setWeight(1);
                }
            }
        }
    }

    public static void artistTraversal(){
        genreTraversal();
        for (int i=0;i<artistList.size();i++){
            for (int j=0;j<weightedSongs.size();j++){
                if (artistList.get(i).toString() == weightedSongs.get(j).getArtist()){
                    weightedSongs.get(j).setWeight(1);
                }
            }
        }
    }

    public static ArrayList<WeightedSongs> getWeightSongList(){
        artistTraversal();
        ArrayList<WeightedSongs> finalList = new ArrayList<>();
        for (int i=0;i<weightedSongs.size();i++){
            if (weightedSongs.get(i).getWeight() > 0){
                finalList.add(weightedSongs.get(i));
            }
        }
        return finalList;
    }

}