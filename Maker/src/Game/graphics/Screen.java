package Game.graphics;

import java.util.Random;

import Game.entity.mob.Chaser;
import Game.entity.mob.Follower;
import Game.entity.mob.Mob;
import Game.entity.projectile.Projectile;
import Game.level.tile.Tile;

public class Screen {

		public int width, height;
		public int[] pixels;
		public final int MAP_SIZE = 64; //Random map generating
		public final int MAP_SIZE_MASK = MAP_SIZE -1; //Random map generating
		public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; //Random map generator
		
		public int xOffset, yOffset;
		private Random random = new Random();
		
		private final int ALPHA_COL = 0xffff00ff; //pink
		
		//create a screen of a specific width and height
		public Screen(int width, int height) {
			this.width = width;
			this.height = height;
			pixels = new int[width * height];
		
			for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) { //Random map generating
				tiles[i] = random.nextInt(0xffffff);
			}
		}
		
		//Clean the screen of any pixels for the next image
		public void clear() {
			for(int i = 0; i < pixels.length; i++) {
				pixels[i] = 0;
			}
		}
		
		//Render a menu screen
		public void renderMenu() {
			
		}
		
		//Render an image on screen which can move with the player
		public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
			if(fixed) {
				xp -= xOffset;
				yp -= yOffset;
			}
			for (int y = 0; y < sprite.getHeight(); y++) {
				int ya = y + yp;
				for (int x = 0; x < sprite.getWidth(); x++) {
					int xa = x + xp;
					if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
					int col = sprite.pixels[x + y * sprite.getWidth()];
					if(col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = col;
				}
			}
		}
		
		public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
			if(fixed) {
				xp -= xOffset;
				yp -= yOffset;
			}
			for (int y = 0; y < sprite.getHeight(); y++) {
				int ya = y + yp;
				for (int x = 0; x < sprite.getWidth(); x++) {
					int xa = x + xp;
					if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
					int col = sprite.pixels[x + y * sprite.getWidth()];
					if(col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = color;
				}
			}
		}
		
		//Render tile movement
		public void renderTile(int xp, int yp, Tile tile) {
			xp -= xOffset;
			yp -= yOffset;
			for (int y = 0; y < tile.sprite.SIZE; y++) {
				int ya = y + yp;
				for (int x = 0; x < tile.sprite.SIZE; x++) {
					int xa = x + xp;
					if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				}
			}
		}
		
		//Render a projectile sprite1
		public void renderProjectile(int xp, int yp, Projectile p) {
			xp -= xOffset;
			yp -= yOffset;
			for (int y = 0; y < p.getSpriteSize(); y++) {
				int ya = y + yp;
				for (int x = 0; x < p.getSpriteSize(); x++) {
					int xa = x + xp;
					if(xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					int col =  p.getSprite().pixels[x + y * p.getSprite().SIZE];
					if (col != ALPHA_COL) pixels[xa + ya * width] = col;
				}
			}
		}
		
		//Render Mobs that can be different colors
		public void renderMob(int xp, int yp, Mob mob) {
			xp -= xOffset;
			yp -= yOffset;
			for (int y = 0; y < 32; y++) {
				int ya = y + yp;
				int ys = y;
				for (int x = 0; x < 32; x++) {
					int xa = x + xp;
					int xs = x;
					if(xa < -32 || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					int col =  mob.getSprite().pixels[xs + ys * 32];
					if((mob instanceof Chaser) && col == 0xff20066C) col = 0xff7F0000;
					if((mob instanceof Follower) && col == 0xff20066C) col = 0xffe8e83a;
					if (col != ALPHA_COL) pixels[xa + ya * width] = col;
				}
			}
		}
		
		//Rendering player movement
		public void renderMob(int xp, int yp, Sprite sprite) { 
			xp -= xOffset;
			yp -= yOffset;
			for (int y = 0; y < 32; y++) {
				int ya = y + yp;
				int ys = y;
				for (int x = 0; x < 32; x++) {
					int xa = x + xp;
					int xs = x;
					if(xa < -32 || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					int col =  sprite.pixels[xs + ys * 32];
					if (col != ALPHA_COL) pixels[xa + ya * width] = col;
				}
			}
		}
		
		public void setOffset(int xOffset, int yOffset) {
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}

		public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
			if(fixed) {
				xp -= xOffset;
				yp -= yOffset;
			}
			for(int x = xp; x < xp + height; x++) {
				if(yp >= this.height || x < 0 || x >= this.width) continue;
				if(yp > 0) pixels[x + yp * this.width] = color;
				if(yp + height >= this.height) continue;
				if(yp + height > 0) pixels[x + (yp + height) * this.width] = color;
			}
			for(int y = yp; y <= yp + height; y++) {
				if(xp >= this.width || y < 0 || y >= this.height) continue;
				if(xp > 0) pixels[y + xp * this.width] = color;
				if(xp + width >= this.width) continue;
				if(xp  + width > 0) pixels[(xp + width) + y * this.width] = color;
			}
		}		
}