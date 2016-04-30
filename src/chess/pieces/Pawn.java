package chess.pieces;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    /**
     * Constructor for a Pawn
     * @param x the x position of the Pawn
     * @param y the y location of the Pawn
     * @param color the color of the Pawn
     */
    public Pawn(int x, int y, int color) {
        super(x, y, color, Piece.PAWN);
    }

    /**
     * Copy constructor for Pawn
     * @param p Pawn to be copied
     */
    public Pawn(Pawn p) {
        super(p);
    }

    /**
     * A method to get the possible moves for a Pawn
     * @return an ArrayList of Points representing the pawns possible moves
     */
    public ArrayList<Point> possibleMoves() {
        return null;
    }
}
