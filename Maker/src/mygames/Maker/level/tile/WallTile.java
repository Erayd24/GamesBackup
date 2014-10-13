package mygames.Maker.level.tile;

import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;

public class WallTile extends Tile{

	public WallTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //multiply by sixteen
	}

	public boolean solid() { //Is the tile a wall?
		return true;
	}
}
