package chess.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import chess.game.Chess;
import chess.ui.Button;

public class MenuState extends GameState {

	// Buttons
	private Button[] buttons = new Button[3];
	private String[] buttonNames = new String[] {"One Player", "Two Player", "Quit"};
	private final int ONE_PLAYER_BUTTON = 0;
	private final int TWO_PLAYER_BUTTON = 1;
	private final int QUIT_BUTTON = 2;
	
	private Font buttonFont = new Font("Arial", Font.PLAIN, 64);
    private int buttonX = 100;
    private int buttonWidth = Chess.WIDTH - 2 * buttonX;
    private int buttonHeight = 100;
    private int spacing = buttonHeight + 50;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(buttonX, i * spacing + spacing, buttonWidth, buttonHeight, buttonNames[i], buttonFont, Color.BLACK, Color.RED, Color.BLACK);
		}
	}
	
	public void update() {
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].update();
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].draw(g);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(buttons[ONE_PLAYER_BUTTON].isMouseInside()) {
			// start one player
		} else if(buttons[TWO_PLAYER_BUTTON].isMouseInside()) {
			this.gsm.addState(new TwoPlayerState(this.gsm));
		} else if(buttons[QUIT_BUTTON].isMouseInside()) {
			System.exit(0);
		}
		
			
	}
	
}
