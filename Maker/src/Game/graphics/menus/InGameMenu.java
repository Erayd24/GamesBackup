package Game.graphics.menus;

import Game.Game;
import Game.STATE;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.input.Keyboard;


public class InGameMenu {

	private Font font;
	private Keyboard input;
	private Screen screen;
	private STATE State;
	private int wait = 0;

	public InGameMenu(Font font, Keyboard input) {
		this.input = input;
		this.font = font;
	}

	public void render(Screen screen) {
		this.screen = screen;
		setBackground();
		setText();
		screen.renderMenu();
	}
	
	private void setBackground() {
		
	}

	public void setText() {
		font.render(100, 50, -3, 0xff00ff00, "open", screen);
	}

	public void update() {
		wait++;
		if(input.inventory && wait > 30) {
			Game.changeState(STATE.GAME);
			wait = 0;
		}
	}
	
}
