package battleship;

import java.awt.Point;
import java.util.Arrays;

public class Board {
	public String[][] board;
	
	public void create() {
		board = new String[10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = "N";
				}
			}
	}
	
	
	void print() {
		for (int i = 0; i < 10; i++) {
//			for (j = 0; j < 10; j++) {
//				System.out.println(board[i][j]);
//				}
			System.out.println(Arrays.toString(board[i]));
			}
		}
	
	public void addToBoard(Ships ship) {
		for(int i=0; i < ship.size; i++) {
			if (ship.position[i].x >= 0 && ship.position[i].y >= 0 && ship.position[i].x < 10 && ship.position[i].y < 10 ) {
				board[ship.position[i].x][ship.position[i].y] = "S";
			}
		}
	}
	
	public boolean update(Point hitPoint) {
		if (board[hitPoint.x][hitPoint.y] == "N") {
			board[hitPoint.x][hitPoint.y] = "M";
			return false;
		} else if (board[hitPoint.x][hitPoint.y] == "S") {
			board[hitPoint.x][hitPoint.y] = "H";
			return true;
		}
		
		return false;
		
	}
}