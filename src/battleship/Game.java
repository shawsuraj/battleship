package battleship;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

/**
 * This class holds of the main logics of the game.
 */
public class Game extends globalConstants {
	Board cBoard;

	// Initialising all the ships
	List<Ship> ships = new ArrayList<Ship>();
	
	int score = 0;	// Score of the player
	int hits = 0;	// Total hits
	int miss = 0;	// Total miss
	
	boolean isEasy = false;

	// Constructor
	public Game() {
		
	}
	
	/**
	  * Creates a new game and places the ship randomly.
	*/
	public void newGame() {
		heading();
		intro();
		
		this.cBoard = new Board();
		this.cBoard.create();
		// cBoard.print();

		// Creating ships
		this.ships.add(new Ship(5, "A", "Aircraft carrier"));	// Aircraft carrier
		this.ships.add(new Ship(4, "B", "Battleship"));	// Battleship
		this.ships.add(new Ship(3, "S", "Submarine"));	// Submarine
		this.ships.add(new Ship(3, "D", "Destroyer"));	// Destroyer
		this.ships.add(new Ship(2, "P", "Patrol Boat"));	// Patrol Boat


		// Adding ships to the board
		for (Ship s : ships) {
			cBoard.addToBoard(s);
		}
		

		 cBoard.print();
	}

	/**
	  * Creates a new game and places the ship randomly.
	*/
	public void play() {
		while (true) {
			Point coord = shoot();
			boolean attackStat = isHit(coord);
			
			if (attackStat) {
				System.out.println("My ship was hit!");
			} else {
				System.out.println("You missed!");
			}
			
			if(attackStat) {
				 for (Ship s : ships) {
			           if (s.name == this.cBoard.boardMatrix[coord.y - 1][coord.x - 1]) {
			        	   s.health--;
			        	   if (s.destroyed()) {
			        		   score += s.size * 2;
			        		   System.out.println(String.format("You sank my %s!", s.fullName));
			        	   }
			        	   break;
			           }
			      }
			}
			
			this.cBoard.update(coord, attackStat);
			this.cBoard.print();
			
			// Details of the player's game
			System.out.println(String.format("\n[Score : %d] [Shot(s) : %d] [Hit(s) : %d]  [Miss(es) : %d]  [hit-to-miss ratio : %.2f]",this.score, this.hits+this.miss, this.hits, this.miss, hm_ratio() ));
			
			if (finished()) {
				exit();
				break;
			}
		}
		
	}
	
	/**
	  * To check if shoot was a hit or miss.
	  * 
	  * @param hitPoint  the coordinates of the shoot
	  * @return boolean - hit or miss
	*/ 
	boolean isHit(Point hitPoint) {
		if (cBoard.boardMatrix[hitPoint.y - 1][hitPoint.x - 1].equals("N")) {
			this.miss++;
			return false;
		} else {
			this.score++;
			this.hits++;
			return true;
		}
	}

	/**
	  * Take input of the shoot coordinates form user.
	  * 
	  * @return Point - shoot coordinates
	*/ 
	public Point shoot() {
		Point hitPoint = new Point(1,1);
		
		// Input within the range of [1,10]
		do {
			if (hitPoint.x <= 0 || hitPoint.x > 10 || hitPoint.y <= 0 || hitPoint.y > 10) {
				System.out.println("Please enter valid coordinates i.e within range [1,10]");
			}
			
			System.out.printf("Enter x:  ");
			hitPoint.x = scan.nextInt();

			System.out.printf("Enter y:  ");
			hitPoint.y = scan.nextInt();
			
		} while (hitPoint.x <= 0 || hitPoint.x > 10 || hitPoint.y <= 0 || hitPoint.y > 10);
		
		this.score--;	// decrement in score for every shoot

		return hitPoint;
	}

	/**
	  * Introduction of a new game.
	*/ 
	static void intro() {
		String stat = "Hello, welcome to Battleship. This is a game of just "
					+ "luck and little bit of logic. If you think you are lucky, "
				    + "and have some common sense. PLAY THIS GAME NOW.\n\n"
				    + "The computer will randomly place the ships on the board,"
				    + "vertically or horizontally, taking care that no ship "
				    + "overlaps with another ship or is out of the bounds of the 10 by 10 board."
				    + "You have ‘shoots’ in order to hit and sink all the ships of the computer.\n"
				    + "A ship sinks when all of its squares have been hit.\n";
		slowPrint(stat, 10);

	}
	
	/**
	  * Hit-to-miss ratio.
	  * 
	  * @return float - hit-to-miss ratio
	*/ 
	float hm_ratio() {
		if (this.hits == 0 || this.miss == 0) {
			return 0;
		}
		
		return (this.hits/this.miss);
	}

	/**
	  * Exit
	*/ 
	void exit() {
		if (finished()) {
			System.out.println("You have successfully completed the game. ");
		} else {
			System.out.println("Do you want to save the progress? (Y/N)");
			String doSave = scan.nextLine();
			if (doSave == "Y" || doSave == "y") {
//				saveGameToJSON();
				System.out.println("Your progress have beenn saved. ");
			}
		}
		System.out.println("Thank you for playing the game.");
	}

	
	/**
	 * 
	 * 
	 * @param
	 * @return 
	 */
	boolean finished() {
		int totalHealth = 0;
		for (Ship s : ships) {
			totalHealth += s.health;
		}
		
		if (totalHealth == 0) return true;
		return false;
	}
	
	/**
	 * Save games to 
	 */
//	@SuppressWarnings("unchecked")
//	public void saveGameToJSON() {
//		JSONArray games = new JSONArray();
//		
//        JSONObject obj = new JSONObject();
//        
//        obj.put("name", "Game1");
//
//        // Translating board data into JSON format
//        JSONArray board = new JSONArray();
//        for (String[] i : cBoard.boardMatrix) {
//          JSONArray arr = new JSONArray();
//          for (String j : i) {
//            arr.add(j); // or some other conversion
//          }
//          board.add(arr);
//        }
//        obj.put("board", board);
//        
//        // Translating ship data into JSON format
//        JSONArray ships = new JSONArray();
//        for (Ship s : this.ships) {
//        	JSONObject ship = new JSONObject();
//        	ship.put("name", s.name);
//        	ship.put("fulName", s.fullName);
//        	ship.put("size", s.size);
//        	ship.put("position", s.position);
//        	ship.put("health", s.health);
//        	ships.add(ship);
//        }
//        obj.put("ships", ships);
//        
//        // Translating player stats into JSON format
//        obj.put("score", this.score);
//        obj.put("hits", this.hits);
//        obj.put("miss", this.miss);
//        
//        games.add(obj);
//
//        try (FileWriter file = new FileWriter("c:\\projects\\test.json")) {
//            file.write(obj.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        
//
//    }
	
	public void loadGame() {}
	
	void contGame() {
		
	}
	
	
	
}
