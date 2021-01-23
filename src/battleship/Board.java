package battleship;

import java.awt.Point;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Board {
	private String[][] boardMatrix;

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
			System.out.println(Arrays.toString(boardMatrix[i]));
		}
	}

	// Add ships to board
	public void addToBoard(Ship s) {
		try {
			s.newPosition();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isValidPosition(s)) {
			for(int i=0; i < s.getSize(); i++) {
				// Condition to place ships only in board matrix
				if (s.getPosition()[i].x >= 0 && s.getPosition()[i].y >= 0 
					&& s.getPosition()[i].x < 10 && s.getPosition()[i].y < 10 ) {
					boardMatrix[s.getPosition()[i].x][s.getPosition()[i].y] = s.getName();
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
		for (Point p : s.getPosition()) {
			if (!boardMatrix[p.x][p.y].equals("N")) {
				return false;
			}
		}
		return true;
	}
	
	// getters and setters
	/**
	 * @return the boardMatrix
	 */
	public String[][] getBoardMatrix() {
		return boardMatrix;
	}

	/**
	 * @param boardMatrix the boardMatrix to set
	 */
	public void setBoardMatrix(String[][] boardMatrix) {
		this.boardMatrix = boardMatrix;
	}
}
