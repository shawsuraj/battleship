package battleship;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// 1. Aircraft carrier: 5 squares long : A
// 2. Battleship: 4 squares long : B
// 3. Submarine: 3 squares long : S
// 4. Destroyer: 3 squares long : D
// 5. Patrol Boat: 2 squares long : P

/** 
 * This class will hold functions for ship.
*/
public class Ship {
	String name;		// Code name of ship
	String fullName;	// Full name of ship
	int size;			// Size of ship
	Point[] position;	// Coordinates of ship
	public int health;	// Health of ship = (size - number of times the ship got shot)
	
	/**
	 * Ship Constructor.
	 * 
	 * @param s Size of ship
	 * @param n Code name of ship
	 * @param f Full name of ship
	 */
	public Ship(int s, String n, String f) {
	    this.size = s;
	    this.name = n;
	    this.fullName = f;
	    this.health = size;
	  }
	
	/**
	 * Generates new position for the ships.
	 * 
	 * @return Point[] - Array of ships's coordinates.
	 */
	public Point[] newPosition() {
		position = new Point[size];
		
		Random rand = new Random();
		for(int i = 0; i < position.length; i++) {
		    position[i] = new Point();          
		}
		
		// Create the ship
		position[0].x = rand.nextInt(10);
		position[0].y = rand.nextInt(10);
		
		int x;
		int y;
		
		List<Integer> orientations = validOrientations(position[0]);
		int r = rand.nextInt(orientations.size());
		if (orientations.get(r) == 0) {
			x = -1;
			y = 0;
		}
		
		else if (orientations.get(r) == 1) {
			x = 0;
			y = -1;
		}
		
		else if (orientations.get(r) == 2) {
			x = 1;
			y = 0;
		}
		
		else {
			x = 0;
			y = 1;
		}
		
		for (int i = 1; i<size; i++) {
			position[i].x = position[i-1].x + x;
			position[i].y = position[i-1].y + y;
		}
		
		return (position);
		
	}
	
	/**
	 * Generates valid orientations for the ship.
	 * 
	 * Random orientation
	 * 0 = left
	 * 1 = top
	 * 2 = right
	 * 3 = bottom
	 *
	 * @param pos Current random of ship.
	 * @return List<Integer> - Array of ships's possible orientation(s).
	 */
	List<Integer> validOrientations(Point pos) {
		List<Integer> o = new ArrayList<Integer>();
		
		if(pos.x - size >= 0) o.add(0);
		
		if(pos.y - size >= 0) o.add(1);
		
		if(pos.x + size < 10) o.add(2);
		
		if(pos.y + size < 10) o.add(3);
		
		
		return o;
	}
	
	/**
	 * Print coordinates of the ship.
	 */
	void printCoord() {
		for (int i = 0; i < this.size; i++) {
			System.out.println("x -> " + position[i].x + " and y -> " + position[i].y);
		}
	}
	

	public boolean exists() {
		return false;
		
	}
	
	public boolean destroyed() {
		// Return true if exists otherwise false
		if (health == 0) {
			return true;
		}
		return false;
	}
	
}
