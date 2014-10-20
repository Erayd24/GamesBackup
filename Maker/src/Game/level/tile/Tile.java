package Game.level.tile;

import Game.graphics.Screen;
import Game.graphics.Sprite;

public class Tile {

	//Use this class to add sprites in to tile for access later
		public Sprite sprite;
		
		//Add new Tile options here
		public static Tile grass = new GrassTile(Sprite.grass);
		public static Tile flower = new FlowerTile(Sprite.flower);
		public static Tile rock = new RockTile(Sprite.rock);
		public static Tile wallBrick = new WallTile(Sprite.wallBrick);
		public static Tile voidTile = new voidTile(Sprite.voidSprite);
		
		//Add the color code that corresponds to that tile here
		public final static int col_grass = 0xff00ff00;
		public final static int col_flower = 0xffffff00;
		public final static int col_rock = 0xff7f7f00;
		public final static int col_wallBrick = 0xff7f0000;

		//Constructor
		public Tile(Sprite sprite) {
			this.sprite = sprite;
		}
		
		//Render the tile
		public void render(int x, int y, Screen screen) {
		}
		
		//Return true when an object is meant to be solid
		public boolean solid() {
			return false;
		}
		
		//Return true if an object is meant to be breakable - Unimplemented 
		public boolean breakable() {
			return false;
		}
}