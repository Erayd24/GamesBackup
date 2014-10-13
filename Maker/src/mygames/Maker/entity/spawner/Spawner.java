package mygames.Maker.entity.spawner;

import mygames.Maker.entity.Entity;
import mygames.Maker.entity.particle.Particle;
import mygames.Maker.level.Level;

public abstract class Spawner extends Entity{
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
