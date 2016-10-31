package chess.gamestates;

import chess.game.Chess;
import chess.images.Images;
import chess.images.Sprite;
import chess.logic.BitboardUtil;
import chess.logic.BoardState;
import chess.logic.Location;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TwoPlayerState extends GameState {

	private int x, y, width, height;

	// sizes
	private int cellSize;
	private int boardDimension;

	// board state used to store info about board
	private BoardState currentState;

	// boolean and integers to store info
	private boolean cellSelected = false;
	private int rowSelected, colSelected;
	private long possibleMoves;

	// boolean to tell if it is white's turn, true if yes, false if no
	private int colorTurn = BoardState.WHITE;

	public TwoPlayerState(GameStateManager gsm) {
		super(gsm);
		this.x = 0;
		this.y = 0;
		this.width = Chess.WIDTH;
		this.height = Chess.HEIGHT;
		boardDimension = 8;
		cellSize = width / boardDimension;

		// board state used to store board info
		currentState = new BoardState();
	}

	public void update() {

	}

	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3) {
			undoLastMove();
		}

		if(cellSelected) {
			makeMove();

			// check if in check or checkmate
			if(currentState.isInCheck(BoardState.BLACK)) JOptionPane.showMessageDialog(null, "Black is in check!");
			else if(currentState.isInCheck(BoardState.WHITE)) JOptionPane.showMessageDialog(null, "White is in check!");

			if(currentState.isInCheckMate(BoardState.WHITE)) JOptionPane.showMessageDialog(null, "White is in check mate!");
			else if(currentState.isInCheckMate(BoardState.BLACK)) JOptionPane.showMessageDialog(null, "Black is in check mate!");

		} else {
			getPossibleMoves();
		}

	}

	private void undoLastMove() {
		currentState.undoLastMove();
		colorTurn *= -1;
	}

	private void makeMove() {
		int rowClicked = convertDrawRowToRow((Chess.mouseY - y) / cellSize);
		int colClicked = convertDrawColToCol((Chess.mouseX - x) / cellSize);

		if(BitboardUtil.isOn(possibleMoves, rowClicked, colClicked)) {
			currentState.makeMove(colorTurn, rowSelected, colSelected, rowClicked, colClicked);
			colorTurn *= -1;
		}

		possibleMoves = 0L;
		cellSelected = false;
	}

	private void getPossibleMoves() {
		rowSelected = convertDrawRowToRow((Chess.mouseY - y) / cellSize);
		colSelected = convertDrawColToCol((Chess.mouseX - x) / cellSize);

		if(currentState.isOccupied(colorTurn, rowSelected, colSelected)) {
			possibleMoves = currentState.getMoves(rowSelected, colSelected);
			cellSelected = true;
		}
	}

	public void draw(Graphics2D g) {
		drawCells(g);
		drawPieces(g);
		if(cellSelected) {
			drawPossibleMoves(g);
		}
	}

	/**
	 * Draws the background board onto the screen as individual squares
	 * @param g Graphics object used to draw
	 */
	public void drawCells(Graphics2D g) {
		for(int r = 0; r < boardDimension; r++) {
			for(int c = 0; c < boardDimension; c++) {
				if((r % 2 == 0 && c % 2 == 0) | (r % 2 == 1 && c % 2 == 1)) g.setColor(Color.WHITE);
				else g.setColor(Color.BLACK);

				g.fillRect(x + c * cellSize, y + r * cellSize, cellSize, cellSize);
			}
		}
	}

	/**
	 * Draws the possible moves for the selected piece
	 * @param g Graphics object used to draw
	 */
	public void drawPossibleMoves(Graphics2D g) {
		g.setColor(new Color(0.2f, 0.2f, 0.7f, 0.5f));
		for(int r = 1; r <= 8; r++) {
			for(int c = 1; c <= 8; c++) {
				if(BitboardUtil.isOn(possibleMoves, r, c)) {
					g.fillRect(x + convertColToDrawCol(c) * cellSize, y + convertRowToDrawRow(r) * cellSize, cellSize, cellSize);
				}
			}
		}
	}

	/**
	 * Draws all the pieces
	 * @param g Graphics object to use for drawing
	 */
	public void drawPieces(Graphics2D g) {
		drawWhitePieces(g);
		drawBlackPieces(g);
	}

	/**
	 * Draws all of the white pieces in their current state
	 * @param g the Graphics object to be used
	 */
	public void drawWhitePieces(Graphics2D g) {
		long[] whitePieces = currentState.getWhitePieces();
		for(int i = 0; i < whitePieces.length; i++) {
			long set = whitePieces[i];
			ArrayList<Location> positions = BitboardUtil.getBitboardAsList(set);
			Sprite piece = getWhiteImage(i);
			for(Location pos: positions) {
				piece.drawOnCenterOfCell(g, convertColToDrawCol(pos.getCol()) * cellSize, convertRowToDrawRow(pos.getRow()) * cellSize, cellSize);
			}
		}
	}

	/**
	 * Draws all black pieces in their current state
	 * @param g the Graphics object used for drawing
	 */
	public void drawBlackPieces(Graphics2D g) {
		long[] blackPieces = currentState.getBlackPieces();

		for(int i = 0; i < blackPieces.length; i++) {
			long set = blackPieces[i];
			ArrayList<Location> positions = BitboardUtil.getBitboardAsList(set);
			Sprite piece = getBlackImage(i);
			for(Location pos: positions) {
				piece.drawOnCenterOfCell(g, convertColToDrawCol(pos.getCol()) * cellSize, convertRowToDrawRow(pos.getRow()) * cellSize, cellSize);
			}
		}
	}


	/*
     * Helper methods
     */
	public int convertRowToDrawRow(int row) {
		return -row + 8;
	}
	public int convertColToDrawCol(int col) {
		return col - 1;
	}
	public int convertDrawRowToRow(int row) {
		return -row + 8;
	}
	public int convertDrawColToCol(int col) {
		return col + 1;
	}

	/**
	 * Gets the image for a black piece from the Images class
	 * @param index index of the piece
	 * @return the correct image for the index
	 */
	public Sprite getWhiteImage(int index) {
		switch(index) {
			case BoardState.PAWNS:
				return Images.WHITE_PAWN;
			case BoardState.BISHOPS:
				return Images.WHITE_BISHOP;
			case BoardState.KINGS:
				return Images.WHITE_KING;
			case BoardState.KNIGHTS:
				return Images.WHITE_KNIGHT;
			case BoardState.QUEENS:
				return Images.WHITE_QUEEN;
			case BoardState.ROOKS:
				return Images.WHITE_ROOK;
			default:
				throw new IndexOutOfBoundsException("The index doesn't exist!");
		}
	}

	/**
	 * Gets the image for a black piece from the Images class
	 * @param index index of the piece
	 * @return the correct image for the index
	 */
	public Sprite getBlackImage(int index) {
		switch(index) {
			case BoardState.PAWNS:
				return Images.BLACK_PAWN;
			case BoardState.BISHOPS:
				return Images.BLACK_BISHOP;
			case BoardState.KINGS:
				return Images.BLACK_KING;
			case BoardState.KNIGHTS:
				return Images.BLACK_KNIGHT;
			case BoardState.QUEENS:
				return Images.BLACK_QUEEN;
			case BoardState.ROOKS:
				return Images.BLACK_ROOK;
			default:
				throw new IndexOutOfBoundsException("The index doesn't exist!");
		}
	}
	
}


