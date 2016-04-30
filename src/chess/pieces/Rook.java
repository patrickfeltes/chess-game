package chess.pieces;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece{

    /**
     * Constructor for a Rook
     * @param x the x position of the Rook
     * @param y the y position of the Rook
     * @param color the color of the Rook
     */
    public Rook(int x, int y, int color) {
        super(x, y, color, Piece.ROOK);
    }

    /**
     * Copy constructor for Rook
     * @param r Rook to be copied
     */
    public Rook(Rook r) {
        super(r);
    }

    /**
     * A method to get the possible moves for the Rook
     * @return an ArrayList of Points representing the Rook's possible moves
     */
    public ArrayList<Point> possibleMoves() {
        return null;
    }

}
