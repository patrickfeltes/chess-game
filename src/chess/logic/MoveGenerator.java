package chess.logic;

/**
 * Static class that takes longs and returns moves
 */
public class MoveGenerator {

    public void generatePawnPassiveMoves(long pawns, long enemies, boolean movingAway) {
        long moves;
        if(movingAway) {

        } else {

        }


    }

    public static long generatePawnAttackMoves(long pawns, long enemies, boolean movingAway) {
        long attacks;

        if(movingAway) {
            long possibleMoves = ((pawns << 7) & ~BitboardUtil.FILE_H) | ((pawns << 9) & ~BitboardUtil.FILE_A);
            attacks = enemies & possibleMoves;
        } else {
            long possibleMoves = ((pawns >> 7) & ~BitboardUtil.FILE_A) | ((pawns >> 9) & ~BitboardUtil.FILE_H);
            attacks = enemies & possibleMoves;
        }

        return attacks;
    }



}
