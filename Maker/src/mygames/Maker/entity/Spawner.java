package mygames.Maker.entity;

import java.util.ArrayList;
import java.util.List;

import mygames.Maker.entity.particle.Particle;
import mygames.Maker.level.Level;

public class Spawner extends Entity{
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		for(int i = 0; i < amount; i++) {
			if(type == type.PARTICLE){
				level.add(new Particle(x, y, 50));
			}
		}
	}
}
