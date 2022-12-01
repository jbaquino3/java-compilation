package rectangle;

import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;   
import java.util.Collections;

public class Rectangle implements Comparable<Rectangle> {
	private Point lowerLeft, upperRight;
	
	// Constructor
	Rectangle(Point lowerLeft, Point upperRight) throws IllegalArgumentException {
		if(lowerLeft.getX() < upperRight.getX() && lowerLeft.getY() < upperRight.getY()) {
			this.lowerLeft = lowerLeft;
			this.upperRight = upperRight;
		} else {
			throw new IllegalArgumentException("Invalid coordinates");
		}
	}
	
	public int compareTo(Rectangle r){
		if(upperRight.getY() < r.upperRight.getY()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public Point getLowerRight() {
		return new Point((int) upperRight.getX(),(int) lowerLeft.getY());
	}
	
	public Point getUpperLeft() {
		return new Point((int) lowerLeft.getX(),(int) upperRight.getY());
	}
	
	public boolean contains(Point p) {
		if(p.getX() >= lowerLeft.getX() && p.getY() >= lowerLeft.getY() && p.getX() <= upperRight.getX() && p.getY() <= upperRight.getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean intersects(Rectangle rec) {
		Point recLowerLeft = new Point((int) rec.getUpperLeft().getX(), (int) rec.getLowerRight().getY());
		Point recUpperRight = new Point((int) rec.getLowerRight().getX(), (int) rec.getUpperLeft().getY());
		
		// Intersection happens when one of the corners of the rectangle is within the other
		return
			rec.contains(lowerLeft) ||
			rec.contains(upperRight) ||
			rec.contains(getUpperLeft()) ||
			rec.contains(getLowerRight()) ||
			this.contains(recLowerLeft) ||
			this.contains(recUpperRight) ||
			this.contains(rec.getUpperLeft()) ||
			this.contains(rec.getLowerRight())
		;
	}
	
	public static boolean anyIntersect(Rectangle[] recs) {
		for(int i=0; i<recs.length; i++) {
			for(int j=i+1; j<recs.length; j++) {
				if(recs[i].intersects(recs[j])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static Rectangle randRect() {
		Random r = new Random();
		Point lowerLeft = new Point(r.nextInt(100), r.nextInt(100));
		Point upperRight = new Point(r.nextInt(100), r.nextInt(100));
		
		try {
			Rectangle rec = new Rectangle(lowerLeft, upperRight);
			return rec;
		} catch (IllegalArgumentException e) {
			return randRect();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
		
		// Generate 50 rectangles
		for(int i=0; i<50; i++) {
			recs.add(Rectangle.randRect());
		}
		
		Collections.sort(recs);
		
		for(Rectangle rec:recs){  
    	    System.out.println(rec.getUpperLeft() + ", " + rec.getLowerRight());  
		} 
	}
}
