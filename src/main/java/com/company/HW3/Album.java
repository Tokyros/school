package com.company.HW3;

public class Album {
	private final int MAX_SONGS = 20;
	private String albumName;
	private Song[] songs;
	private int totalAlbumLength;
	private int numSongs;
	
	public Album(String albumName){
        setAlbumName(albumName);
        this.songs = new Song[MAX_SONGS];
	}
	
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
	
	public void setAlbumName(String albumName){
		this.albumName = albumName.substring(0,1).toUpperCase()+albumName.substring(1).toLowerCase();
	}
	
	public void addSong(Song newSong){
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

    //Bubble sort
	public void sortByArtist(){
        for (int i = 0; i < numSongs; i++) {
			for (int j = 1; j < numSongs-i; j++) {
				Song song1 = this.songs[j-1];
				Song song2 = this.songs[j];
				boolean shouldSwap;

				// If songs have the same artist we sort by song length ascending.
				if (song1.isAtristEqaul(song2.getArtistName())){
					shouldSwap = song1.getSongLength() > song2.getSongLength();
				} else {
					// Otherwise we sort by the artist name ascending
					shouldSwap = song1.getArtistName().compareToIgnoreCase(song2.getArtistName()) > 0;
				}

				if (shouldSwap) swapSongs(j, j-1);
            }
        }
    }

    private void swapSongs(int i, int j){
		Song temp = this.songs[j];
		this.songs[j] = this.songs[i];
		this.songs[i] = temp;
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