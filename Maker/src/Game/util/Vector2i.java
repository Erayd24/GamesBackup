package Game.util;

import java.io.Serializable;

public class Vector2i implements Serializable {
	private static final long serialVersionUID = 2734629248165065319L;
	
	private int x, y;
	private double xx, yy;
	
	//Constructor - Make a vector at 0, 0
	public Vector2i() {
		set(0, 0);
	}
	
	//Constructor - Make a vector from the x and y of another vector
	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}
	
	//Constructor - Make a new vector at a custom position
	public Vector2i(int x, int y) {
		set(x, y);
	}
	
	//Constructor - Make a new vector at a very precise position
	public Vector2i(double x, double y) {
		setDouble(xx, yy);
	}

	//Set the precise position of a vector for future use
	public void setDouble(double xx, double yy) {
		this.xx = xx;
		this.yy = yy;
	}
	
	//Set the position of a vector for future use
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Get the x position of a vector
	public int getX() {
		return x;
	}
	
	//Set a new x coordinate of a vector
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	//Get a new y coordinate of a vector
	public int getY() {
		return y;
	}
	
	//Set a new y coordinate of a vector
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
	//Add two vectors together
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	//Subtract two vectors from each other
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	//Direct distance of two vectors from each other - hypotenuse
	public static double getDistance(Vector2i v0, Vector2i v1) {
		double x = v0.getX() - v0.getX();
		double y = v0.getY() - v1.getY();
		return Math.sqrt(x * x + y * y);
	}
	
	//Check to see if a vector is the same as another vector
	public boolean equals(Object object) { //Overrides object class
		if(!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
}