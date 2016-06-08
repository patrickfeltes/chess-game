package chess.logic;

public class Move {

    private int color, pieceType, sRow, sCol, eRow, eCol;
    private int rowTaken, colTaken, typeTaken, colorTaken;
    private boolean pieceTaken;

    /**
     * Constructor for a move
     * @param color the color of the piece being moved
     * @param pieceType the piece type of the move
     * @param sRow starting row
     * @param sCol starting col
     * @param eRow ending row
     * @param eCol ending col
     */
    public Move(int color, int pieceType, int sRow, int sCol, int eRow, int eCol) {
        this.color = color;
        this.pieceType = pieceType;
        this.sRow = sRow;
        this.sCol = sCol;
        this.eRow = eRow;
        this.eCol = eCol;
        this.rowTaken = -1;
        this.colTaken = -1;
        this.typeTaken = -1;
        this.colorTaken = 0;
        pieceTaken = false;
    }

    public void addPieceTaken(int row, int col, int type, int color) {
        this.rowTaken = row;
        this.colTaken = col;
        this.typeTaken = type;
        this.colorTaken = color;
        pieceTaken = true;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return pieceType;
    }

    public int getStartRow() {
        return sRow;
    }

    public int getStartCol() {
        return sCol;
    }

    public int getEndRow() {
        return eRow;
    }

    public int getEndCol() {
        return eCol;
    }

    public int getTypeTaken() {
        return typeTaken;
    }

    public int getColorTaken() {
        return colorTaken;
    }

    public int getRowTaken() {
        return rowTaken;
    }

    public int getColTaken() {
        return colTaken;
    }

    public boolean wasPieceTaken() {
        return pieceTaken;
    }





}
