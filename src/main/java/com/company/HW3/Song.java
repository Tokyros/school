package com.company.HW3;

public class Song {
    // class behaviours
    private String songName = "";
    private String artistName = "";
    private int songLength = 0;

    //constructors
    public Song(String songName, String artistName, int minutes, int seconds) {
        setSongName(songName);
        setAtristName(artistName);
        setSongLength(minutes, seconds);
    }

    public Song(String songName, String artistName, int seconds) {
        setSongName(songName);
        setAtristName(artistName);
        setSongLength(seconds);
    }

    // getters
    public String getSongName() {
        return this.songName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public int getSongLength() {
        return this.songLength;
    }

    // setters
    public void setSongName(String songName) {
        this.songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();
    }

    public void setAtristName(String artistName) {
        this.artistName = artistName.substring(0, 1).toUpperCase() + artistName.substring(1).toLowerCase();
    }

    public void setSongLength(int minutes, int seconds) {
        this.songLength = 60 * minutes + seconds;
    }

    public void setSongLength(int seconds) {
        this.songLength = seconds;
    }

    // special Methods
    public boolean isSongEqaul(String songName) {
        return songName.equalsIgnoreCase(getSongName());
    }

    public boolean isAtristEqaul(String artistName) {
        return artistName.equalsIgnoreCase(getArtistName());
    }

    // to String
    public String toString() {
        return "Song Name: " + this.songName + ", Artist Name: " + this.artistName
                + ", Song Length: " + this.songLength + " seconds";
    }
}