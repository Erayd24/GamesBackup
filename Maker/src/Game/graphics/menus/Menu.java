package Game.graphics.menus;

import Game.graphics.Screen;

public abstract class Menu {
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	public abstract void setText(Screen screen);
	
	public abstract void showText(Screen screen);
	
	public abstract int getWidth();;

	public abstract int getHeight();
	
	public abstract void load(String path);
}
