package com.company.HW3;

public class Album {
	// class behaviours
	private String albumName;
	private Song[] songs;
	private int totalAlbumLength;
	private int numSongs;
	
	// constructor
	public Album(String albumName){
        setAlbumName(albumName);
        this.songs = new Song[20];
	}
	
	// getters
	public String getAlbumName(){
		return this.albumName;
	}
	public Song[] getAlbumSongs(){
		return this.songs;
	}
	public int getAlbumLength(){
		return this.totalAlbumLength;
	}
	public int getNumSongs(){
		return this.numSongs;
	}
	
	// setters
	public void setAlbumName(String albumName){
		this.albumName = albumName.substring(0,1).toUpperCase()+albumName.substring(1).toLowerCase();
	}
	
	// special setters: adding new song
	public void addSong(Song newSong){
		//TODO: What if not?
		if (this.numSongs < this.songs.length){
	    	totalAlbumLength += newSong.getSongLength();
        	this.songs[this.numSongs++] = newSong;
		}
	}

	public void addSong(String songName, String artistName, int minutes, int seconds){
        Song newSong = new Song(songName, artistName, minutes, seconds);
        this.addSong(newSong);
	}
	
	// special Methods	
	public int isSongExist(String songName){
        for (int i = 0; i < this.numSongs; i++) {
            if (this.songs[i].isSongEqaul(songName)) return i;
        }
        return -1;
    }
		
	public void sortByArtist(){
        for (int i = 0; i < numSongs; i++) {
            for (int j = i+1; j < numSongs; j++) {
                if (this.songs[i].getArtistName().compareTo(this.songs[j].getArtistName()) > 0){
                    Song temp = this.songs[j];
                    this.songs[j] = this.songs[i];
                    this.songs[i] = temp;
                } else if (this.songs[i].isAtristEqaul(this.songs[j].getArtistName()) && this.songs[i].getSongName().compareTo(this.songs[j].getSongName()) > 0){
                    Song temp = this.songs[j];
                    this.songs[j] = this.songs[i];
                    this.songs[i] = temp;
                }
            }
        }
    }
	
	
	// toString	
	public String toString(){
		String str = "Album Name: " + this.albumName 
						+ ", number of Songs: " + this.numSongs
							+ ", Album Length (in seconds): " + this.totalAlbumLength;
		for (int i = 0; i < this.numSongs; i++)
			str += "\n" + this.songs[i];
		return str;
	}
}