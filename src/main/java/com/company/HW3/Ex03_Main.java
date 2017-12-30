package com.company.HW3;
//	Student Name: XXXXXX YYYYYYYY,		Student ID: XXXXXXXXX
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ex03_Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		// main program variables
		// ----------------------

		AlbumSet albumSet = new AlbumSet("Shahar");
		// phase A
		//-----------
		// getting file name and open it

		File file = new File("C:\\Users\\SBK\\IdeaProjects\\school\\src\\main\\java\\com\\company\\HW3\\albumInputFile.txt");//JOptionPane.showInputDialog("FileName:"));
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()){
			final String[] split = scanner.nextLine().replaceAll("\\s+", " ").split(";");
			String songName = split[0].trim();
			String artistName = split[1].trim();
			String songTime = split[2].trim();
			int songMinutes = Integer.parseInt(songTime.split(":")[0].trim());
			int songSeconds = Integer.parseInt(songTime.split(":")[1].trim());
			String albumName = split[3];

			albumSet.addSongToAlbum(albumName, songName, artistName, songMinutes, songSeconds);
		}

		
		// reading input file and creating the albums

				// *** to be completed ***
	
				
		// phase B
		// -------
		// album and song ordering

		albumSet.sortByAlbumsName();
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			albumSet.getOneAlbumByIndex(i).sortByArtist();
		}
				// *** to be completed ***
	
		
		// phase C
		// -------
		// statistical information

		System.out.println(albumSet);
				// *** to be completed ***
	
		
		// phase D
		// -------
		// searching for a song

				// *** to be completed ***

		final String songNameToLookFoor = JOptionPane.showInputDialog("Song Name:");
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			if (albumSet.getOneAlbumByIndex(i).isSongExist(songNameToLookFoor) > -1){
				System.out.println(albumSet.getOneAlbumByIndex(i));
				System.out.println(albumSet.getOneAlbumByIndex(i).isSongExist(songNameToLookFoor));
			};
		}

		// phase E
		// ----------
		// getting output file name and write it...

				// *** to be completed ***
		PrintWriter printWriter = new PrintWriter("out.txt");
		printWriter.write("Total num of albums: " + albumSet.getNumAlbums() + "\n");
		for (int i = 0; i < albumSet.getNumAlbums(); i++) {
			printWriter.write(albumSet.getOneAlbumByIndex(i).toString() + "\n\n");
		}
		printWriter.flush();
	}

}
