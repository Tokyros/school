package com.company.HW3;

/**
 * Created by SBK on 12/4/2017.
 */
public class HW3_ShaharRosen {
    public static void main(String[] args) {
        "".substring(3,4);
        Album album = new Album("TestAlbum");
        Song song1 = new Song("Abc", "aa", 50);
        Song song3 = new Song("Ani Mevulgan", "aa", 40);
        Song song4 = new Song("Ani Mesudar", "aa", 5000);
        Song song5 = new Song("Abc", "aa", 500);
        album.addSong(song3);
        album.addSong(song4);
        album.addSong(song1);
        album.addSong(song5);
        album.sortByArtist();
        System.out.println(album);
    }
}
