package chess.gamestates;

import chess.game.Chess;
import chess.logic.BoardHandler;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class TwoPlayerState extends GameState {

	private BoardHandler boardHandler;

	public TwoPlayerState(GameStateManager gsm) {
		super(gsm);
		boardHandler = new BoardHandler(0, 0, Chess.WIDTH, Chess.HEIGHT);
	}
	
	public void update() {

	}
	
	public void draw(Graphics2D g) {
		boardHandler.draw(g);
	}
	
	public void mouseClicked(MouseEvent e) {
		boardHandler.mouseClicked(e);
	}
	
}


