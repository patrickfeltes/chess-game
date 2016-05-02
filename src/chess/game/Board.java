package chess.game;

import chess.pieces.Pawn;
import chess.pieces.Piece;

import java.awt.*;

public class Board {

    private int x, y;
    private int size;

    private int rows = 8;
    private int cols = 8;
    private int tileSize;

    private Piece pawn = new Pawn(10, 10, Piece.BLACK);

    private int[][] board;

    public static final int OCCUPIED = 1;
    public static final int EMPTY = 0;

    public Board(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        tileSize = size / rows;
        board = new int[rows][cols];
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                if((r % 2 == 0 && c % 2 == 0) || (r % 2 == 1 && c % 2 == 1)) g.setColor(Color.WHITE);
                else g.setColor(Color.BLACK);
                g.fillRect(x + c * tileSize, y + r * tileSize, tileSize, tileSize);
            }
        }

        pawn.draw(g);
    }

    public boolean isSpaceOccupied(int x, int y) {
        return (board[y][x] == OCCUPIED);
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

}
