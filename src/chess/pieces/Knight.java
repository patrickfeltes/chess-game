package chess.pieces;

import chess.images.Images;

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
        this.pieceSprite = (color == Piece.WHITE) ? Images.WHITE_KNIGHT : Images.BLACK_KNIGHT;
    }

    /**
     * Copy constructor for Knight
     * @param k Knight to be copied
     */
    public Knight(Knight k) {
        super(k);
    }
}
