package chess.tests;

import chess.logic.BitboardUtil;
import chess.logic.MoveGenerator;

public class Tests {

    public static void testBitboard() {
        System.out.println("Testing Bitboard...\n") ;

        // getBit tests
        System.out.println("Testing getBit()...");
        long bb = 0b1010;
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 1, 7), 1L);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 1, 5), 1L);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 8, 2), 0L);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 8, 3), 0L);
        System.out.println("getBit() passed!\n");

        // test set bit
        System.out.println("Testing setBit()...");
        bb = 0b1010;
        bb = BitboardUtil.setBit(bb, 7, 7, true);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 7, 7), 1L);
        bb = BitboardUtil.setBit(bb, 7, 7, false);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 7, 7), 0L);
        bb = BitboardUtil.setBit(bb, 2, 3, true);
        TestUtil.assertEquals(BitboardUtil.getBit(bb, 2, 3), 1L);
        System.out.println("setBit() passed!\n");

        BitboardUtil.print(BitboardUtil.FILE_A);

        System.out.println("Bitboard passed!\n");
    }

    public static void testMoveGenerator() {
        System.out.println("Testing MoveGenerator...\n");

        System.out.println("Testing pawn attack...\n");
        long knights = 0L;
        knights = BitboardUtil.setBit(knights, 4, 4, true);
        BitboardUtil.print(MoveGenerator.generateKnightMoves(knights, 0));

        System.out.println("Pawn attack passed!\n");

        long board = BitboardUtil.setBit(0, 1, 1, true);
        BitboardUtil.print(BitboardUtil.flipVertical(BitboardUtil.PAWN_START));

        System.out.println("Move Generator passed!\n");
    }


    public static void main(String[] args) {
        testBitboard();
        testMoveGenerator();
    }



}
