package battleship;

import java.awt.Point;
import java.util.Random;


// 1. Aircraft carrier: 5 squares long
// 2. Battleship: 4 squares long
// 3. Submarine: 3 squares long
// 4. Destroyer: 3 squares long
// 5. Patrol Boat: 2 squares long

public class Ships {
	int size;
	public Point[] position;
	static int health;
	void create() {
		position = new Point[size];
		
		Random rand = new Random();
		for(int i = 0; i < position.length; i++) {
		    position[i] = new Point();
		}
		
		// Create the ship
		position[0].x = rand.nextInt(10);
		position[0].y = rand.nextInt(10);
		
		// Random orientation
		// 0 -> left
		// 1 -> top
		// 2 -> right
		// 3 -> bottom
		
		int x,y;
		
		int orientation = rand.nextInt(4);
		if (orientation == 0) {
			x = -1;
			y = 0;
		}
		
		else if (orientation == 1) {
			x = 0;
			y = 1;
		}
		
		else if (orientation == 2) {
			x = 1;
			y = 0;
		}
		
		else {
			x = 0;
			y = -1;
		}
		
		for (int i = 1; i<size; i++) {
			position[i].x = position[i-1].x + x;
			position[i].y = position[i-1].y + y;
		}
		
	}
	
	void printCoord() {
		for (int i = 0; i < this.size; i++) {
			System.out.println("x -> " + position[i].x + " and y -> " + position[i].y);
		}
	}

	static boolean exists() {
		// Return true if exists otherwise false
		if (health == 0) {
			return false;
		}
		return true;
		
	}
	
	static void destroy() {
//		Destroy the ship
	}
	
}
