package mygames.Maker.level.tile;

import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;

public class Tile {

	//Use this class to add sprites in to tile 
		public Sprite sprite;
		
		public static Tile grass = new GrassTile(Sprite.grass);
		public static Tile flower = new FlowerTile(Sprite.flower);
		public static Tile rock = new RockTile(Sprite.rock);
		public static Tile wallBrick = new WallTile(Sprite.wallBrick);
		public static Tile voidTile = new voidTile(Sprite.voidSprite);
		
		public final static int col_grass = 0xff00ff00;
		public final static int col_flower = 0xffffff00;
		public final static int col_rock = 0xff7f7f00;
		public final static int col_wallBrick = 0xff7f0000;

		public Tile(Sprite sprite) {
			this.sprite = sprite;
		}
		
		public void render(int x, int y, Screen screen) {
			
		}
		
		public boolean solid() { //Is the tile a wall?
			return false;
		}
		
		public boolean breakable() {
			return false;
		}
}
