package chess.logic;

public class Move {

    private int sRow, sCol, eRow, eCol, pieceType;
    private boolean attacking;

    /**
     * Constructor for a move
     * @param sRow starting row
     * @param sCol starting col
     * @param eRow ending row
     * @param eCol ending col
     * @param pieceType the type of piece
     * @param attacking true if a piece is taken, false if not
     */
    public Move(int sRow, int sCol, int eRow, int eCol, int pieceType, boolean attacking) {
        this.sRow = sRow;
        this.sCol = sCol;
        this.eRow = eRow;
        this.eCol = eCol;
        this.pieceType = pieceType;
        this.attacking = attacking;
    }


}
