package Game.level.tile;

import Game.graphics.Screen;
import Game.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //multiply by sixteen
	}

}
