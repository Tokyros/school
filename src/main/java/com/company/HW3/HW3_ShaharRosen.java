package com.company.HW3;
//	Student Name: Shahar Rosen,	Student ID: 204541791
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class HW3_ShaharRosen {

	private static final String OWNER = "Shahar";
	private static final int SONG_NAME_INDEX = 0;
	private static final int SECONDS_INDEX = 1;
	private static final int ARTIST_NAME_INDEX = 1;
	private static final int SONG_TIME_INDEX = 2;
	private static final int MINUTES_INDEX = 0;
	private static final int ALBUM_NAME_INDEX = 3;


	public static void main(String[] args) throws FileNotFoundException {
		AlbumSet albumSet = new AlbumSet(OWNER);
		// phase A
		//-----------
		// getting file name and open it
		populateAlbumSetFromFile(albumSet);

		// phase B
		// -------
		// album and song ordering
		sortAlbumSet(albumSet);

		// phase C
		// -------
		// statistical information
		printStatisticalInfo(albumSet);

		// phase D
		// -------
		// searching for a song
		int shouldSearchSong = JOptionPane.showConfirmDialog(null, "Would you like to search for a specific song?");
		if (shouldSearchSong == JOptionPane.YES_OPTION){
			final String songNameForSearch = JOptionPane.showInputDialog("Song Name:");
			if (songNameForSearch != null) searchSongAndPrintResult(albumSet, songNameForSearch);
		}

		// phase E
		// ----------
		// getting output file name and write it...
		saveAlbumSet(albumSet);
	}

	private static void saveAlbumSet(AlbumSet albumSet) throws FileNotFoundException {
		final String outputPath = JOptionPane.showInputDialog("Please enter output file name");
		// The user clicked cancel
		if (outputPath == null) return;
		saveAlbumSetToFile(albumSet, outputPath);
	}

	private static void saveAlbumSetToFile(AlbumSet albumSet, String outputPath) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(outputPath);
		printWriter.write("Total num of albums: " + albumSet.getNumAlbums() + "\n");
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			printWriter.write("Album no' " + (i + 1) + "\n");
			printWriter.write("==============\n");
			printWriter.write(albumSet.getOneAlbumByIndex(i) + "\n");
		}
		printWriter.close();
	}

	private static void searchSongAndPrintResult(AlbumSet albumSet, String songNameForSearch) {
		boolean songFound = false;
		StringBuffer searchResults = new StringBuffer(String.format("Found the song \"%s\" in the following albums:\n", songNameForSearch));
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			Album oneAlbum = albumSet.getOneAlbumByIndex(i);
			int songIndex = oneAlbum.isSongExist(songNameForSearch);
			if (songIndex > -1){
				searchResults
						.append("Song: ")
						.append(songNameForSearch)
						.append(" exists at Album: ")
						.append(oneAlbum.getAlbumName())
						.append(" as song number: ")
						.append(songIndex + 1)
						.append("\n");
				songFound = true;
			}
		}

		if (!songFound){
			System.out.println(String.format("Could not find the song \"%s\"", songNameForSearch));
		} else {
			System.out.println(searchResults);
		}
	}

	private static Scanner readInputFile() throws FileNotFoundException {
		String inputFileName = JOptionPane.showInputDialog("Please enter input file name");
		// Return null if the user canceled
		if (inputFileName == null) return null;
		File file = new File(inputFileName);
		return new Scanner(file);
	}

	private static void populateAlbumSetFromFile(AlbumSet albumSet) throws FileNotFoundException {
		Scanner inputFile = readInputFile();
		if (inputFile == null) return;
		while (inputFile.hasNext()){
			String oneLine = inputFile.nextLine();
			addSongFromLine(albumSet, oneLine);
		}
	}


	private static void addSongFromLine(AlbumSet albumSet, String nextLine) {
		final String stringToReplace = "\\s+";
		final String replacement = " ";
		final String separator = ";";
		final String timeSeparator = ":";
		// remove double spaces from string and split values on ";"
		final String[] lineValues = nextLine.replaceAll(stringToReplace, replacement).trim().split(separator);

		// Extract the values from the relevant indexes into variables
		String songName = lineValues[SONG_NAME_INDEX].trim();
		String artistName = lineValues[ARTIST_NAME_INDEX].trim();
		String songTime = lineValues[SONG_TIME_INDEX].trim();
		int songMinutes = Integer.parseInt(songTime.split(timeSeparator)[MINUTES_INDEX].trim());
		int songSeconds = Integer.parseInt(songTime.split(timeSeparator)[SECONDS_INDEX].trim());
		String albumName = lineValues[ALBUM_NAME_INDEX].trim();

		albumSet.addSongToAlbum(albumName, songName, artistName, songMinutes, songSeconds);
	}

	private static void sortAlbumSet(AlbumSet albumSet) {
		// in-place sorting of album set and all the albums inside it
		albumSet.sortByAlbumsName();
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			albumSet.getOneAlbumByIndex(i).sortByArtist();
		}
	}

	private static void printStatisticalInfo(AlbumSet albumSet) {
		StringBuffer statisticalInfo = new StringBuffer();
		statisticalInfo
				.append("Number of albums: ")
				.append(albumSet.getNumAlbums())
				.append("\n");

		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			Album oneAlbumByIndex = albumSet.getOneAlbumByIndex(i);
			statisticalInfo
					.append("Album name:")
					.append(oneAlbumByIndex.getAlbumName())
					.append(", Number of songs: ")
					.append(oneAlbumByIndex.getNumSongs())
					.append(", Total songs lengths: ")
					.append(oneAlbumByIndex.getAlbumLength())
					.append("(seconds).\n");
		}
		System.out.println(statisticalInfo);
	}

}
