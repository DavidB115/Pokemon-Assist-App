package com.techelevator;


import java.io.*;
import java.time.LocalDate;


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
		Log errorLog = new Log("C:\\Users\\Student\\Workspace\\te-curriculum-sept-2023\\java\\module-1\\17_File_IO_Writing\\lecture", "errorLog.txt");
		Log transactionLog = new Log("C:\\Users\\Student\\Workspace\\te-curriculum-sept-2023\\java\\module-1\\17_File_IO_Writing\\lecture", "transactionLog.txt");
		int x = 5;
		int y = 0;


		try {
			System.out.println(x/y);
		}
		catch (Exception e){
			System.out.println("An unknown error occured:" + e.getMessage());
			errorLog.writeToLog(LocalDate.now().toString() + e.getMessage());
		}


		try{
			transactionLog.writeToLog(logMessage);
		}catch (IOException e){
			System.out.println("Error appending file");
		}
		catch (Exception e){
			System.out.println("An unknown error occured:" + e.getMessage());
		}

	}






}
