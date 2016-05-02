package chess.pieces;

import chess.game.Board;
import chess.images.Sprite;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {

    // piece types
    public static final int PAWN = 0;
    public static final int KNIGHT = 1;
    public static final int BISHOP = 2;
    public static final int ROOK = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;

    // sides
    public static final int BLACK = -1;
    public static final int WHITE = 1;

    // piece information
    protected int pieceX, pieceY;
    protected int pieceColor;
    protected int pieceType;
    protected Sprite pieceSprite;

    /**
     * Constructor for a Piece.
     * @param x     the x location of the Piece
     * @param y     the y location of the Piece
     * @param color the color of the Piece
     * @param type  the type of the Piece
     */
    public Piece(int x, int y, int color, int type) {
        pieceX = x;
        pieceY = y;
        pieceType = type;
        pieceColor = color;
    }

    public void draw(Graphics2D g) {
        pieceSprite.draw(g, pieceX, pieceY);
    }

    /**
     * A copy constructor for Piece
     * @param p Piece to be copied
     */
    public Piece(Piece p) {
        pieceX = p.pieceX;
        pieceY = p.pieceY;
        pieceColor = p.pieceColor;
        pieceType = p.pieceType;
    }

    /**
     * Method to return the possible moves for the Piece, will be defined by sub-classes
     * @return an ArrayList of Points representing possible moves
     */
    public abstract ArrayList<Point> possibleMoves(Board b);

    /**
     * Method to get the type of a Piece
     * @return an int representing the type of a piece
     */
    public int getType() {
        return pieceType;
    }

    /**
     * Method to set the type of a piece
     * @param type int representing the new type to set5
     */
    public void setType(int type) {
        pieceType = type;
    }

}
