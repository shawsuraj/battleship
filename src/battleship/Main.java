/**
* The Battleship program is a game.
*
* @author  Suraj Shaw
* @since   2014-03-31 
*/

package battleship;

import java.io.File;
import java.io.IOException;

public class Main extends globalConstants{
	public static void menu() {
		
		String menuOptions = "1) New Game\n"
							+ "2) Continue\n"
							+ "3) Load Game\n" 
							+ "4) Options\n"
							+ "5) Help\n"
							+ "6) Quit\n";
		System.out.println(menuOptions);
		System.out.println("Select an option : ");
	}
	
	public static void loadGame() {
		
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
		while (dontExit) {
			heading();
			menu();
			Game game = new Game();
			
			int option = scan.nextInt();
			scan.nextLine();	// It consumes the \n character
			
			switch (option) {
			
			case 1 :
				game.newGame();
				game.play();
				game.saveCurrentGame();
				break;
				
			case 2 :
				break;
				
			case 3 :
//				game.loadGame();
				game.play();
				break;
				
			case 6 :
				dontExit = false;
				break;

			default :
				System.out.println("Please Enter a valid option");
				break;
			}
		}
		
		scan.close();
	}
}