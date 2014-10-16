package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.STATE;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;


public class InGameMenu {

	private Font font;
	private Keyboard input;
	
	private int wait = 0;
	private int width;
	private int height;
	public int[] pixels;
	
	public InGameMenu(Font font, Keyboard input) {
		this.input = input;
		this.font = font;
		load("/menus/ingamemenu.png");
	}

	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		setText(screen);
	}

	public void setText(Screen screen) {
		font.render(7, 30, -3, 0, "Items", screen);
		font.render(11, 53, -5, 0, "Equipment", screen);
		font.render(12, 80, -3, 0, "Magic", screen);
		font.render(7, 107, -3, 0, "Status", screen);
		font.render(7, 135, -3, 0, "Save Game", screen);
	}

	public void update() {
		wait++;
		if(wait > 7500) wait = 0;
		if(input.inventory && wait > 30) {
			Game.changeState(STATE.GAME);
			wait = 0;
		}
	}
	
	private void load(String path) {
		try{	
			System.out.print("Trying to load: " + path + " ... ");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeeded!");
			this.width = image.getWidth();
			this.height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch  (IOException e) {
			e.printStackTrace();
		  } catch (Exception e) {
			  System.err.println(" failed!");
		  }
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
