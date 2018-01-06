package com.company.HW3;
//	Student Name: XXXXXX YYYYYYYY,		Student ID: XXXXXXXXX
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ex03_Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		AlbumSet albumSet = new AlbumSet("Shahar");
		// phase A
		//-----------
		// getting file name and open it
		Scanner scanner = readInputFile();
		populateAlbumSetFromFile(albumSet, scanner);

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
		final String songNameForSearch = JOptionPane.showInputDialog("Song Name:");
		searchSongAndPrintResult(albumSet, songNameForSearch);

		// phase E
		// ----------
		// getting output file name and write it...
		String outputPath = JOptionPane.showInputDialog("Please enter output file name");
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
		printWriter.flush();
	}

	private static void searchSongAndPrintResult(AlbumSet albumSet, String songNameForSearch) {
		if (songNameForSearch != null){
			boolean songFound = false;
			StringBuffer searchResults = new StringBuffer(String.format("Found the song \"%s\" in the following albums:\n", songNameForSearch));
			for (int i = 0; i < albumSet.getNumAlbums(); i++) {
				Album oneAlbum = albumSet.getOneAlbumByIndex(i);
				int songIndex = oneAlbum.isSongExist(songNameForSearch);
				if (songIndex > -1){
					searchResults
							.append("Song: ")
							.append(songNameForSearch)
							.append("exists at Album: ")
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
	}

	private static void populateAlbumSetFromFile(AlbumSet albumSet, Scanner scanner) {
		while (scanner.hasNext()){
			String nextLine = scanner.nextLine();
			addSongFromLine(albumSet, nextLine);
		}
	}

	private static Scanner readInputFile() throws FileNotFoundException {
		String inputFileName = JOptionPane.showInputDialog("Please enter input file name");
		String constPathName = "C:\\Users\\SBK\\IdeaProjects\\school\\src\\main\\java\\com\\company\\HW3\\albumInputFile.txt";
		File file = new File(inputFileName.isEmpty() ? inputFileName : constPathName);
		return new Scanner(file);
	}

	private static void addSongFromLine(AlbumSet albumSet, String nextLine) {
		final String[] split = nextLine.replaceAll("\\s+", " ").trim().split(";");
		String songName = split[0].trim();
		String artistName = split[1].trim();
		String songTime = split[2].trim();
		int songMinutes = Integer.parseInt(songTime.split(":")[0].trim());
		int songSeconds = Integer.parseInt(songTime.split(":")[1].trim());
		String albumName = split[3].trim();

		albumSet.addSongToAlbum(albumName, songName, artistName, songMinutes, songSeconds);
	}

	private static void sortAlbumSet(AlbumSet albumSet) {
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
