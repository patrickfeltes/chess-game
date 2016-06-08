package chess.pieces;

import chess.images.Images;

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
        this.pieceSprite = (color == Piece.WHITE) ? Images.WHITE_KING : Images.BLACK_KING;
    }

    /**
     * Copy constructor for King
     * @param k King to be copied
     */
    public King(King k) {
        super(k);
    }

}
