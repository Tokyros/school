package com.company.HW3;

public class AlbumSet {

    private String owner;
    private Album[] albums;
    int numAlbums;
    // Behaviours

    // *** to be completed ***


    // Constructor
    public AlbumSet(String owner) {
        setOwner(owner);
        this.albums = new Album[20];
        this.numAlbums = 0;
    }

    // getters
    public String getOwner() {
        return this.owner;
    }

    public int getNumAlbums() {
        return this.numAlbums;
    }

    public Album getOneAlbumByIndex(int index) {
        return this.albums[index];
    }

    public void setOwner(String owner) {
        this.owner = owner.substring(0, 1).toUpperCase() + owner.substring(1).toLowerCase();
    }

    // special setter: adding song to album
    public void addSongToAlbum(String albumName, String songName, String artistName, int minutes, int seconds) {
        if (isAlbumExists(albumName)) {
            getOneAlbumByIndex(getAlbumIndex(albumName)).addSong(songName, artistName, minutes, seconds);
        } else {
            //TODO: What if not?
            if (this.numAlbums < this.albums.length){
                Album albumToAddTo = new Album(albumName);
                albumToAddTo.addSong(songName, artistName, minutes, seconds);
                this.albums[this.numAlbums++] = albumToAddTo;
            }
        }
    }

    // special Methods
    public boolean isAlbumExists(String albumName) {
        return getAlbumIndex(albumName) >= 0;
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
                    Album temp = getOneAlbumByIndex(j);
                    albums[j] = albums[i];
                    albums[i] = temp;
                }
            }
        }

    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Number of albums: ");
        sb.append(numAlbums);
        sb.append("\n");
        for (int i = 0; i < numAlbums; i++) {
            Album album = albums[i];
            sb.append(album);
            sb.append("\n");
        }
        return sb.toString();
    }
}