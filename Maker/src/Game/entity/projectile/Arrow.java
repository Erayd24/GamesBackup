package Game.entity.projectile;

import Game.entity.spawner.ParticleSpawner;
import Game.graphics.Screen;
import Game.graphics.Sprite;

public class Arrow extends Projectile {
	private static final long serialVersionUID = 8395058876106897504L;
	
	public static final int rateOfFire = 25; //The higher the slower
	
	//Constructor - new arrow projectile
	public Arrow(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 2;
		damage = 20;
		sprite = Sprite.arrow;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	//update the projectile location in the level, checks for collisions
	public void update() {
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 6, 6, 3)) {
			level.add(new ParticleSpawner((int)x, (int)y, 50, 50, level));
			remove();
		}
		move();
	}
	
	//Move the arrow by the next x and y
	protected void move() {
			x += nx;
			y += ny;
		if(distance() > range) remove();
	}
	
	//Calculate the distance of the hypotenuse
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	//Render the arrow on the screen
	public void render(Screen screen) {
		screen.renderProjectile((int)x - 5 , (int)y - 10, this); //adjust this for positioning of shot on player
	}
}