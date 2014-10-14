package Game.entity.projectile;

import Game.entity.spawner.ParticleSpawner;
import Game.graphics.Screen;
import Game.graphics.Sprite;

public class Arrow extends Projectile {

	public static final int rateOfFire = 25; //The higher the slower
	
	public Arrow(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 2;
		damage = 20;
		sprite = Sprite.arrow;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 6, 6, 3)) {
			level.add(new ParticleSpawner((int)x, (int)y, 50, 50, level));
			remove();
		}
		move();
	}
	
	protected void move() {
			x += nx;
			y += ny;
		if(distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x - 5 , (int)y - 10, this); //adjust this for positioning of shot
	}
}
