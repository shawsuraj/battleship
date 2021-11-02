package battleship;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.nio.charset.StandardCharsets;

/**
 * This class holds of the main logics of the game.
 */
public class Game extends globalConstants {
	private Board cBoard;

	// Initialising all the ships
	private List<Ship> ships = new ArrayList<>();
	
	private List<Point> shootCoord = new ArrayList<>(); 
	
	private int score = 0;	// Score of the player
	private int hits = 0;	// Total hits
	private int miss = 0;	// Total miss

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
		List<String> sunked = new ArrayList<>();
		List<String> unsunked = new ArrayList<>();
		for (Ship s : this.ships) {
			if (s.getHealth() == 0) {
			sunked.add(s.getFullName());
			} else {
				unsunked.add(s.getFullName());
			}
		}
		
		
		while (startShooting(sunked, unsunked)) {
			this.cBoard.print();
			printStats(sunked, unsunked);
		};
		
	}
	
	public boolean startShooting(List<String> sunked, List<String> unsunked) {
		Point coord = shoot();
		
		for (Point sc : shootCoord) {
			if (coord.getX() == sc.getX() && coord.getY() == sc.getY()) {
				slowPrint("You already shot this target.", 100);
				return true;
			}
		}
		
		this.shootCoord.add(coord);
		
		if (coord.x == 0 || coord.y == 0) {
			return false;
		} else if (finished()) {
			exit();
			return false;
		}
		
		boolean attackStat = isHit(coord);
		String hitStat;
		
		if(attackStat) {
			hitStat = "My ship was hit!";
			 for (Ship s : this.ships) {
		           if (s.getName() != null && s.getName().equals(this.cBoard.getBoardMatrix()[coord.y - 1][coord.x - 1])) {
		        	   s.setHealth(s.getHealth() - 1);
		        	   if (s.destroyed()) {
		        		   score += s.getSize() * 2;
		        		   sunked.add(s.getFullName());
		        		   System.out.println(String.format("You sank my %s!", s.getFullName()));
		        	   } else {
		        		   unsunked.add(s.getFullName());
		        	   }
		        	   return true;
		           }
		      }
		} else {
			hitStat = "You missed!";
		}
		this.cBoard.update(coord, attackStat);
		slowPrint("\n" + hitStat, 100);
		
		// Details of the player's game
		// System.out.println(String.format("\n[Score : %d] [Shot(s) : %d] [Hit(s) : %d]  [Miss(es) : %d]  [hit-to-miss ratio : %.2f]",this.score, this.hits+this.miss, this.hits, this.miss, hm_ratio() ));
		
		return true; 
	}
	
	/**
	  * To check if shoot was a hit or miss.
	  * 
	  * @param hitPoint  the coordinates of the shoot
	  * @return boolean - hit or miss
	*/ 
	public boolean isHit(Point hitPoint) {
//		if (cBoard.getBoardMatrix()[hitPoint.y - 1][hitPoint.x - 1].equals("H") || cBoard.getBoardMatrix()[hitPoint.y - 1][hitPoint.x - 1].equals("M")) {
//			slowPrint("You already shot this target.", 100);
//			this.miss++;
//			return false;
//		} else 
			
		if (cBoard.getBoardMatrix()[hitPoint.y - 1][hitPoint.x - 1].equals("N")) {
			this.miss++;
			return false;
		} else{
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
			if (hitPoint.x < 0 || hitPoint.x > 10 || hitPoint.y < 0 || hitPoint.y > 10) {
				System.out.println("Please enter valid coordinates i.e within range [1,10]");
			}
			
			System.out.println("Enter x and y coordinates to shoot. Enter x = 0 or y = 0 to exit shooting.");
			System.out.print("Enter x:  ");
			hitPoint.x = input_int();
//			scan.nextLine();	// It consumes the \n character

			System.out.print("Enter y:  ");
			hitPoint.y = input_int();
//			scan.nextLine();	// It consumes the \n character
			
		} while (hitPoint.x < 0 || hitPoint.x > 10 || hitPoint.y < 0 || hitPoint.y > 10);
		
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
		slowPrint(stat, 0);

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
			String doSave = input_onlyString();
			if (doSave != null && (doSave.equals("Y") || doSave.equals("y"))) {
				saveGameToJSON();
				System.out.println("Your progress have beenn saved. ");
			}
		}
		System.out.println("Thank you for playing the game.");
	}
	
	public void printStats(List<String> sunked, List<String> unsunked) {
		System.out.println(String.format("*****************************************************************************\n"
						 + "[Score : %d] [Shot(s) : %d]\n"
						 + "[Hit(s) : %d] [Miss(es) : %d] [hit-to-miss ratio : %.2f]\n\n"
						 + "Ships sunked : " + sunked + "\n"
						 + "Ships unsunked : " + unsunked + "\n"
						 + "*****************************************************************************"
						 , this.score, this.hits+this.miss, this.hits, this.miss, hm_ratio()));
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
			totalHealth += s.getHealth();
		}
		
		if (totalHealth == 0) return true;
		return false;
	}
	
	/**
	 * Save games details
	 */
	public void saveGameToJSON(String gameName) {
		JSONArray games = new JSONArray();
        JSONObject obj = new JSONObject();
        
//        System.out.println("The game will be saved by name : ");
//        String gameName = scan.nextLine();
        
        obj.put("name", gameName);

        // Translating board data into JSON format
        JSONArray board = new JSONArray();
        for (String[] i : this.cBoard.getBoardMatrix()) {
          JSONArray arr = new JSONArray();
          for (String j : i) {
            arr.put(j); // or some other conversion
          }
          board.put(arr);
        }
        obj.put("board", board);
        
        // Translating ships data into JSON format
        JSONArray shipArray = new JSONArray();
        for (Ship s : this.ships) {
        	JSONObject ship = new JSONObject();
        	ship.put("name", s.getName());
        	ship.put("fulName", s.getFullName());
        	ship.put("size", s.getSize());
        	JSONArray positionArray = new JSONArray();
        	for (Point p : s.getPosition()) {
        		JSONArray pointArray = new JSONArray();
        		pointArray.put(p.x);
        		pointArray.put(p.y);
        		positionArray.put(pointArray);
        	}
        	ship.put("position", positionArray);
        	ship.put("health", s.getHealth());
        	shipArray.put(ship);
        }
        obj.put("ships", shipArray);
        
        // Translating player stats into JSON format
        obj.put("score", this.score);
        obj.put("hits", this.hits);
        obj.put("miss", this.miss);
        
        games.put(obj);

        try (FileWriter file = new FileWriter("savedgames.json")) {
            file.write(games.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveCurrentGame() {
		saveGameToJSON("lastGame");
	}
 	
//	public void loadGame() throws IOException {
//		InputStream is = new FileInputStream("savedgames.json");
//		String output = new String(is.readAllBytes(), StandardCharsets.UTF_8);
//
//
//        JSONTokener tokener = new JSONTokener(is);
//        JSONObject object = new JSONObject(tokener);
//        
//        JSONArray games = new JSONArray(tokener);
//        
//        System.out.println("Id  : " + object.getLong("id"));
//        System.out.println("Name: " + object.getString("name"));
//        System.out.println("Age : " + object.getInt("age"));
//
//        System.out.println("Courses: ");
//        JSONArray courses = object.getJSONArray("courses");
//        for (int i = 0; i < courses.length(); i++) {
//            System.out.println("  - " + courses.get(i));
//        }
//        
//     // Translating board data into JSON format
//        JSONArray board = new JSONArray(games);
//        for (String[] i : this.cBoard.getBoardMatrix()) {
//          JSONArray arr = new JSONArray();
//          for (String j : i) {
//            arr.put(j); // or some other conversion
//          }
//          board.put(arr);
//        }
//        obj.put("board", board);
//        
//        // Translating ships data into JSON format
//        JSONArray shipArray = new JSONArray();
//        for (Ship s : this.ships) {
//        	JSONObject ship = new JSONObject();
//        	ship.put("name", s.getName());
//        	ship.put("fulName", s.getFullName());
//        	ship.put("size", s.getSize());
//        	JSONArray positionArray = new JSONArray();
//        	for (Point p : s.getPosition()) {
//        		JSONArray pointArray = new JSONArray();
//        		pointArray.put(p.x);
//        		pointArray.put(p.y);
//        		positionArray.put(pointArray);
//        	}
//        	ship.put("position", positionArray);
//        	ship.put("health", s.getHealth());
//        	shipArray.put(ship);
//        }
//        obj.put("ships", shipArray);
//        
//        // Translating player stats into JSON format
//        obj.put("score", this.score);
//        obj.put("hits", this.hits);
//        obj.put("miss", this.miss);
//        
//        games.put(obj);
//    }
	
	void contGame() {
		
	}
	
	
	
}
