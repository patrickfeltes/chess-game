package chess.pieces;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{

    /**
     * Constructor for a Queen
     * @param x the x position of the Queen
     * @param y the y position of the Queen
     * @param color the color of the Queen
     */
    public Queen(int x, int y, int color) {
        super(x, y, color, Piece.QUEEN);
    }

    /**
     * Copy constructor for Queen
     * @param q Queen to be copied
     */
    public Queen(Queen q) {
        super(q);
    }

    /**
     * A method to get the possible moves for the Queen
     * @return an ArrayList of Points representing the Queen's possible moves
     */
    public ArrayList<Point> possibleMoves() {
        return null;
    }

}
