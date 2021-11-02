package battleship;

import java.io.File;
import java.io.IOException;

public class Main{
	public static void menu() {
		
		String menuOptions = "1) New Game\n"
							+ "2) Continue\n"
							+ "3) Load Game\n" 
							+ "4) Options\n"
							+ "5) Help\n"
							+ "6) Quit\n";
		System.out.println(menuOptions);
		System.out.print("Select an option : ");
	}
	

	
	public static void checks() {
		File gameJSON = new File("savedgames.json");
		 if (!gameJSON.exists()) {
			 try {
				if(!gameJSON.createNewFile()){
					System.out.println("File cannot be created");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("Does not Exists");
		 }
	} 
	
	
	public static void main(String[] args) {
		
		checks();
		boolean dontExit = true;
		boolean canCont = false;
		Game game = new Game();
		while (dontExit) {
			GlobalMethods.heading();
			menu();
			int option = GlobalMethods.inputInt();
			switch (option) {
			
			case 1 :
				game.newGame();
				canCont = true;
				game.play();
//				game.saveCurrentGame();
				break;
				
			case 2 :
				if (canCont) {
					game.play();
				} else {
					System.out.println("Start a game first to continue.");
				}
				
			case 3 :
				// game.loadGame();
				// game.play();
				System.out.println("This is coming soon. Devs are working for u. :)");
				break;
				
			case 5 :
				System.out.println("Instructions of the game : ");
				System.out.print("\nThe computer places the following five types of ship of different lengths randomly on a 10 by 10 board. The types of ship and their lengths are shown below:\n"
						+ "   1. Aircraft carrier: 5 squares long\n"
						+ "   2. Battleship: 4 squares long\n"
						+ "   3. Submarine: 3 squares long\n"
						+ "   4. Destroyer: 3 squares long\n"
						+ "   5. Patrol Boat: 2 squares long\n\n"
						+ "The player cannot see the ship position"
						+ "The player ‘shoots’ in order to hit and sink all the ships of the computer. A ship sinks when all of its squares have been hit.\n"
						+ "The player should enter the X,Y coordinates of a shot. And enter x = 0 and y = 0 to go back to main menu.\n"
						+ "The game will display the appropriate message according to the outcome of the shot:\n"
						+ "   - My ship was hit!\n"
						+ "   - You missed!\n"
						+ "   - You sank my [ship type]!\n\n"
						+ "Scoring rules :\n"
						+ "   1. -(1) for every shoot.\n"
						+ "   2. +(1) for every hit.\n"
						+ "   3. +(2 * ship_size) for sinking every ship.\n\n"
						+ "The board also contains two sea monsters, Kraken and Cetus, which will be annoyed if hit."
						+ "If Kraken is hit, it will consume all of the points in the player’s score at the time at which it is hit. "
						+ "If Cetus is hit, it will cause all un-sunk ships on the board to move to different places on the board."
						+ "Each sea monster will be placed on a random square (which is not occupied by a ship) immediately after the computer has placed the ships on the board.\n"
						+ "Tip : Annoying monsters is not always a bad idea.\n\n");
				System.out.println("Press enter to continue...\n");
				GlobalMethods.input();
				break;
				
			case 6 :
				dontExit = false;
				break;

			default :
				System.out.println("Please Enter a valid option");
				break;
			}
		}
		
		GlobalMethods.scan.close();
	}
}
