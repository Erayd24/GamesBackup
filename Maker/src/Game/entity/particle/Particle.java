package Game.entity.particle;

import Game.entity.Entity;
import Game.graphics.Screen;
import Game.graphics.Sprite;

public class Particle extends Entity {
	private static final long serialVersionUID = 8613733125930510301L;

	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za; //adjustments
	
	//Constructor - Create a particle
	public Particle(int x, int y, int life) {
		this.x = x; //int
		this.y = y;
		this.xx = x; //double
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10); //Setting a random life length 0 - 9
		sprite = Sprite.particle_normal; //The sprite used for particles
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2;
	}
	
	public void update() {
		time++;
		if(time >= 7400) time = 0;
		if(time > life) remove();
		za -= 0.1;
		
		if(zz < 0) { //Bounce
			zz = 0;
			za *= -0.55; //Stopping movement
			xa *= 0.4; //Slowing movement after bounce
			ya *= 0.4;
		}
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	private void move(double x, double y) {
		if(collision(x, y)) { //Reverse direction of particles
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt); //left
			int iy = (int) Math.ceil(yt); //up
			if(c % 2 == 0) ix = (int) Math.floor(xt); //right
			if(c / 2 == 0) iy = (int) Math.floor(yt); //down
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int)xx, (int)yy - (int) zz, sprite, true); //xx - 4
	}
}
