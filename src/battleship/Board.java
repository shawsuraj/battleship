package battleship;

import java.awt.Point;
import java.util.Arrays;

public class Board {
	String[][] boardMatrix;

	// Create a new board
	public void create() {
		boardMatrix = new String[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardMatrix[i][j] = "N";
				}
			}
	}

	// Print the board
	void print() {
		System.out.println("\n");
		for (int i = 0; i < 10; i++) {
//			for (j = 0; j < 10; j++) {
//				System.out.println(boardMatrix[i][j]);
//				}
			System.out.println(Arrays.toString(boardMatrix[i]));
			}
		}

	// Add ships to board
	public void addToBoard(Ship s) {
		s.newPosition();
		if (isValidPosition(s)) {
			for(int i=0; i < s.size; i++) {
				// Condition to place ships only in board matrix
				if (s.position[i].x >= 0 && s.position[i].y >= 0 
					&& s.position[i].x < 10 && s.position[i].y < 10 ) {
					boardMatrix[s.position[i].x][s.position[i].y] = s.name;
				}
			}
		} else {
			addToBoard(s);
		}
		
	}

	// Update the board
	public void update(Point hitPoint, boolean attackStat) {
		if (attackStat) {
			boardMatrix[hitPoint.y - 1][hitPoint.x - 1] = "H";
		} else {
			boardMatrix[hitPoint.y - 1][hitPoint.x - 1] = "M";
		}
		
		
	}
	
	// Position is valid i.e don't overlap with other ships or go out of board
	public boolean isValidPosition(Ship s) {
		for (Point p : s.position) {
			if (boardMatrix[p.x][p.y] != "N") {
				return false;
			}
		}
		return true;
	}
}
