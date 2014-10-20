package Game.entity.projectile;

import java.util.Random;

import Game.entity.Entity;
import Game.graphics.Sprite;

public abstract class Projectile extends Entity{
	private static final long serialVersionUID = -8413600608019722170L;
	
	final protected double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny; //new x, y for update
	protected double speed, range, damage;
	protected double distance;
	
	protected final Random random = new Random();
	
	//Constructor - Create a new projectile in a specific location x, y
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	//Get the sprite needed for the projectile
	public Sprite getSprite() {
		return sprite;
	}
	
	//Get the size of the sprite
	public int getSpriteSize() {
		return sprite.SIZE;
	}

	//move the projectile, this method is inherited by sub-classes
	protected void move() {
		
	}
}