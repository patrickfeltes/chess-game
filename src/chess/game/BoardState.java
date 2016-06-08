package chess.game;

import chess.logic.BitboardUtil;
import chess.logic.Move;

public class BoardState {

    // bitboards for black and white pieces
    private long[] whitePieces, blackPieces;

    // constants
    public static final int PAWNS = 0;
    public static final int KNIGHTS = 1;
    public static final int BISHOPS = 2;
    public static final int ROOKS = 3;
    public static final int QUEENS = 4;
    public static final int KINGS = 5;

    public BoardState() {
        whitePieces = new long[6];
        blackPieces = new long[6];

        whitePieces[PAWNS] = BitboardUtil.PAWN_START;
        whitePieces[KNIGHTS] = BitboardUtil.KNIGHT_START;
        whitePieces[BISHOPS] = BitboardUtil.BISHOP_START;
        whitePieces[ROOKS] = BitboardUtil.ROOK_START;
        whitePieces[QUEENS] = BitboardUtil.QUEEN_START;
        whitePieces[KINGS] = BitboardUtil.KING_START;

        for(int i = 0; i < blackPieces.length; i++) {
            blackPieces[i] = BitboardUtil.flipVertical(whitePieces[i]);
        }
    }

    public BoardState(long[] whitePieces, long[] blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    public long[] getBlackPieces() {
        return blackPieces;
    }

    public long[] getWhitePieces() {
        return whitePieces;
    }

}
