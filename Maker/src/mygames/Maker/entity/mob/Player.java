package mygames.Maker.entity.mob;

import mygames.Maker.Game;
import mygames.Maker.entity.projectile.Arrow;
import mygames.Maker.entity.projectile.Projectile;
import mygames.Maker.graphics.AnimatedSprite;
import mygames.Maker.graphics.Screen;
import mygames.Maker.graphics.Sprite;
import mygames.Maker.graphics.SpriteSheet;
import mygames.Maker.input.Keyboard;
import mygames.Maker.input.Mouse;

public class Player extends Mob{

		private Keyboard input;
		private Sprite sprite;
		private boolean walking = false;
		private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
		private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
		private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
		private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
		
		private AnimatedSprite animSprite = down;
		
		private int fireRate = 0;
		
		public Player(Keyboard input) {
			this.input = input;
			sprite = Sprite.player_forward;
			frameRate();
		}
		
		private void frameRate() {
			up.setFrameRate(8);
			down.setFrameRate(8);
			left.setFrameRate(5);
			right.setFrameRate(5);
		}

		public Player(int x, int y, Keyboard input) {
			this.x = x;
			this.y = y; //Goes back to Entity Super class
			this.input = input;
			sprite = Sprite.player_forward;
			frameRate();
			fireRate = Arrow.rateOfFire;
		}
		
		public void update() { //Player movement input
			if(walking) animSprite.update();
			else animSprite.setFrame(0);
			if(fireRate > 0) fireRate--;
			double xa = 0, ya = 0;
			double speed = 1;
			
			if(input.up) {
				animSprite = up;
				ya -= speed;
			} else if(input.down) {
				animSprite = down;
				ya += speed; 
			}
			if(input.left) {
				animSprite = left;
				xa -= speed; 
			} else if(input.right) {
				animSprite = right;
				xa += speed; 
			}
			
			if(xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			} else {
				walking = false;
			}
			clear();
			updateShooting();
		}
		
		private void clear() {
			for(int i = 0; i < level.getProjectiles().size(); i++) {
				Projectile p = level.getProjectiles().get(i);
				if(p.isRemoved()) level.getProjectiles().remove(i);
			}
		}

		private void updateShooting() { //shooting projectiles from the player(Possible item?)
			if(Mouse.getButton() == 1 && fireRate <= 0) {
				double dx = Mouse.getX() - Game.getWindowWidth() /2;
				double dy = Mouse.getY() - Game.getWindowHeight() /2;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir);
				fireRate = Arrow.rateOfFire;
			}
		}
		
		public void render(Screen screen) {
			sprite = animSprite.getSprite();
			screen.renderMob((int)(x - 16), (int)(y - 16), sprite);
		}
}
