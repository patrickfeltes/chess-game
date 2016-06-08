package chess.logic;

import java.util.ArrayList;

public class BitboardUtil {

    // constants
    public static final long PAWN_START = (1L << 8) | (1L << 9) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 13) | (1L << 14) | (1L <<15);
    public static final long ROOK_START = (1L << 0) | (1L << 7);
    public static final long BISHOP_START = (1L << 5) | (1L << 2);
    public static final long KNIGHT_START = (1L << 1) | (1L << 6);
    public static final long QUEEN_START = (1L << 4);
    public static final long KING_START = (1L << 3);

    public static final long EMPTY = 0L;
    public static final long UNIVERSAL = ~EMPTY;

    public static final long RANK_1 = (0b11111111L);
    public static final long RANK_2 = (0b11111111L << 8);
    public static final long RANK_3 = (0b11111111L << 16);
    public static final long RANK_4 = (0b11111111L << 24);
    public static final long RANK_5 = (0b11111111L << 32);
    public static final long RANK_6 = (0b11111111L << 40);
    public static final long RANK_7 = (0b11111111L << 48);
    public static final long RANK_8 = (0b11111111L << 56);

    public static final long FILE_A = ((1L << 63) | (1L << 55) | (1L << 47) | (1L << 39) | (1L << 31) | (1L << 23) | (1L << 15) | (1L << 7));
    public static final long FILE_B = ((1L << 62) | (1L << 54) | (1L << 46) | (1L << 38) | (1 << 30) | (1 << 22) | (1 << 14) | (1 << 6));
    public static final long FILE_C = ((1L << 61) | (1L << 53) | (1L << 45) | (1L << 37) | (1 << 29) | (1 << 21) | (1 << 13) | (1 << 5));
    public static final long FILE_D = ((1L << 60) | (1L << 52) | (1L << 44) | (1L << 36) | (1 << 28) | (1 << 20) | (1 << 12) | (1 << 4));
    public static final long FILE_E = ((1L << 59) | (1L << 51) | (1L << 43) | (1L << 35) | (1 << 27) | (1 << 19) | (1 << 11) | (1 << 3));
    public static final long FILE_F = ((1L << 58) | (1L << 50) | (1L << 42) | (1L << 34) | (1 << 26) | (1 << 18) | (1 << 10) | (1 << 2));
    public static final long FILE_G = ((1L << 57) | (1L << 49) | (1L << 41) | (1L << 33) | (1 << 25) | (1 << 17) | (1 << 9) | (1 << 1));
    public static final long FILE_H = ((1L << 56) | (1L << 48) | (1L << 40) | (1L << 32) | (1 << 24) | (1 << 16) | (1 << 8) | (1 << 0));

    /**
     * gets true/false for a specific bit in the board, 1 <= row, col <= 8
     * @param board the board to get the bit from
     * @param row the row position of the bit, row 1 is bottom row
     * @param col the col position of the bit, col 1 is left col
     * @return true if the bit is on, false if the bit is off
     */
    public static long getBit(long board, int row, int col) {
        return (board >> getIndex(row, col)) & 1;
    }

    /**
     * Sets the specified row/col to on or off in the Bitboard
     * @param board the board to change
     * @param row row to set
     * @param col col to set
     * @param on on/off
     */
    public static long setBit(long board, int row, int col, boolean on) {
        if(on) {
            board = setOn(board, row, col);
        } else {
            board = setOff(board, row, col);
        }
        return board;
    }

    /**
     * Sets a specific row/col to on
     * @param board the board to change
     * @param row row to set
     * @param col col to set
     */
    public static long setOn(long board, int row, int col) {
        return board | (1L << getIndex(row, col));
    }

    /**
     * Sets a specific row/col to off
     * @param board the board to change
     * @param row row to set
     * @param col col to set
     */
    public static long setOff(long board, int row, int col) {
        return board & ~(1L << getIndex(row, col));
    }

    /**
     * Gets the bit position of a specific row/col
     * @param row row of position
     * @param col col of position
     * @return the bit position of the row and col combination
     */
    public static int getIndex(int row, int col) {
        return (row - 1) * 8 + (7 - (col - 1));
    }

    /**
     * Flips the board vertically, used for getting black start
     * @param board bitboard to be flipped
     * @return bitboard that has been flipped
     */
    public static long flipVertical(long board) {
        return ((board >> 56) | ((board << 40) & 0x00ff000000000000L) | ((board << 24) & 0x0000ff0000000000L) |
                ((board << 8) & 0x000000ff00000000L) | ((board >> 8) & 0x00000000ff000000L) | ((board >> 24) & 0x0000000000ff0000L) |
                ((board >> 40) & 0x000000000000ff00L) | board << 56);
    }

    /**
     * Method to determine if a position in a bitboard is on or off
     * @param board the bitboard to check
     * @param row the row to check
     * @param col the col to check
     * @return true if the bit is 1, false otherwise
     */
    public static boolean isOn(long board, int row, int col) {
        return getBit(board, row, col) == 1;
    }

    /**
     * Gets the board as a list
     * @param board bitboard
     * @return a list of locations of on bits
     */
    public static ArrayList<Location> getBitboardAsList(long board) {
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int r = 1; r <= 8; r++) {
            for(int c = 1; c <= 8; c++) {
                if(isOn(board, r, c)) locations.add(new Location(r, c));
            }
        }
        return locations;
    }

    /**
     * Prints the board
     * @param board the board to be printed
     */
    public static void print(long board) {
        for (int r = 8; r >= 1; r--) {
            for (int c = 1; c <= 8; c++) {
                if (getBit(board, r, c) == 0) System.out.print(". ");
                else System.out.print("1 ");
            }
            System.out.println();
        }
    }

}
