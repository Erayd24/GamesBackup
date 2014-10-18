package Game.graphics.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.STATE;
import Game.data.File;
import Game.graphics.Font;
import Game.graphics.Screen;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;


public class InGameMenu {

	private Font font;
	private Keyboard input;
	
	private int wait = 0;
	private int width; //of the image
	private int height;
	private int xRect = 22;
	private int yRect = 22;
	private int location = 0;
	private int menuHeight = 134;
	
	private OPTION option;
	public int[] pixels;
		
	private enum OPTION {
		ITEMS,
		EQUIPMENT,
		MAGIC,
		STATUS,
		SAVE,
		NONE
	};
	
	public InGameMenu(Keyboard input) {
		this.input = input;
		font = new Font();
		option = OPTION.NONE;
		load("/menus/ingamemenu.png"); 
	}

	public void render(Screen screen) {
		screen.renderMenu(0, 0, this);
		setText(screen);
		showText(screen);
		screen.drawRect(xRect, yRect, 108, 27, 0xffBC9D36, false);
	}

	private void showText(Screen screen) {
		if(option == OPTION.NONE) font.render(170, 35, -2, 0, "The Judgement", screen);
		if(option == OPTION.ITEMS) font.render(215, 35, -4, 0, "Items", screen);
		if(option == OPTION.EQUIPMENT) font.render(200, 35, -4, 0, "Equipment", screen);
		if(option == OPTION.MAGIC) font.render(220, 35, -4, 0, "Magic", screen);
		if(option == OPTION.STATUS) font.render(215, 35, -4, 0, "Status", screen);
		if(option == OPTION.SAVE) font.render(200, 35, -4, 0, "Save Game", screen);
	}

	public void setText(Screen screen) {
		font.render(9, 27, -4, 0, "Items", screen); //Location 0 - 4 of left groups
		font.render(13, 53, -5, 0, "Equipment", screen);
		font.render(15, 80, -4, 0, "Magic", screen);
		font.render(12, 107, -5, 0, "Status", screen);
		font.render(11, 135, -4, 0, "Save Game", screen);
	}

	public void update() {
		wait++;
		if(wait > 7500) wait = 0;
		if(input.inventory && wait > 10) {
			yRect = 22;
			Game.changeState(STATE.GAME);
			location = 0;
			option = OPTION.NONE;
			wait = 0;
		}
		
		//Inputs for when a group hasn't been chosen
		if(option == OPTION.NONE) {
			if(input.down && wait > 5) {
				if(yRect + 27 <= menuHeight) yRect += 27;
				location++;
				wait = 0;
			}
			if(input.up && wait > 5) {
				if(yRect - 27 >= 22) yRect = yRect -= 27;
				location--;
				wait = 0;
			} 
			if(input.action && wait > 5) {
				wait = 0;
				if(location == 0) { 
					option = OPTION.ITEMS;
				}
				if(location == 1) {
					option = OPTION.EQUIPMENT;
				}
				if(location == 2) {
					option = OPTION.MAGIC;
				}
				if(location == 3) {
					option = OPTION.STATUS;
				}
				if(location == 4) {
					option = OPTION.SAVE;
				}
			}
		} //end outOfGroup if
		
		//Inputs and changes for each group choice
		if(option == OPTION.ITEMS) {
			if(input.back){
				option = OPTION.NONE;
			}
		}
		if(option == OPTION.EQUIPMENT) {
			if(input.back){
				option = OPTION.NONE;
			}
		}
		if(option == OPTION.MAGIC) {
			if(input.back){
				option = OPTION.NONE;
			}
		}
		if(option == OPTION.STATUS) {
			if(input.action) Game.loadState("axoh17");
			if(input.back){
				option = OPTION.NONE;
			}
		}
		if(option == OPTION.SAVE) {
			if(input.action) Game.saveState("axoh17");
			if(input.back){
				option = OPTION.NONE;
			}
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
