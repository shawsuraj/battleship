package battleship;

import java.awt.Point;

public class Radar {
	private int rem = 4;
	
	public boolean use(Board board, Point p) {
		this.rem--;
		for (int i = p.y - 1; i <= p.y + 1; i++) {
			for (int j = p.x - 1; j <= p.x + 1; j++) {
				if (board.getBoardMatrix()[i][j] != "N" && 
					board.getBoardMatrix()[i][j] != "K" && 
					board.getBoardMatrix()[i][j] != "C" &&
					board.getBoardMatrix()[i][j] != "H" &&
					board.getBoardMatrix()[i][j] != "M") {
					System.out.print(i+"   "+j);
					return true;
				}
			}
		}
		return false;
	}
	
	public int getRem() {
		return rem;
	}

	public void setRem(int rem) {
		this.rem = rem;
	}
	
	
}
