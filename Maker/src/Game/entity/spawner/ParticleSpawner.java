package Game.entity.spawner;

import Game.entity.particle.Particle;
import Game.level.Level;

public class ParticleSpawner extends Spawner{
	private static final long serialVersionUID = 2015424310843900831L;
	
	@SuppressWarnings("unused")
	private int life;

	//Constructor
	//Create a type of spawner, specifically a particle spawner
	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level); //Spawner
		this.life = life;
		for(int i = 0; i < amount; i++) {
				level.add(new Particle(x, y, life));
		}
	}

}
