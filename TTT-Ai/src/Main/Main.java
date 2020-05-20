package Main;

import Exceptions.InvalidStateException;
import Exceptions.TileTakenException;
import Models.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidStateException, TileTakenException {
        TTTGame game = new TTTGame();
        boolean active = true;
        System.out.println("Welcome to an unwinnable game!");
        System.out.println("Here is a map of the game");
        System.out.println("1 | 2 | 3");
        System.out.println("4 | 5 | 6");
        System.out.println("7 | 8 | 9");
        System.out.println("_________");
        Scanner sc = new Scanner(System.in);
        printGame(game);
        while (active) {
            System.out.println("Where would you like to go next?");
            System.out.println("Indicate your choice using the above map and enter an integer 1-9");
            boolean isSucessful = false;
            while(!isSucessful) {
                try {
                    int i = sc.nextInt() - 1;
                    game.getGameboard().get(i).setState(1);
                    isSucessful = true;
                } catch (TileTakenException tte) {
                    System.out.println("Tile already taken, please try again");
                } catch (IndexOutOfBoundsException ooe) {
                    System.out.println("Tile doesn't exist, please try again");
                }
            }
            aiTurn(game);
            printGame(game);
            if (game.haswinner(2)) {
                System.out.println("The AI has won");
                active = false;
            }
            if (game.haswinner(1)) {
                System.out.println("There must be a bug in the code...");
                active = false;
            }
            if (game.ended()) {
                System.out.println("It is a tie! (but you didn't win)");
                active = false;
            }
        }
    }

    // Prints the game tiles for the player to see
    private static void printGame(TTTGame game) {
        System.out.println(getTile(0, game) + " | " + getTile(1, game) + " | " + getTile(2, game));
        System.out.println(getTile(3, game) + " | " + getTile(4, game) + " | " + getTile(5, game));
        System.out.println(getTile(6, game) + " | " + getTile(7, game) + " | " + getTile(8, game));
    }

    // gets the data to print
    private static String getTile(int i, TTTGame g) {
        if (g.getGameboard().get(i).getState() == 1)
            return "X";
        if (g.getGameboard().get(i).getState() == 2)
            return "O";
        return "_";
    }

    private static void aiTurn(TTTGame g) throws InvalidStateException, TileTakenException {

        // Rule 1: If I have a winning move, take it.
        if (g.findWin() != 10) {
            g.getGameboard().get(g.findWin()).setState(2);
            return;
        }

        // Rule 2: If the opponent has a winning move, block it.
        if (g.preventLoss() != 10) {
            g.getGameboard().get(g.preventLoss()).setState(2);
            return;
        }

        // Rule 3: Place randomly, centre, corner, side
        if (g.getGameboard().get(4).getState() == 0) { // centre case
            g.getGameboard().get(4).setState(2);
            return;
        }
        System.out.println("print");
        int[] arrx = new int[] {0,2,6,8};
        for (int i : arrx) {
            if (g.getGameboard().get(i).getState() == 0) {
                g.getGameboard().get(i).setState(2);
                return;
            }
        }
        System.out.println("print2");
        int[] arrs = new int[] {1,3,5,7};
        for (int i : arrs) {
            if (g.getGameboard().get(i).getState() == 0) {
                g.getGameboard().get(i).setState(2);
                return;
            }
        }
    }

}
