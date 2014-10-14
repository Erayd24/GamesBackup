package mygames.Maker.entity.mob;

import mygames.Maker.entity.Entity;
import mygames.Maker.entity.projectile.Arrow;
import mygames.Maker.entity.projectile.Projectile;
import mygames.Maker.graphics.Screen;

public abstract class Mob extends Entity{ //Handles a move method for moving mobs

		protected boolean moving = false;
		protected boolean walking = false;
		
		protected enum Direction {
			UP, DOWN, LEFT, RIGHT
		}
		
		protected Direction dir;
		
		public void move(double xa, double ya) {
			if(xa != 0 && ya != 0) { //Process collision separately to slide on walls
				move(xa, 0);
				move(0, ya);
				return;
			}
			
			if(xa > 0) dir = Direction.RIGHT;
			if(xa < 0) dir = Direction.LEFT;
			if(ya > 0) dir = Direction.DOWN;
			if(ya < 0) dir = Direction.UP;

			//Set speeds for each Mob
			while(xa != 0) {
				if(Math.abs(xa) > 1) {
					if (!collision(abs(xa), ya)) {
						this.x += abs(xa);
					}
					xa -= abs(xa);
				} else {
					if (!collision(abs(xa), ya)) {
						this.x += xa;
					}
					xa = 0;
				}
			}
			
			while(ya != 0) {
				if(Math.abs(ya) > 1) {
					if (!collision(xa, abs(ya))) {
						this.y += abs(ya);
					}
					ya -= abs(ya);
				} else {
					if (!collision(xa, abs(ya))) {
						this.y += ya;
					}
					ya = 0;
				}
			}
		}
			
		private int abs(double value) {
			if(value < 0) return -1;
			return 1;
		}
		
		protected void shoot(double x, double y, double dir) {
			//dir *= (180 / Math.PI);
			Projectile p = new Arrow(x, y, dir);
			level.add(p);
		}
		
		private boolean collision(double xa, double ya) { //Collision detection
			boolean solid = false;
			for(int c = 0; c < 4; c++) {
				double xt = ((x + xa) - c % 2 * 15) / 16;
				double yt = ((y + ya) - c / 2 * 15) / 16; //y direction
				int ix = (int) Math.ceil(xt); //left
				int iy = (int) Math.ceil(yt); //up
				if(c % 2 == 0) ix = (int) Math.floor(xt); //right
				if(c / 2 == 0) iy = (int) Math.floor(yt); //down
				if(level.getTile(ix, iy).solid()) solid = true;
			}
			return solid;
		}
		
		public abstract void render(Screen screen);
		public abstract void update();
}
