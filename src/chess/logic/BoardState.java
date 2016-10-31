package chess.logic;

import chess.logic.BitboardUtil;
import chess.logic.Move;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

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

	public static final int WHITE = 1;
	public static final int BLACK = -1;

	// stack of moves to store previous moves
	private Stack<Move> pastMoves;

	public BoardState() {
		whitePieces = new long[6];
		blackPieces = new long[6];
		pastMoves = new Stack<Move>();

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

	public long[] getBlackPieces() {
		return blackPieces;
	}
	public long[] getWhitePieces() {
		return whitePieces;
	}

	/**
	 * Gets the moves for a piece at a specific location
	 * @param r the row of the piece
	 * @param c the col of the piece
     * @return a bitboard of the moves for the piece
     */
	public long getMoves(int r, int c) {
		int color = getPieceColor(r, c);
		int pieceType = getPieceType(color, r, c);

		long moves = getPieceMoves(pieceType, color, r, c, getOccupied(BLACK), getOccupied(WHITE));

		// filter out moves that cause checkmate
		ArrayList<Location> moveLocations = BitboardUtil.getBitboardAsList(moves);
		for(Location move: moveLocations) {
			makeMove(color, r, c, move.getRow(), move.getCol());
			if(isInCheck(color)) {
				moves = BitboardUtil.setBit(moves, move.getRow(), move.getCol(), false);
			}
			undoLastMove();
		}

		return moves;
	}

	/**
	 * Makes a move on the board, move must be possible
	 * @param color the color of the piece to move
	 * @param startRow the startRow of the piece
	 * @param startCol the starting col of the piece
	 * @param endRow the ending row of the piece
     * @param endCol the ending col of the piece
     */
	public void makeMove(int color, int startRow, int startCol, int endRow, int endCol) {
		int pieceType = getPieceType(color, startRow, startCol);
		removePiece(color, startRow, startCol);
		addPiece(pieceType, color, endRow, endCol);

		Move move = new Move(color, pieceType, startRow, startCol, endRow, endCol);

		// remove enemy piece if there
		if(isOccupied(-color, endRow, endCol)) {
			move.addPieceTaken(endRow, endCol, getPieceType(-color, endRow, endCol), -color);
			removePiece(-color, endRow, endCol);
		}

		pastMoves.push(move);
	}

	/**
	 * Undoes the last move of play
	 */
	public void undoLastMove() {
		if(pastMoves.size() == 0) return;

		Move lastMove = pastMoves.pop();

		removePiece(lastMove.getColor(), lastMove.getEndRow(), lastMove.getEndCol());
		addPiece(lastMove.getType(), lastMove.getColor(), lastMove.getStartRow(), lastMove.getStartCol());

		if(lastMove.wasPieceTaken()) {
			addPiece(lastMove.getTypeTaken(), lastMove.getColorTaken(), lastMove.getRowTaken(), lastMove.getColTaken());
		}

	}

	/**
	 * Adds a piece of a specific color/type to the board
	 * @param pieceType type of the piece
	 * @param color color of the piece
	 * @param row row to be added at
     * @param col col to be added at
     */
	public void addPiece(int pieceType, int color, int row, int col) {
		if(color == WHITE) {
			whitePieces[pieceType] = BitboardUtil.setBit(whitePieces[pieceType], row, col, true);
		} else {
			blackPieces[pieceType] = BitboardUtil.setBit(blackPieces[pieceType], row, col, true);
		}
	}

	/**
	 * Method to remove a piece of specific color from the board
	 * @param color specifies the color
	 * @param row row to remove
	 * @param col col to remove
	 */
	public void removePiece(int color, int row, int col) {
		if(color == WHITE) {
			for(int i = 0; i < 6; i++) {
				whitePieces[i] = BitboardUtil.setBit(whitePieces[i], row, col, false);
			}
		} else {
			for(int i = 0; i < 6; i++) {
				blackPieces[i] = BitboardUtil.setBit(blackPieces[i], row, col, false);
			}
		}
	}

	public int maxi(int depth) {
		if(depth == 0) return evaluate(1);

		int max = Integer.MIN_VALUE;

		ArrayList<Move> moves = getAllMoves(WHITE);

		for(Move move: moves) {
			makeMove(WHITE, move.getStartRow(), move.getStartCol(), move.getEndRow(), move.getEndCol());
			int temp = mini(depth - 1);
			undoLastMove();
			if(temp > max) {
				max = temp;
			}
		}

		return max;
	}

	public int mini(int depth) {
		if(depth == 0) return -evaluate(1);

		int min = Integer.MAX_VALUE;

		ArrayList<Move> moves = getAllMoves(BLACK);

		for(Move move: moves) {
			makeMove(BLACK, move.getStartRow(), move.getStartCol(), move.getEndRow(), move.getEndCol());
			int temp = maxi(depth - 1);
			undoLastMove();
			if(temp < min) {
				min = temp;
			}
		}

		return min;
	}

	/**
	 * This is the root minimax call, it checks all possible current moves for the best outcome
	 * @return
     */
	public Move getAIMove() {
		ArrayList<Move> moves = getAllMoves(BLACK);
		System.out.println(moves.size());

		Move minimizingMove = null;
		int min = Integer.MAX_VALUE;

		for(Move move: moves) {
			makeMove(BLACK, move.getStartRow(), move.getStartCol(), move.getEndRow(), move.getEndCol());
			int val = mini(3);
			System.out.println(move.getColor() + " " + move.getStartRow() + " " + move.getStartCol() + " " + move.getEndRow() + " " + move.getEndCol() + " " + val);

			if(val < min) {
				min = val;
				minimizingMove = move;
			}

			undoLastMove();
		}

		return minimizingMove;
	}

	/**
	 * Evaluates the current board state
	 * @return a double representing the value of the board with respect to the maximizing player
     */
	public int evaluate(int color) {
		return 200 * color * (getPieceCount(KINGS, WHITE) - getPieceCount(KINGS, BLACK))
				+ 9 * color * (getPieceCount(QUEENS, WHITE) - getPieceCount(QUEENS, BLACK))
				+ 3 * color * (getPieceCount(BISHOPS, WHITE) - getPieceCount(BISHOPS, BLACK))
				+ 3 * color * (getPieceCount(KNIGHTS, WHITE) - getPieceCount(KNIGHTS, BLACK))
				+ 1 * color * (getPieceCount(PAWNS, WHITE) - getPieceCount(PAWNS, BLACK));
	}


	/**
	 * Checks if a specific color is in check
	 * @param color specifies the color
	 * @return true if the color is in check, false if not
     */
	public boolean isInCheck(int color) {
		long kings = (color == WHITE) ? whitePieces[KINGS] : blackPieces[KINGS];
		long whites = getOccupied(WHITE);
		long blacks = getOccupied(BLACK);

		long enemyMoves = 0L;

		// generate all the enemy moves
		for(int r = 1; r <= 8; r++) {
			for(int c = 1; c <= 8; c++) {
				enemyMoves |= getPieceMoves(getPieceType(-color, r, c), -color, r, c, blacks, whites);
			}
		}

		return (enemyMoves & kings) != 0L;
	}


	public boolean isInCheckMate(int color) {
		long pieces = getOccupied(color);

		ArrayList<Location> locations = BitboardUtil.getBitboardAsList(pieces);

		for(Location loc: locations) {
			long moves = getMoves(loc.getRow(), loc.getCol());
			ArrayList<Location> movesList = BitboardUtil.getBitboardAsList(moves);
			for(Location move: movesList) {
				makeMove(color, loc.getRow(), loc.getCol(), move.getRow(), move.getCol());
				if(!isInCheck(color)) {
					undoLastMove();
					return false;
				}
				undoLastMove();
			}
		}

		return true;
	}

	/**
	 * Gets the bitboard of moves for a specific piece, helper method for getMoves
	 * @param pieceType the type of the piece, PAWNS, KINGS, etc...
	 * @param color specifies the color
	 * @param row the row of the piece
	 * @param col the col of the piece
	 * @param blacks bitboard containing all black pieces
     * @param whites bitboard containing all white pieces
     * @return a bitboard of the moves for that piece
     */
	public long getPieceMoves(int pieceType, int color, int row, int col, long blacks, long whites) {
		if(color == 0 || pieceType == -1) return 0L;
		long pieces = BitboardUtil.setBit(0L, row, col, true);
		switch(pieceType) {
			case PAWNS:
				if(color == WHITE) {
					return MoveGenerator.generatePawnMoves(pieces, whites, blacks, true);
				} else {
					return MoveGenerator.generatePawnMoves(pieces, blacks, whites, false);
				}
			case KNIGHTS:
				if(color == WHITE) {
					return MoveGenerator.generateKnightMoves(pieces, whites);
				} else {
					return MoveGenerator.generateKnightMoves(pieces, blacks);
				}
			case BISHOPS:
				if(color == WHITE) {
					return MoveGenerator.generateBishopMoves(row, col, whites, blacks);
				} else {
					return MoveGenerator.generateBishopMoves(row, col, blacks, whites);
				}
			case QUEENS:
				if(color == WHITE) {
					return MoveGenerator.generateQueenMoves(row, col, whites, blacks);
				} else {
					return MoveGenerator.generateQueenMoves(row, col, blacks, whites);
				}
			case ROOKS:
				if(color == WHITE) {
					return MoveGenerator.generateRookMoves(row, col, whites, blacks);
				} else {
					return MoveGenerator.generateRookMoves(row, col, blacks, whites);
				}
			case KINGS:
				if(color == WHITE) {
					return MoveGenerator.generateKingMoves(pieces, whites);
				} else {
					return MoveGenerator.generateKingMoves(pieces, blacks);
				}
			default:
				return 0L;
		}
	}

	/**
	 * Gets the piece type for a specific row and col
	 * @param color specifies the color
	 * @param row row to check
	 * @param col col to check
     * @return piece type of the piece at the (row, col), -1 if there is no piece
     */
	public int getPieceType(int color, int row, int col) {
		if(color == 0) return -1;
		for(int i = 0; i < 6; i++) {
			if((color == WHITE) && BitboardUtil.isOn(whitePieces[i], row, col)) return i;
			else if((color == BLACK) && BitboardUtil.isOn(blackPieces[i], row, col)) return i;
		}
		return -1;
	}

	/**
	 * Gets the color of a piece in a specific row/col, PIECE MUST EXIST FOR METHOD TO WORK
	 * @param row row of the piece
	 * @param col col of the piece
     * @return returns color, 0 if no piece
     */
	public int getPieceColor(int row, int col) {
		for(int i = 0; i < 6; i++) {
			if(isOccupied(WHITE, row, col)) return WHITE;
			else if(isOccupied(BLACK, row, col)) return BLACK;
		}
		return 0;
	}

	/**
	 * Tells if a piece of a specific color is in a specific position on the current board
	 * @param color specifies the piece color
	 * @param r the row to look at
	 * @param c the col to look at
	 * @return true if the bitboard contains a piece of that color at the (r, c), false if otherwise
	 */
	public boolean isOccupied(int color, int r, int c) {
		long occupied = getOccupied(color);
		return BitboardUtil.isOn(occupied, r, c);
	}

	public ArrayList<Move> getAllMoves(int color) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int r = 1; r <= 8; r++) {
			for(int c =1; c <= 8; c++) {
				if(getPieceColor(r, c) == color) {
					int type = getPieceType(color, r, c);
					ArrayList<Location> moveLocations = BitboardUtil.getBitboardAsList(getMoves(r, c));
					for(Location loc: moveLocations) {
						moves.add(new Move(color, type, r, c, loc.getRow(), loc.getCol()));
					}
				}
			}
		}
		return moves;
	}

	public int getNumberOfMoves(int color) {
		int count = 0;
		for(int r = 1; r <= 8; r++) {
			for(int c = 1; c <= 8; c++) {
				if(getPieceColor(r, c) == color) {
					long moves = getMoves(r, c);
					count += BitboardUtil.numberOfOnBits(moves);
				}
			}
		}
		return count;
	}

	public int getPieceCount(int pieceType, int pieceColor) {
		if(pieceColor == WHITE) {
			return Long.bitCount(whitePieces[pieceType]);
		} else {
			return Long.bitCount(blackPieces[pieceType]);
		}
	}

	public long getOccupied(int color) {
		long res = 0L;

		for(int i = 0; i < 6; i++) {
			res |= (color == WHITE) ? whitePieces[i] : blackPieces[i];
		}
		return res;
	}

	/*public long getBlackOccupied() {
		long res = 0L;
		for(int i = 0; i < 6; i++) {
			res |= blackPieces[i];
		}
		return res;
	}

	public long getWhiteOccupied() {
		long res = 0L;
		for(int i = 0; i < 6; i++) {
			res |= whitePieces[i];
		}
		return res;
	}*/

}
