package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.graphics.AnimatedSprite;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;
import Game.input.Mouse;

public class TitleMenu extends Menu {

	private Font font;
	private Keyboard input;
	private Sprite sprite;
	
	private int wait;
	private int width;
	private int height;
	private int location;
	private int menuHeight = 134;
	private int x = 118, y = 153;
	
	private static OPTION option;
	public int[] pixels;
	
	private AnimatedSprite menuArrow = new AnimatedSprite(SpriteSheet.menuArrow, 32, 32, 4);
	
	public TitleMenu(Keyboard input, Mouse mouse) {
		this.input = input;
		font = new Font();
		option = OPTION.NONE;
		load("/menus/titlemenu1.png");
		menuArrow.setFrameRate(12);
	}
	
	public void update() {
		wait++;
		
		if(input.action && wait > 5 && location == 0) { //Load game
			wait = 0;
			location = 0;
			x = 85;
			y = 75;
			//Game.load("axoh17"); 
			load("/menus/titlemenu2.png");
			option = OPTION.LOADGAME;
		}
		
		if(input.action && wait > 5 && location == 1) { //New Game
			wait = 0;
			location = 0;
			x = 85;
			y = 75;
			load("/menus/titlemenu2.png");
			option = OPTION.NEWGAME;
			//Game.newGame();
		}
		
		//For when a newgame or load game choice hasnt been made
		if(input.down && wait > 5 && location < 1 && option == OPTION.NONE) {
			wait = 0;
			x -= 10;
			y += 30;
			location++;
		}
		if(input.up && wait > 5 && location > 0 && option == OPTION.NONE) {
			wait = 0;
			x += 10;
			y -= 30;
			location--;
		}
		
		//Controls for when the first choice has been made
		if(option == OPTION.NEWGAME || option == OPTION.LOADGAME) {
			if(input.back && wait > 10) {
				wait = 0;
				x = 118;
				y = 153;
				location = 0;
				option = OPTION.NONE;
				load("/menus/titlemenu1.png");
			}
			
			if(input.down && wait > 10 && location < 2) {
				y += 42;
				location++;
				wait = 0;
			}
			if(input.up && wait > 10 && location > 0) {
				y -= 42;
				location--;
				wait = 0;
			}
		}
		menuArrow.update();
	}

	
	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		showText(screen);
		sprite = menuArrow.getSprite();
		screen.renderSprite(x, y, sprite, false);
	}

	public void showText(Screen screen) {
		if(option == OPTION.NONE) font.render(140, 160, -4, 0, "Load Game", screen);
		if(option == OPTION.NONE) font.render(130, 190, -4, 0, "New Game", screen);
		if(option == OPTION.NONE) font.render(120, 50, -2, 0xffD8B400, "The\n  Judgement", screen);
		if(option == OPTION.NEWGAME) font.render(145, 30, -4, 0, "New Game", screen);
		if(option == OPTION.LOADGAME) font.render(135, 30, -4, 0, "Load Game", screen);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void load(String path) {
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

	public void setText(Screen screen) {
	}
	

}
