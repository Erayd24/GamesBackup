package Game.graphics.menus;

import java.awt.Font;

import Game.graphics.Screen;

public class InGameMenu {

	private Font boldFont = new Font("arial", Font.BOLD, 50); //big Bold Font

	public void render(Screen screen) {
		setFont();
		screen.renderMenu();
	}
	
	public void setFont() {
		/*Graphics g = new Graphics();
		screen.setFont(boldFont);
		screen.setColor(Color.white);
		screen.drawString("Menu", Game.getWindowWidth(), 100);
		 */
	}
	
	//Could I use level to make a level which is just an image that overlays the whole screen?
}
