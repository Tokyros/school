package com.company.HW3;

public class AlbumSet {
    //Properties
    private final int MAX_ALBUMS = 20;
    private String owner;
    private Album[] albums;
    private int numAlbums;

    // Constructor
    public AlbumSet(String owner) {
        setOwner(owner);
        this.albums = new Album[MAX_ALBUMS];
    }

    // getters
    public String getOwner() {
        return this.owner;
    }

    public int getNumAlbums() {
        return this.numAlbums;
    }

    public Album getOneAlbumByIndex(int index) {
        if (index > MAX_ALBUMS - 1 || index < 0) return null;
        return this.albums[index];
    }

    public void setOwner(String owner) {
        this.owner = owner.substring(0, 1).toUpperCase() + owner.substring(1).toLowerCase();
    }

    // special setter: adding song to album
    public void addSongToAlbum(String albumName, String songName, String artistName, int minutes, int seconds) {
        if (isAlbumExists(albumName)) {
            getOneAlbumByIndex(getAlbumIndex(albumName)).addSong(songName, artistName, minutes, seconds);
        } else if (this.numAlbums < this.albums.length){
            Album newAlbum = new Album(albumName);
            newAlbum.addSong(songName, artistName, minutes, seconds);
            this.albums[this.numAlbums++] = newAlbum;
        }
    }

    // special Methods
    public boolean isAlbumExists(String albumName) {
        return getAlbumIndex(albumName) > -1;
    }

    public int getAlbumIndex(String albumName) {
        for (int i = 0; i < this.numAlbums; i++) {
            if (this.albums[i].getAlbumName().equalsIgnoreCase(albumName)) return i;
        }
        return -1;
    }

    public void sortByAlbumsName() {
        for (int i = 0; i < numAlbums; i++) {
            for (int j = i + 1; j < numAlbums; j++) {
                if (getOneAlbumByIndex(i).getAlbumName().compareTo(getOneAlbumByIndex(j).getAlbumName()) > 0){
                    swapAlbums(i, j);
                }
            }
        }
    }

    private void swapAlbums(int i, int j){
        Album temp = getOneAlbumByIndex(j);
        albums[j] = getOneAlbumByIndex(i);
        albums[i] = temp;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Number of albums: ").append(numAlbums).append("\n");
        for (int i = 0; i < numAlbums; i++) {
            Album album = albums[i];
            sb.append(album);
            sb.append("\n");
        }
        return sb.toString();
    }
}