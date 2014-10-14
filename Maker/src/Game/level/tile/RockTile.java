package Game.level.tile;

import Game.graphics.Screen;
import Game.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //multiply by sixteen
	}

	public boolean solid() { //Is the tile a wall?
		return true;
	}
}
