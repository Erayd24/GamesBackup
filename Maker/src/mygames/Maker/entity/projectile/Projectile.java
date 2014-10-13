package mygames.Maker.entity.projectile;

import java.util.Random;

import mygames.Maker.entity.Entity;
import mygames.Maker.graphics.Sprite;

public abstract class Projectile extends Entity{
	
	final protected int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny; //new x/y
	protected double speed, range, damage;
	protected double distance;
	
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {
		
	}
}
