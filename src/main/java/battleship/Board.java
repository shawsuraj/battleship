package battleship;

import java.awt.Point;
import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;


public class Board {
	private String[][] boardMatrix;

	// Create a new board
	public void create(int size) {
		boardMatrix = new String[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boardMatrix[i][j] = "N";
			}
		}
	}

//	 Print the board for debugging
//	void print() {
//		System.out.println("\n");
//		for (int i = 0; i < this.boardMatrix.length; i++) {
//			System.out.println(Arrays.toString(boardMatrix[i]));
//		}
//	}
	
//	// output board
	public void print() {
		int gameBoardLength = this.boardMatrix.length;
		System.out.println("\n");
		System.out.print("    ");
		for(int i = 0; i < gameBoardLength; i++ ) {
			System.out.print(i + 1 + "   ");
		}
		System.out.println("\n");

		for(int y = 0; y <  gameBoardLength; y++) {
			if (y < 9) {
				System.out.print(y + 1 + "  ");
			} else {
				System.out.print(y + 1 + " ");
			}
			
			for(int x = 0; x <  gameBoardLength; x++) {
				String position = this.boardMatrix[y][x];
				if (position == "H" || position == "M") {
					System.out.print("[" + position + "]" + " ");
				} else {
					System.out.print("[-]"  + " ");
				}
				
			}

			System.out.println("\n");
		}
	}

	// Add ships to board
	public void addShipToBoard(Ship s) {
		try {
			s.newPosition();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isValidShipPosition(s)) {
			for(int i=0; i < s.getSize(); i++) {
				// Condition to place ships only in board matrix
				if (s.getPosition()[i].x >= 0 && s.getPosition()[i].y >= 0
					&& s.getPosition()[i].x < this.boardMatrix.length && s.getPosition()[i].y < this.boardMatrix.length ) {
					boardMatrix[s.getPosition()[i].y][s.getPosition()[i].x] = s.getName();
				}
			}
		} else {
			addShipToBoard(s);
		}
	}

	public void addMonsterToBoard(Monster m) {
		try {
			m.newPosition();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isValidMonsterPosition(m)) {
			if (m.getPosition().x >= 0 && m.getPosition().y >= 0
				&& m.getPosition().x < this.boardMatrix.length && m.getPosition().y < this.boardMatrix.length ) {
				boardMatrix[m.getPosition().y][m.getPosition().x] = m.getName();
			}
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
	public boolean isValidShipPosition(Ship s) {
		for (Point p : s.getPosition()) {
			if (!boardMatrix[p.y][p.x].equals("N")) {
				return false;
			}
		}
		return true;
	}

	public boolean isValidMonsterPosition(Monster m) {
		if (!boardMatrix[m.getPosition().y][m.getPosition().x].equals("N")) {
			return false;
		}
		return true;
	}
	
	public void clearBoard() {
		for(int i = 0; i < this.boardMatrix.length; i++){
		    for(int j = 0; j < 10; j++){
		       this.boardMatrix[i][j] = "N";
		    }
		}
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
