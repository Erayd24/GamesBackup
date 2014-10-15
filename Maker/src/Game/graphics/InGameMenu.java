package Game.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Game;

public class InGameMenu {

	private Font boldFont = new Font("arial", Font.BOLD, 50); //big Bold Font

	public void render(Graphics g) {
		g.setFont(boldFont);
		g.setColor(Color.white);
		g.drawString("Menu", Game.getWindowWidth(), 100);
	}
	
	
}
