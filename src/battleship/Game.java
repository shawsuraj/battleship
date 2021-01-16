package battleship;

import java.awt.Point;

public class Game extends globalConstants {
	Board cBoard;
	Ships aircraft_carrier, battleship, submarine, destroyer, patrol_boat;
	
	public void initialise() {
		cBoard = new Board();
		cBoard.create();
		// cBoard.print();

		// Creating ships
		
		Ships aircraft_carrier = new Ships();
		aircraft_carrier.size = 5;
		aircraft_carrier.create();
		// aircraft_carrier.printCoord();
	
		Ships battleship = new Ships();
		battleship.size = 4;
		battleship.create();
	
		Ships submarine = new Ships();
		submarine.size = 3;
		submarine.create();
		  
		Ships destroyer = new Ships();
		destroyer.size = 3;
		destroyer.create();
	
		Ships patrol_boat = new Ships();
		patrol_boat.size = 2;
		patrol_boat.create();
		
		// Adding ships to the board
		
		cBoard.addToBoard(aircraft_carrier);
		cBoard.addToBoard(battleship);
		cBoard.addToBoard(submarine);
		cBoard.addToBoard(destroyer);
		cBoard.addToBoard(patrol_boat);
	
		// cBoard.print();
	}
	
	public void play() {
		Point coord = shoot();
		boolean isHit = cBoard.update(coord);
		attackStatus(isHit);
	}
	
	public Point shoot() {
		Point hitPoint = new Point();
		
		System.out.printf("Enter x:  ");
		hitPoint.x = scan.nextInt();
		
		System.out.printf("Enter y:  ");
		hitPoint.y = scan.nextInt();
		
		return hitPoint;
	}
	
	static void slowPrint(String message, int delay) {
        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try {
                Thread.sleep(delay);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
	static void intro() {
		String stat = "Hello, welcome to Battleship. This is a game of patience " +
					  "and logic. If you think you lack anything, please leave " +
					  "the game.\n";
		slowPrint(stat, 1);
		
	}
	
	void attackStatus(boolean isHit) {
		if (isHit) {
			System.out.println("It was a Hit!");
		} else {
			System.out.println("It was a Miss!");
		}
	}
	
	void exit() {
		System.out.println("Thank you for playing the game.");
	}
	
	public void start() {
		initialise();
		heading();
		intro();
	}
}



