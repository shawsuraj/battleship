/**
* The Battleship program is a game.
*
* @author  Suraj Shaw
* @since   2014-03-31 
*/

package battleship;

public class Main extends globalConstants{
	public static void menu() {
		String menuOptions = "1) New Game\n"
							+ "2) Load Game\n" 
							+ "3) Options\n"
							+ "4) Help\n"
							+ "5) About Creater\n";
		System.out.println(menuOptions);
		System.out.printf("Select an option : ");
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		heading();
		menu();
		
		int option = scan.nextInt();
		
		switch (option) {
		
		case 1 :
			game.newGame();
			game.play();
			
			scan.nextLine();
			System.out.println("Do you want to continue : ");
			String toCont = scan.nextLine();
			
			if (toCont.equals("Y") || toCont.equals("y")) {
				System.out.println("Do you want to continue :");
			} else {
				System.out.println("Exit");
				game.exit();
			}

			scan.close();
			break;
			
		case 2 :
			game.loadGame();
			game.play();
			break;
			
		case 3 :
			break;
		}
		
		
			
		
		
//		cBoard.print();
		
		
		
	}
	  

}