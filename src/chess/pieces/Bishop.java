package chess.pieces;

import chess.game.Board;
import chess.images.Images;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {

    /**
     * Constructor for a Bishop
     * @param x the x position of the Bishop
     * @param y the y position of the Bishop
     * @param color the color of the Bishop
     */
    public Bishop(int x, int y, int color) {
        super(x, y, color, Piece.BISHOP);
        this.pieceSprite = (color == Piece.WHITE) ? Images.WHITE_BISHOP : Images.BLACK_BISHOP;
    }

    /**
     * Copy constructor for Bishop
     * @param b Bishop to be copied
     */
    public Bishop(Bishop b) {
        super(b);
    }

    /**
     * A method to get the possible moves for the Bishop
     * @return an ArrayList of Points representing the Bishop's possible moves
     */
    public ArrayList<Point> possibleMoves(Board b) {
      return null;
    }
}
