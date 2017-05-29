/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epam.akademia;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author cubba
 */
public class Game {

    private Board board;
    private char actualMove;

    public Game(int size) {
        board = new Board(size);
    }

    public void startGame() {
        System.out.println("NEW GAME STARTED");
        setFirstMove();
        playGame();
    }

    public void setFirstMove() {
        System.out.println("WHO GOES FIRST O OR X?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String a = "";
        do {
            System.out.println("YOU MUST TYPE O OR X");
            try {
                a = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!a.equalsIgnoreCase("O") && !a.equalsIgnoreCase("X"));
        a = a.toUpperCase();
        actualMove = a.charAt(0);
    }

    //zamienic na enuma jak bedzie czas
    public int getColRow(String colRow) {
        int index = 0;
        int boardSize = board.getSize();
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("PLEASE ENTER A " + colRow + " TO PUT " + actualMove);
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!Utils.isInteger(input)) {
                System.out.println("YOU MUST ENTER VALID INTEGER");
                continue;
            } else {
                index = Integer.parseInt(input);
            }
            if (index > boardSize || index <= 0) {
                System.out.println("INCORRECT NUMBER");
                continue;
            }

            return index;
        }
    }

    public void playGame() {
        while (true) {
            board.printBoard();

            int row = getColRow("ROW");
            int col = getColRow("COL");

            while (!board.isCellEmpty(row, col)) {
                System.out.println("THIS CELL HAS BEEN ALREADY FILLED");
                row = getColRow("ROW");
                col = getColRow("COL");
            }

            board.setCell(row, col, actualMove);

            if (board.checkIfWon(row, col, actualMove, 3)) {
                board.printBoard();
                System.out.println("PLAYER WITH " + actualMove + " WINS !");
                break;
            }

            if (board.isBoardFull()) {
                System.out.println("BOARD IS FULL. TIE !");
                break;
            }

            if (actualMove == 'O') {
                actualMove = 'X';
            } else {
                actualMove = 'O';
            }
        }
    }

    public static void main(String[] args) {
        Game game;
        while (true) {
            System.out.println("DO YOU WANT TO PLAY A GAME?  Y/N");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String input = "";
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input.equalsIgnoreCase("y")) {
                game = new Game(3);
                game.startGame();
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("THANK YOU FOR PLAYING THE GAME. GOOD BYE!");
                break;
            }
        }
    }

}
