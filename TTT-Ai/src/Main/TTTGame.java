package Main;

import Exceptions.InvalidStateException;
import Exceptions.TileTakenException;
import Models.Tile;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

public class TTTGame implements Cloneable {
    private ArrayList<Tile> gameboard;

    public TTTGame() throws InvalidStateException, TileTakenException {
        this.gameboard = new ArrayList<Tile>();
        for (int i = 0; i < 9; i++) {
            Tile t = new Tile();
            t.setState(0);
            gameboard.add(t);
            gameboard.get(i).setState(0);
        }
    }

    boolean haswinner(int key) {
        if (    (gameboard.get(0).getState() == key && gameboard.get(1).getState() == key && gameboard.get(2).getState() == key) ||
                (gameboard.get(3).getState() == key && gameboard.get(4).getState() == key && gameboard.get(5).getState() == key) ||
                (gameboard.get(6).getState() == key && gameboard.get(7).getState() == key && gameboard.get(8).getState() == key) ||
                (gameboard.get(0).getState() == key && gameboard.get(3).getState() == key && gameboard.get(6).getState() == key) ||
                (gameboard.get(1).getState() == key && gameboard.get(4).getState() == key && gameboard.get(7).getState() == key) ||
                (gameboard.get(2).getState() == key && gameboard.get(5).getState() == key && gameboard.get(8).getState() == key) ||
                (gameboard.get(0).getState() == key && gameboard.get(4).getState() == key && gameboard.get(8).getState() == key) ||
                (gameboard.get(2).getState() == key && gameboard.get(4).getState() == key && gameboard.get(6).getState() == key)
        ) { return true;}
        return false;
    }

    boolean ended() {
        for (int i = 0; i < 9; i++) {
            if (gameboard.get(i).getState() == 0) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Tile> getGameboard() {
        return gameboard;
    }

    // Finds and returns the game winning position given a game board
    // return 10 if cannot win
    int findWin() {
        for (int i = 0; i < 9; i++) {
            if (gameboard.get(i).getState() == 0) {
                gameboard.get(i).testState(2);
                if (haswinner(2)) {
                    gameboard.get(i).testState(0);
                    return i;
                } else {
                    gameboard.get(i).testState(0);
                }
            }
        }
        return 10;
    }

    // Finds and returns the game continuing position given a game board
    // return 10 if cannot win
    int preventLoss() {
        for (int i = 0; i < 9; i++) {
            if (gameboard.get(i).getState() == 0) {
                gameboard.get(i).testState(1);
                if (haswinner(1)) {
                    gameboard.get(i).testState(0);
                    return i;
                } else {
                    gameboard.get(i).testState(0);
                }
            }
        }
        return 10;
    }


}
