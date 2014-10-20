package Game.util;

import java.io.Serializable;

public class Vector2i implements Serializable {
	private static final long serialVersionUID = 2734629248165065319L;
	
	private int x, y;
	private double xx, yy;
	
	public Vector2i() { //vector at origin
		set(0, 0);
	}
	
	public Vector2i(Vector2i vector) { //Vector from vector
		set(vector.x, vector.y);
	}
	
	public Vector2i(int x, int y) { //New vector with new points
		set(x, y);
	}
	
	public Vector2i(double x, double y) {
		setDouble(xx, yy);
	}

	public void setDouble(double xx, double yy) {
		this.xx = xx;
		this.yy = yy;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	
	//Direct distance of two vectors from each other
	public static double getDistance(Vector2i v0, Vector2i v1) {
		double x = v0.getX() - v0.getX();
		double y = v0.getY() - v1.getY();
		return Math.sqrt(x * x + y * y);
	}
	
	public boolean equals(Object object) { //Overrides object class
		if(!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
}
