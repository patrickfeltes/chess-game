package chess.gamestates;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> states;
	
	public GameStateManager() {
		states = new Stack<GameState>();
		addState(new MenuState(this));
	}
	
	public void update() {
		if(states.size() != 0) {
			states.peek().update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(states.size() != 0) {
			states.peek().draw(g);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(states.size() != 0) {
			states.peek().mouseClicked(e);
		}
	}
	
	public void addState(GameState s) {
		states.push(s);
	}
	
}
