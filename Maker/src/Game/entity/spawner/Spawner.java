package Game.entity.spawner;

import Game.entity.Entity;
import Game.entity.particle.Particle;
import Game.level.Level;

public class Spawner extends Entity{
	private static final long serialVersionUID = -1756996502563639175L;

	//Types of spawned entities
	public enum Type {
		MOB, PARTICLE
	}
	
	protected Type type;
	
	//Constructor
	//Spawn a type of something in a level of a specific amount
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		for(int i = 0; i < amount; i++) {
			if(type == Type.PARTICLE){
				level.add(new Particle(x, y, 50));
			}
		}
	}
}
