package chess.pieces;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {

    /**
     * Constructor for a Knight
     * @param x the x position of the Knight
     * @param y the y position of the Knight
     * @param color the color of the Knight
     */
    public Knight(int x, int y, int color) {
        super(x, y, color, Piece.KNIGHT);
    }

    /**
     * Copy constructor for Knight
     * @param k Knight to be copied
     */
    public Knight(Knight k) {
        super(k);
    }

    /**
     * Method to get the possible moves for a knight
     * @return an ArrayList of Points representing the knight's possible moves
     */
    public ArrayList<Point> possibleMoves() {
        return null;
    }
}
