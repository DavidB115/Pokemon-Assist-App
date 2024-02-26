package com.techelevator;

import java.io.*;


public class Log {
    private String logLocation;
    private String logFileName;
    public Log (String logLocation, String logFileName){
        this.logLocation = logLocation;
        this.logFileName = logFileName;
    }

    public void writeToLog(String message) throws IOException {
        File newFile = new File(logLocation, logFileName);

        try (FileWriter writer = new FileWriter(newFile, true);
             BufferedWriter pw = new BufferedWriter(writer)) {
             pw.write(message);
        }
    }

}
