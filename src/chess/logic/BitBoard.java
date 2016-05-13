package chess.logic;

public class BitBoard {

	private long board;
	/**
	 * Default constructor for BitBoard: sets board to empty board
	 */
	public BitBoard() {
		board = 0L;
	}
	
	/**
	 * Constructor for BitBoard
	 * @param board board to be represented in BitBoard
	 */
	public BitBoard(long board) {
		this.board = board;
	}
	
	/**
	 * Copy constructor
	 * @param bb BitBoard to copy 
	 */
	public BitBoard(BitBoard bb) {
		this.board = bb.getBoard();
	}
	
	/**
	 * gets true/false for a specific bit in the board, 0 <= row, col <= 7
	 * @param row the row position of the bit
	 * @param col the col position of the bit
	 * @return true if the bit is on, false if the bit is off
	 */
	public long getBit(int row, int col) {
		int index = row * 8 + col;
		return (board >> index) & 1;
	}
	
	/**
	 * Getter for board
	 * @return long representing board
	 */
	public long getBoard() {
		return board;
	}
	
}
