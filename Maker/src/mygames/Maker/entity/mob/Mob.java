package mygames.Maker.entity.mob;

import mygames.Maker.entity.Entity;
import mygames.Maker.entity.projectile.Arrow;
import mygames.Maker.entity.projectile.Projectile;
import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;

public abstract class Mob extends Entity{ //Handles a move method for moving mobs

		protected boolean moving = false;
		protected boolean walking = false;
		
		protected enum Direction {
			UP, DOWN, LEFT, RIGHT
		}
		
		protected Direction dir;
		
		public void move(int xa, int ya) {
			if(xa != 0 && ya != 0) { //Process collision separately to slide on walls
				move(xa, 0);
				move(0, ya);
				return;
			}
			
			if(xa > 0) dir = Direction.RIGHT;
			if(xa < 0) dir = Direction.LEFT;
			if(ya > 0) dir = Direction.DOWN;
			if(ya < 0) dir = Direction.UP;

			if (!collision(xa, ya)) {
			x += xa;
			y += ya;
			}
		} //0 = north, 1 = east, 2 = south, 3 = west
				
		protected void shoot(int x, int y, double dir) {
			//dir *= (180 / Math.PI);
			Projectile p = new Arrow(x, y, dir);
			level.add(p);
		}
		
		private boolean collision(int xa, int ya) { //Collision detection
			boolean solid = false;
			for(int c = 0; c < 4; c++) {
				int xt = ((x + xa) + c % 2 * 4 - 2) / 16; //Changing pixel collision in x direction change two digits before the 16
				int yt = ((y + ya) + c / 2 * 12 + 3) / 16; //y direction
				if(level.getTile(xt, yt).solid()) solid = true;
			}
			return solid;
		}
		
		public abstract void render(Screen screen);
		public abstract void update();
}
