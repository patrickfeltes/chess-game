package chess.logic;

/**
 * Static class that takes generates moves for all pieces of all colors
 */
public class MoveGenerator {

    // rook generation
    private static long[] right = new long[64];
    private static long[] left = new long[64];
    private static long[] up = new long[64];
    private static long[] down = new long[64];

    // bishop generation
    private static long[] deg45 = new long[64];
    private static long[] deg135 = new long[64];
    private static long[] deg225 = new long[64];
    private static long[] deg315 = new long[64];

    /**
     * Gets all pawn passive moves for a given bitboard of pawns
     * @param pawns bitboard of pawns
     * @param allies bitboard of pawn allies
     * @param enemies bitboard of pawn enemies
     * @param movingAway true if moving up, false if moving down
     * @return a bitboard containing the possible moves for the set of pawns
     */
    public static long generatePawnPassiveMoves(long pawns, long allies, long enemies, boolean movingAway) {
        long moves;

        if(movingAway) {
            moves = (pawns << 8) | ((pawns << 16) & BitboardUtil.RANK_4);
        } else {
            moves = (pawns >> 8) | ((pawns >> 16) & BitboardUtil.RANK_5);
        }

        moves &= ~(allies | enemies);

        return moves;
    }

    /**
     * Gets all pawn attack moves for a given bitboard of pawns
     * @param pawns bitboard of pawns
     * @param allies bitboard of pawn allies
     * @param enemies bitboard of pawn enemies
     * @param movingAway true if moving up, false if moving down
     * @return a bitboard containing the possible attakcs for the set of pawns
     */
    public static long generatePawnAttackMoves(long pawns, long allies, long enemies, boolean movingAway) {
        long attacks;

        if(movingAway) {
            attacks = ((pawns << 7) & ~BitboardUtil.FILE_A) | ((pawns << 9) & ~BitboardUtil.FILE_H);
        } else {
            attacks = ((pawns >> 9) & ~BitboardUtil.FILE_A) | ((pawns >> 7) & ~BitboardUtil.FILE_H);
        }

        attacks &= enemies;
        attacks &= ~allies;

        return attacks;
    }

    /**
     * Gets all pawn passive/attack moves
     * @param pawns bitboard of pawns
     * @param allies bitboard of pawn allies
     * @param enemies bitboard of pawn enemies
     * @param movingAway true if moving up, false if moving down
     * @return a bitboard containing all possible attacks and passive moves
     */
    public static long generatePawnMoves(long pawns, long allies, long enemies, boolean movingAway) {
        return generatePawnAttackMoves(pawns, allies, enemies, movingAway) | generatePawnPassiveMoves(pawns, allies, enemies, movingAway);
    }

    /**
     * Gets all knights moves
     * @param knights a bitboard of knights
     * @param allies a bitboard of allies to the knights
     * @return a bitboard of the moves possible
     */
    public static long generateKnightMoves(long knights, long allies) {
        long moves = 0L;

        moves |= (knights << 17) & ~BitboardUtil.FILE_H;
        moves |= (knights << 15) & ~BitboardUtil.FILE_A;
        moves |= (knights << 10) & ~BitboardUtil.FILE_H & ~BitboardUtil.FILE_G;
        moves |= (knights << 6) & ~BitboardUtil.FILE_A & ~BitboardUtil.FILE_B;
        moves |= (knights >> 17) & ~BitboardUtil.FILE_A;
        moves |= (knights >> 15) & ~BitboardUtil.FILE_H;
        moves |= (knights >> 10) & ~BitboardUtil.FILE_A & ~BitboardUtil.FILE_B;
        moves |= (knights >> 6) & ~BitboardUtil.FILE_G & ~BitboardUtil.FILE_H;

        moves &= (~allies);

        return moves;
    }

    /**
     * Gets all king moves
     * @param kings a bitboard of kings
     * @param allies a bitboard of allies to the king
     * @return a bitboard of the possible moves for the kings
     */
    public static long generateKingMoves(long kings, long allies) {
        long moves = 0L;

        moves |= (kings << 8);
        moves |= (kings << 9) & ~BitboardUtil.FILE_H;
        moves |= (kings << 7) & ~BitboardUtil.FILE_A;
        moves |= (kings << 1) & ~BitboardUtil.FILE_H;
        moves |= (kings >> 1) & ~BitboardUtil.FILE_A;
        moves |= (kings >> 7) & ~BitboardUtil.FILE_H;
        moves |= (kings >> 9) & ~BitboardUtil.FILE_A;
        moves |= (kings >> 8);

        moves &= (~allies);

        return moves;
    }

    /*
     * Ideas for sliding moves from NagaSkaki: http://www.mayothi.com/nagaskakichess6.html
     */

    /**
     * Gets the moves for a single bishop
     * @param row row position for the bishop
     * @param col col position for the bishop
     * @param allies bitboard with the allies of the bishop
     * @param enemies bitboard with the enemies of the bishop
     * @return bitbaord of all possible moves for the bishop
     */
    public static long generateBishopMoves(int row, int col, long allies, long enemies) {
        long deg45 = getDeg45(row, col);
        long deg135 = getDeg135(row, col);
        long deg225 = getDeg225(row, col);
        long deg315 = getDeg315(row, col);

        long deg45Moves = deg45 & (enemies | allies);
        deg45Moves = (deg45Moves << 7) | (deg45Moves << 14) | (deg45Moves << 21) | (deg45Moves << 28) | (deg45Moves << 35) | (deg45Moves << 42);
        deg45Moves &= deg45;
        deg45Moves ^= deg45;

        long deg135Moves = deg135 & (enemies | allies);
        deg135Moves = (deg135Moves >> 9) | (deg135Moves >> 18) | (deg135Moves >> 27) | (deg135Moves >> 36) | (deg135Moves >> 45) | (deg135Moves >> 54);
        deg135Moves &= deg135;
        deg135Moves ^= deg135;

        long deg225Moves = deg225 & (enemies | allies);
        deg225Moves = (deg225Moves >> 7) | (deg225Moves >> 14) | (deg225Moves >> 21) | (deg225Moves >> 28) | (deg225Moves >> 35) | (deg225Moves >> 42);
        deg225Moves &= deg225;
        deg225Moves ^= deg225;

        long deg315Moves = deg315 & (enemies | allies);
        deg315Moves = (deg315Moves << 9) | (deg315Moves << 18) | (deg315Moves << 27) | (deg315Moves << 36) | (deg315Moves << 45) | (deg315Moves<< 54);
        deg315Moves &= deg315;
        deg315Moves ^= deg315;

        return (deg45Moves | deg135Moves | deg225Moves | deg315Moves) & ~allies;
    }

    /**
     * Generates all rook moves at a specific position
     * @param row row position of the rook
     * @param col col position of the rook
     * @param allies bitboard of rook allies
     * @param enemies bitboard of rook enemies
     * @return bitboard of all rook moves
     */
    public static long generateRookMoves(int row, int col, long allies, long enemies) {
        long right = getRight(row, col);
        long left = getLeft(row, col);
        long up = getUp(row, col);
        long down = getDown(row, col);

        long rightMoves = right & (allies | enemies);
        rightMoves = (rightMoves >> 1) | (rightMoves >> 2) | (rightMoves >> 3) | (rightMoves >> 4) | (rightMoves >> 5) | (rightMoves >> 6);
        rightMoves &= right;
        rightMoves ^= right;

        long leftMoves = left & (allies | enemies);
        leftMoves = (leftMoves << 1) | (leftMoves << 2) | (leftMoves << 3) | (leftMoves << 4) | (leftMoves << 5) | (leftMoves << 6);
        leftMoves &= left;
        leftMoves ^= left;

        long upMoves = up & (allies | enemies);
        upMoves = (upMoves << 8) | (upMoves << 16) | (upMoves << 24) | (upMoves << 32) | (upMoves << 40) | (upMoves << 48);
        upMoves &= up;
        upMoves ^= up;

        long downMoves = down & (allies | enemies);
        downMoves = (downMoves >> 8) | (downMoves >> 16) | (downMoves >> 24) | (downMoves >> 32) | (downMoves >> 40) | (downMoves >> 48);
        downMoves &= down;
        downMoves ^= down;

        return (rightMoves | leftMoves | upMoves | downMoves) & ~allies;
    }

    /**
     * Generates all queen moves at a specific position
     * @param row row position of the queen
     * @param col col position of the queen
     * @param allies bitboard of queen allies
     * @param enemies bitboard of queen enemies
     * @return bitboard of all queen moves
     */
    public static long generateQueenMoves(int row, int col, long allies, long enemies) {
        return generateBishopMoves(row, col, allies, enemies) | generateRookMoves(row, col, allies, enemies);
    }

    /*
     * Helper methods for rook and bishop generation
     */
    public static long getRight(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);
        if(right[index] != 0) {
            return right[index];
        }

        for(int c = col + 1; c <= 8; c++) {
            right[index] = BitboardUtil.setBit(right[index], row, c, true);
        }

        return right[index];
    }

    public static long getLeft(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);
        if(left[index] != 0) {
            return left[index];
        }

        for(int c = col - 1; c >= 1; c--) {
            left[index] = BitboardUtil.setBit(left[index], row, c, true);
        }

        return left[index];
    }

    public static long getUp(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);
        if(up[index] != 0) {
            return up[index];
        }

        for(int r = row + 1; r <= 8; r++) {
            up[index] = BitboardUtil.setBit(up[index], r, col, true);
        }

        return up[index];
    }

    public static long getDown(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);

        if(down[index] != 0) {
            return down[index];
        }
        for(int r = row - 1; r >= 1; r--) {
            down[index] = BitboardUtil.setBit(down[index], r, col, true);
        }

        return down[index];
    }

    public static long getDeg45(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);

        if(deg45[index] != 0L) {
            return deg45[index];
        }

        for(int change = 1; change <= 7; change++) {
            if(row + change > 8 || col + change > 8) break;

            deg45[index] = BitboardUtil.setBit(deg45[index], row + change, col + change, true);
        }

        return deg45[index];
    }

    public static long getDeg135(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);

        if(deg135[index] != 0L) {
            return deg135[index];
        }

        for(int change = 1; change <= 7; change++) {
            if(row - change < 1 || col + change > 8) break;

            deg135[index] = BitboardUtil.setBit(deg135[index], row - change, col + change, true);
        }

        return deg135[index];
    }

    public static long getDeg225(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);

        if(deg225[index] != 0L) {
            return deg225[index];
        }

        for(int change = 1; change <= 7; change++) {
            if(row - change < 1 || col - change < 1) break;

            deg225[index] = BitboardUtil.setBit(deg225[index], row - change, col - change, true);
        }

        return deg225[index];
    }

    public static long getDeg315(int row, int col) {
        int index = BitboardUtil.getIndex(row, col);

        if(deg315[index] != 0L) {
            return deg315[index];
        }

        for(int change = 1; change <= 7; change++) {
            if(row + change > 8 || col - change < 1) break;

            deg315[index] = BitboardUtil.setBit(deg315[index], row + change, col - change, true);
        }

        return deg315[index];
    }

}
