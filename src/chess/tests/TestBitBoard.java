package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.logic.BitBoard;

public class TestBitBoard {
	
	@Test
	public void testGetBit() {
		BitBoard bb = new BitBoard(10L);
		assertEquals(bb.getBit(0, 1), 1);
		
		System.out.println("BitBoard.getBit(row, col) passed");
	}
	
	public static void main(String[] args) {
		TestBitBoard tb = new TestBitBoard();
		tb.testGetBit();
	}
	
}
