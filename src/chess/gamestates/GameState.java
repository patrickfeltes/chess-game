package chess.gamestates;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Super class for all game states
 */
public abstract class GameState {

	protected GameStateManager gsm;
	
	/**
	 * Constructor for GameState
	 * @param gsm GameStateManger
	 */
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void mouseClicked(MouseEvent e);
}
