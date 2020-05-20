package Models;

import Exceptions.InvalidStateException;
import Exceptions.TileTakenException;

public class Tile {
    private int state;

    // Requires: Input i is an integer
    // Modifies: state
    // Effects: sets the state to 0 / 1 / 2
    // 0 indicates the tile is empty
    // 1 indicates the player has taken this space
    // 2 indicates the AI has taken this space
    // An invalid state exception is thrown otherwise
    public void setState(int i) throws InvalidStateException, TileTakenException {
        if ((0 <= i) && (i <= 2)) {
            if (state == 0) {
                this.state = i;
            } else {
                throw new TileTakenException();
            }
        } else {
            throw new InvalidStateException();
        }
    }

    public void testState(int i) {
        if ((0 <= i) && (i <= 2)) {
            this.state = i;
        }
    }
    // Getter for current state
    public int getState() {
        return state;
    }

    // Returns true if tile is taken by player or AI
    public boolean isTaken() {
        return (state == 1 || state == 2);
    }

}
