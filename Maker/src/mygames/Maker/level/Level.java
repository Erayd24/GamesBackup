package mygames.Maker.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mygames.Maker.entity.Entity;
import mygames.Maker.entity.mob.Player;
import mygames.Maker.entity.particle.Particle;
import mygames.Maker.entity.projectile.Projectile;
import mygames.Maker.entity.util.Vector2i;
import mygames.Maker.graphics.Screen;
import mygames.Maker.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	private List<Player> players = new ArrayList<Player>();
	
	private Comparator<Node> nodeSorter = new Comparator<Node>() { //Sorting nodes by fCost or total cost
		public int compare(Node n0, Node n1) {
			if(n1.fCost < n0.fCost)return 1; 
			if(n1.fCost > n0.fCost)return -1; 
			return 0;
		}
	};
	
	public static Level spawn = new SpawnLevel("/levels/spawnlevel.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level (String path) {
		loadLevel(path);
		generateLevel();
	} //Use this to load a pre-made level map
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++){
		entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		for(int i = 0; i < players.size(); i++){
			players.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++){ 
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).isRemoved()) players.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	private void time() {
		
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4; 
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if(getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) { //Use this to render levels
		screen.setOffset(xScroll,  yScroll);
		int x0 = xScroll >> 4; //same as divided by 16 Left side of screen
		int x1 = (xScroll + screen.width + 16) >>  4;//Right side of the screen
		int y0 = yScroll >> 4; //Top edge of screen
		int y1 = (yScroll + screen.height + 16) >> 4; //Bottom edge of screen
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x,y).render(x , y, screen); //For Random Generating
			} //end for x loop
		} //end for y loop
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).render(screen);
		}
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(screen);
		}
	} //end render
	
	public void add(Entity e) {
		e.init(this);
		if(e instanceof Particle) {
			particles.add((Particle)e);
		} else if( e instanceof Projectile) {
			projectiles.add((Projectile)e);
		} else if(e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}
	
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	//A* search algorithm for path finding
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		 List<Node> openList = new ArrayList<Node>();
		 List<Node> closedList = new ArrayList<Node>();
		 Node current = new Node(start, null, 0, getDistance(start, goal));
		 openList.add(current);
		 
		 while(openList.size() > 0) {
			 Collections.sort(openList, nodeSorter); //Java class that sorts lists
			 current = openList.get(0);
			 if(current.tile.equals(goal)) {
				 List<Node> path = new ArrayList<Node>();
				 while(current.parent != null) { //Parent only null on start
					 path.add(current);
					 current = current.parent;
				 }
				 openList.clear();
				 closedList.clear();
				 return path;
			 }
			 openList.remove(current);
			 closedList.add(current);
			 
			 for(int i = 0; i < 9; i++) {
				 if(i == 4) continue; //where the player would be
				 int x = current.tile.getX();
				 int y = current.tile.getY();
				 int xi = (i % 3) - 1; //-1, 0, 1
				 int yi = (i / 3) - 1; //These are the value around
				 Tile at = getTile(x + xi, y + yi); //Sets the tile being looked at to this tile
				 if(at == null) continue; //Out of map 
				 if(at.solid()) continue; //Wall
				 Vector2i a = new Vector2i(x + xi, y + yi);
				 double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95); //These two doubles can be tweaked to get desired results on path
				 double hCost = getDistance(a, goal);
				 Node node = new Node(a, current, gCost, hCost);
				 if(vecInList(closedList, a) && gCost >= node.gCost) continue;
				 if(!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			 } //end for
		 } //end while
		 closedList.clear();
		 return null; //if no path was found
	} //end method
	
	private boolean vecInList(List<Node> list, Vector2i vector) {
		for(Node n : list) {
			if(n.tile.equals(vector)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	public List<Entity> getEntities(Entity e, int radius) { //Return all entities
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(entity);
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = x - ex;
			int dy = y - ey;
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(player);
		}
		return result;
	}
	
	//Grass = 0xff00ff00
	//Flower = 0xffffff00
	//Rock = 0xff7f7f00
	//Brick Wall = 0xff7f0000
	public Tile getTile(int x, int y) { //Run this under render above which takes in these tiles
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile; //The boundaries
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass; 
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower; 
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock; 
		if (tiles[x + y * width] == Tile.col_wallBrick) return Tile.wallBrick; 
		return Tile.voidTile; //A black tile
	}
}
