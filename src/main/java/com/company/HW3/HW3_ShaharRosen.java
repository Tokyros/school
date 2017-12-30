package com.company.HW3;

/**
 * Created by SBK on 12/4/2017.
 */
public class HW3_ShaharRosen {
    public static void main(String[] args) {
        Album album = new Album("TestAlbum");
        Song song1 = new Song("Abc", "Shahar", 50);
        Song song3 = new Song("Ani Mevulgan", "Shahar", 50);
        Song song4 = new Song("Ani Mesudar", "Naor", 50);
        Song song5 = new Song("Abc", "Naor", 50);
        album.addSong(song3);
        album.addSong(song4);
        album.addSong(song1);
        album.addSong(song5);
        album.sortByArtist();
        System.out.println(album.isSongExist(song5.getSongName()));
    }
}
