package com.techelevator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class StackGame {
    public static void main(String[] args) {
        boolean gameOver = false;
        Stack<String> moves = new Stack<>();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the useless game!");



        while (!gameOver) {
            System.out.println("Enter the direction to walk: (L = Left, R = Right, U = up, D = Down, E = Escape ");

            String direction = userInput.nextLine();
            if (direction.equals("L")){
                System.out.println("You went left!");
                moves.add(direction);
            } else if (direction.equals("R")) {
                System.out.println("You went right!");
                moves.add(direction);
            } else if (direction.equals("U")) {
                System.out.println("You went up!");
                moves.add(direction);
            }else if (direction.equals("D")) {
                System.out.println("You went down!");
                moves.add(direction);
            }else if (direction.equals("E")) {
                System.out.println("You escaped !");
                moves.add(direction);
                gameOver = true;
            }else {
                System.out.println("Whaaaaat?   ");
            }
        }
        System.out.println(moves);
    }
}
