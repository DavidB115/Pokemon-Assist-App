package com.techelevator;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class Lecture {

	public static void main(String[] args) throws IOException {
		/*
		 * The java.io.File class is a representation of file and directory path names.  It provides methods to inspect and
		 * modify file system objects.
		 *
		 * One benefit is that it compensates for differences in Windows and Unix use of '/' and '\' as directory delimiters.
		 *
		 * A new instance of File can be created from a String that contains a file system path
		 */
		String logMessage = "A4 | 1 | $1.25";
		try {
			createNewFile("log.txt");
		}
		catch (IOException e){
			System.out.println("Error creating file:" + e.getMessage());
		}
		catch (Exception e){
			System.out.println("An unknown error occured:" + e.getMessage());
		}


		try{
			writeToFile(logMessage, "log.txt");
		}catch (IOException e){
			System.out.println("Error appending file");
		}
		catch (Exception e){
			System.out.println("An unknown error occured:" + e.getMessage());
		}

	}

	public static void createNewFile(String newFileName) throws IOException {
		File newFile = new File("C:\\Users\\Student\\Workspace\\te-curriculum-sept-2023\\java\\module-1\\17_File_IO_Writing\\lecture", newFileName);

		if(!newFile.exists()){
			newFile.createNewFile();
		}
	}

	public static void writeToFile(String message, String newFileName) throws IOException {
		File newFile = new File("C:\\Users\\Student\\Workspace\\te-curriculum-sept-2023\\java\\module-1\\17_File_IO_Writing\\lecture", newFileName);

		try (FileWriter writer = new FileWriter(newFile, true);
			 PrintWriter pw = new PrintWriter(writer)) {
			pw.println(message);
			pw.flush();
		}
	}


}
