package Game.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class InGameMenu {

	private Font boldFont = new Font("arial", Font.BOLD, 50); //big Bold Font
	private static int width = 900;
	private static int height = (width / 16 * 9) * 3;

	public void render(Graphics g) {
		g.setFont(boldFont);
		g.setColor(Color.white);
		g.drawString("Menu", width, 100);
	}
	
	
}
