package com.techelevator;

public class Airplane {
    /*
        planeNumber	String	X		The six-character plane number.
        totalFirstClassSeats	int	X		The total number of first class seats.
        bookedFirstClassSeats	int	X		The number of already booked first class seats.
        availableFirstClassSeats (derived)	int	X		The number of available first class seats.
        totalCoachSeats	int	X		The total number of coach seats.
        bookedCoachSeats	int	X		The number of already booked coach seats.
        availableCoachSeats (derived)	int	X		The number of available coach seats.

     */

    private String planeNumber;
    private  int totalFirstClassSeats;
    private int bookedFirstClassSeats;
    private int totalCoachSeats;
    private int bookedCoachSeats;

    public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFirstClassSeats;
        this.totalCoachSeats = totalCoachSeats;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }

    public int getAvailableFirstClassSeats(){
        return totalFirstClassSeats - bookedFirstClassSeats;
    }

    public int getAvailableCoachSeats(){
        return totalCoachSeats - bookedCoachSeats;
    }

    public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats){
        if(forFirstClass){
           if(totalNumberOfSeats > getAvailableFirstClassSeats()) {
               return false;
           } else {
               bookedFirstClassSeats+=totalNumberOfSeats;
               return true;
           }
        }else {
            if (totalNumberOfSeats > getAvailableCoachSeats()) {
                return false;
            } else {
                bookedCoachSeats += totalNumberOfSeats;
                return true;
            }
        }
    }
}
