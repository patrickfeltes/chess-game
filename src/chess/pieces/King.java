package chess.pieces;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {

    /**
     * Constructor for a King
     * @param x the x position of the King
     * @param y the y position of the King
     * @param color the color of the King
     */
    public King(int x, int y, int color) {
        super(x, y, color, Piece.KING);
    }

    /**
     * Copy constructor for King
     * @param k King to be copied
     */
    public King(King k) {
        super(k);
    }

    /**
     * A method to get the possible moves for the King
     * @return an ArrayList of Points representing the King's possible moves
     */
    public ArrayList<Point> possibleMoves() {
        return null;
    }

}
