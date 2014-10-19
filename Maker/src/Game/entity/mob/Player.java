package Game.entity.mob;

import java.io.Serializable;

import Game.Game;
import Game.STATE;
import Game.entity.projectile.Arrow;
import Game.entity.projectile.Projectile;
import Game.graphics.AnimatedSprite;
import Game.graphics.Screen;
import Game.graphics.Sprite;
import Game.graphics.SpriteSheet;
import Game.input.Keyboard;
import Game.input.Mouse;

public class Player extends Mob implements Serializable {
	private static final long serialVersionUID = -7300849165326826638L;
	
		private Keyboard input;
		private Sprite sprite;
		
		private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
		private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
		private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
		private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
		private AnimatedSprite animSprite = down;
		
		private int fireRate = 0;
		private double speed = 1;
		private double xa, ya = 0;
		private int wait = 0;
		
		//Constructor
		public Player(Keyboard input) {
			this.input = input;
			sprite = Sprite.player_forward;
			frameRate();
		}
		
		//Set framerate of individual animations
		private void frameRate() {
			up.setFrameRate(8);
			down.setFrameRate(8);
			left.setFrameRate(5);
			right.setFrameRate(5);
		}

		//Constructor
		//For a specific spawn point
		public Player(int x, int y, Keyboard input) { 
			this.x = x;
			this.y = y; 
			this.input = input;
			sprite = Sprite.player_forward;
			frameRate();
			fireRate = Arrow.rateOfFire;
		}
		
		//Update the sprite animation based on an input
		public void update() {
			wait++;
			xa = 0;
			ya = 0;
			if(walking) animSprite.update();
			else animSprite.setFrame(0);
			
			if(fireRate > 0) fireRate--;
			
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
			
			if(input.inventory && wait > 10) { //Inventory key is 'i'
				Game.changeState(STATE.INGAMEMENU);
				wait = 0;
			}
			clear();
			updateShooting();
		}
		
		//Remove projectiles shot by the player
		private void clear() {
			for(int i = 0; i < level.getProjectiles().size(); i++) {
				Projectile p = level.getProjectiles().get(i);
				if(p.isRemoved()) level.getProjectiles().remove(i);
			}
		}

		//Shoot a projectile
		private void updateShooting() { 
			if(Mouse.getButton() == 1 && fireRate <= 0) {
				double dx = Mouse.getX() - Game.getWindowWidth() / 2;
				double dy = Mouse.getY() - Game.getWindowHeight() / 2;
				double dir = Math.atan2(dy, dx);
				shoot(x, y, dir);
				fireRate = Arrow.rateOfFire;
			}
		}
		
		//Render the player based on it's location
		public void render(Screen screen) {
			sprite = animSprite.getSprite();
			screen.renderMob(x - 16, y - 16, sprite);
		}
}
